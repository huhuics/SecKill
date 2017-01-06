/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import cn.seckill.service.AsynTradeService;
import cn.seckill.util.AssertUtil;
import cn.seckill.util.CommonUtil;
import cn.seckill.util.LogUtil;

/**
 * 异步交易服务接口实现类
 * @author HuHui
 * @version $Id: AsynTradeServiceImpl.java, v 0.1 2017年1月5日 上午10:40:22 HuHui Exp $
 */
@Service
public class AsynTradeServiceImpl implements AsynTradeService {

    private static final Logger logger     = LoggerFactory.getLogger(AsynTradeServiceImpl.class);

    @Resource
    private OrdersMapper        ordersMapper;

    @Resource
    private GoodsMapper         goodsMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    /** 线程池 */
    private ExecutorService     threadPool = Executors.newCachedThreadPool();

    @Override
    public Boolean pay(final PayRequest request) throws Exception {

        LogUtil.info(logger, "收到支付请求,request={0}", request);

        AssertUtil.assertNotNull(request, "支付请求不能为空");
        request.validate();

        //1.创建订单
        final Orders order = CommonUtil.convert2Order(request);

        Future<Boolean> future = threadPool.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                boolean ret = false;

                ret = transactionTemplate.execute(new TransactionCallback<Boolean>() {

                    @Override
                    public Boolean doInTransaction(TransactionStatus status) {
                        TradeStatusEnum tradeStatus = null;
                        //2.修改商品数量
                        boolean updateRet = updateGoods(request.getGoodsId());

                        //3.写入订单
                        tradeStatus = updateRet ? TradeStatusEnum.SUCCESS : TradeStatusEnum.FAILED;
                        order.setTradeStatus(tradeStatus.getCode());
                        ordersMapper.insert(order);

                        return updateRet;
                    }
                });

                return ret;
            }

        });

        return future.get();
    }

    private boolean updateGoods(Long goodsId) {

        boolean ret = false;
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
            LogUtil.error(logger, "更新商品库存失败,goodsId={0}", goodsId);
            ret = false;
        }
        return ret;
    }

}
