/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import cn.seckill.domain.Orders;
import cn.seckill.request.PayRequest;
import cn.seckill.service.TradeMQService;
import cn.seckill.util.AssertUtil;
import cn.seckill.util.CommonUtil;
import cn.seckill.util.LogUtil;

/**
 * 交易请求发送至MQ接口实现类
 * @author HuHui
 * @version $Id: TradeMQServiceImpl.java, v 0.1 2017年1月7日 下午3:49:41 HuHui Exp $
 */
@Service
public class TradeMQServiceImpl implements TradeMQService {

    private static final Logger logger = LoggerFactory.getLogger(TradeMQServiceImpl.class);

    @Resource
    private JmsTemplate         jmsTemplate;

    @Override
    public boolean pay(PayRequest request) {

        LogUtil.info(logger, "收到支付请求,request={0}", request);

        AssertUtil.assertNotNull(request, "支付请求不能为空");
        request.validate();

        //1.创建订单
        final Orders order = CommonUtil.convert2Order(request);

        //2.发送至MQ
        try {
            jmsTemplate.send(new MessageCreator() {

                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(order);
                }
            });
        } catch (JmsException e) {
            LogUtil.error(e, logger, "消息发送失败");
            return false;
        }

        LogUtil.info(logger, "消息发送成功");

        return true;
    }

}
