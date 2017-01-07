/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.seckill.domain.Orders;
import cn.seckill.service.TradeMQListener;
import cn.seckill.util.LogUtil;

/**
 * 交易信息MQ监听接口实现类
 * @author HuHui
 * @version $Id: TradeMQListenerImpl.java, v 0.1 2017年1月7日 下午3:57:33 HuHui Exp $
 */
@Service("tradeMQListener")
public class TradeMQListenerImpl implements TradeMQListener {

    private static final Logger logger = LoggerFactory.getLogger(TradeMQListenerImpl.class);

    @Override
    public void handle(Orders order) {

        LogUtil.info(logger, "收到消息,order={0}", order);

        LogUtil.info(logger, "消息处理完成");

    }

}
