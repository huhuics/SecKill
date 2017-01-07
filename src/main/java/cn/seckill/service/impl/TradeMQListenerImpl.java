/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import java.util.Date;

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
import cn.seckill.service.TradeMQListener;
import cn.seckill.util.AssertUtil;
import cn.seckill.util.LogUtil;

/**
 * 交易信息MQ监听接口实现类
 * @author HuHui
 * @version $Id: TradeMQListenerImpl.java, v 0.1 2017年1月7日 下午3:57:33 HuHui Exp $
 */
@Service("tradeMQListener")
public class TradeMQListenerImpl implements TradeMQListener {

    private static final Logger logger = LoggerFactory.getLogger(TradeMQListenerImpl.class);

    @Resource
    private OrdersMapper        ordersMapper;

    @Resource
    private GoodsMapper         goodsMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public void handle(final Orders order) {

        LogUtil.info(logger, "收到消息,order={0}", order);

        transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                TradeStatusEnum tradeStatus = null;

                //2.修改商品数量
                boolean ret = updateGoods(order.getGoodsId());

                //3.写入订单
                tradeStatus = ret ? TradeStatusEnum.SUCCESS : TradeStatusEnum.FAILED;
                order.setTradeStatus(tradeStatus.getCode());
                ordersMapper.insert(order);
                return ret;

            }
        });

        LogUtil.info(logger, "消息处理完成");

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
            LogUtil.error(e, logger, "更新商品库存失败,goodsId={0}", goodsId);
            ret = false;
        }

        return ret;
    }

}
