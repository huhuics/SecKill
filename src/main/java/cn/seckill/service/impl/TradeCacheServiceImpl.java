/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import cn.seckill.dao.OrdersMapper;
import cn.seckill.domain.Goods;
import cn.seckill.domain.Orders;
import cn.seckill.enums.TradeStatusEnum;
import cn.seckill.request.PayRequest;
import cn.seckill.service.TradeCacheService;
import cn.seckill.util.AssertUtil;
import cn.seckill.util.CommonUtil;
import cn.seckill.util.LogUtil;
import cn.seckill.util.RedisUtil;

/**
 * 
 * @author HuHui
 * @version $Id: TradeCacheServiceImpl.java, v 0.1 2017年1月19日 下午2:52:38 HuHui Exp $
 */
@Service
public class TradeCacheServiceImpl implements TradeCacheService {

    private static final Logger logger = LoggerFactory.getLogger(TradeCacheServiceImpl.class);

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private OrdersMapper        ordersMapper;

    public static String        key    = "GOODS_TICKET";

    @Override
    public TradeStatusEnum pay(final PayRequest request) {
        LogUtil.info(logger, "收到支付请求,request={0}", request);

        AssertUtil.assertNotNull(request, "支付请求不能为空");
        request.validate();

        //1.创建订单
        final Orders order = CommonUtil.convert2Order(request);

        //2.修改商品数量
        final boolean ret = updateGoods(request.getGoodsId());

        boolean transRet = transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                TradeStatusEnum tradeStatus = null;

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

    protected boolean updateGoods(Long goodsId) {
        Goods goods = RedisUtil.get(key, Goods.class);
        Long quantity = goods.getQuantity();
        --quantity;
        goods.setQuantity(quantity);
        RedisUtil.set(key, goods);
        return true;
    }
}
