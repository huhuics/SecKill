/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

import cn.seckill.domain.Orders;

/**
 * 交易信息MQ监听接口
 * @author HuHui
 * @version $Id: TradeMQListener.java, v 0.1 2017年1月7日 下午3:56:30 HuHui Exp $
 */
public interface TradeMQListener {

    void handle(Orders order);

}
