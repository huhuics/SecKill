/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import cn.seckill.dao.GoodsMapper;
import cn.seckill.dao.OrdersMapper;
import cn.seckill.domain.Goods;
import cn.seckill.domain.Orders;
import cn.seckill.enums.TradeStatusEnum;
import cn.seckill.request.PayRequest;
import cn.seckill.service.TradeService;
import cn.seckill.util.AssertUtil;
import cn.seckill.util.LogUtil;

/**
 * 交易服务接口实现类
 * @author HuHui
 * @version $Id: TradeServiceImpl.java, v 0.1 2017年1月3日 下午4:31:21 HuHui Exp $
 */
@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);

    /** 定义公平的可重入锁 */
    private static Lock         lock   = new ReentrantLock(Boolean.TRUE);

    @Resource
    private OrdersMapper        ordersMapper;

    @Resource
    private GoodsMapper         goodsMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public TradeStatusEnum pay(final PayRequest request) {

        LogUtil.info(logger, "收到支付请求,request={0}", request);

        AssertUtil.assertNotNull(request, "支付请求不能为空");
        request.validate();

        //1.创建订单
        final Orders order = convert2Order(request);

        boolean transRet = transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                boolean ret = false;
                TradeStatusEnum tradeStatus = null;

                //2.修改商品数量
                ret = updateGoods(request.getGoodsId());

                //3.写入订单
                tradeStatus = ret ? TradeStatusEnum.SUCCESS : TradeStatusEnum.FAILED;
                order.setTradeStatus(tradeStatus.getCode());
                ordersMapper.insert(order);
                return ret;

            }
        });

        LogUtil.info(logger, "支付完成");

        return transRet ? TradeStatusEnum.SUCCESS : TradeStatusEnum.FAILED;
    }

    private Orders convert2Order(PayRequest request) {
        Orders order = new Orders();
        order.setOrderNo(request.getOrderNo());
        order.setGoodsId(request.getGoodsId());
        order.setGoodsName(request.getGoodsName());
        order.setBuyerId(request.getBuyerId());
        order.setTotalAmount(request.getTotalAmount());
        order.setGmtUpdate(new Date());
        order.setGmtCreate(new Date());

        return order;
    }

    private boolean updateGoods(Long goodsId) {

        boolean ret = false;
        lock.lock();
        try {
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            AssertUtil.assertNotNull(goods, "商品不存在");
            AssertUtil.assertTrue(goods.getQuantity() > 0, "商品库存不足! goodsId=" + goodsId);

            Long tempQuantity = goods.getQuantity();
            goods.setQuantity(--tempQuantity);
            goods.setGmtUpdate(new Date());
            goodsMapper.updateByPrimaryKey(goods);

            LogUtil.info(logger, "商品id={0}当前库存={1}", goods.getId(), goods.getQuantity());
            ret = true;
        } catch (Exception e) {
            LogUtil.error(e, logger, "更新商品库存失败,goodsId={0}", goodsId);
            ret = false;
        } finally {
            lock.unlock();
        }
        return ret;
    }

}
