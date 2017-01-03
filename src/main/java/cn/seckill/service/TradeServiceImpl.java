/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.seckill.dao.GoodsMapper;
import cn.seckill.dao.OrdersMapper;
import cn.seckill.domain.Goods;
import cn.seckill.domain.Orders;
import cn.seckill.request.PayRequest;
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

    @Resource
    private OrdersMapper        ordersMapper;

    @Resource
    private GoodsMapper         goodsMapper;

    @Override
    @Transactional
    public boolean pay(PayRequest request) {

        LogUtil.info(logger, "收到支付请求,request={0}", request);

        AssertUtil.assertNotNull(request, "支付请求不能为空");
        request.validate();

        Orders order = convert2Order(request);

        //写入订单表
        int ret = ordersMapper.insert(order);

        //修改商品数量
        updateGoods(request.getGoodsId());

        LogUtil.info(logger, "支付完成");

        return ret > 0;
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

    private void updateGoods(Long goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        AssertUtil.assertNotNull(goods, "商品不存在");
        Long tempQuantity = goods.getQuantity();
        goods.setQuantity(--tempQuantity);
        goods.setGmtUpdate(new Date());
        goodsMapper.updateByPrimaryKey(goods);

        LogUtil.info(logger, "商品id={0}当前库存={1}", goods.getId(), goods.getQuantity());
    }

}
