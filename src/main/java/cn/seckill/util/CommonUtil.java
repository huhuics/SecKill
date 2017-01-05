/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.util;

import java.util.Date;

import cn.seckill.domain.Orders;
import cn.seckill.request.PayRequest;

/**
 * 常用工具类
 * @author HuHui
 * @version $Id: CommonUtil.java, v 0.1 2017年1月5日 上午10:49:51 HuHui Exp $
 */
public final class CommonUtil {

    /**
     * 将支付请求转换为领域对象
     * @param request
     * @return
     */
    public static Orders convert2Order(PayRequest request) {
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

}
