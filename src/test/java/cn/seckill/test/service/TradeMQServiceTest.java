/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.seckill.request.PayRequest;
import cn.seckill.service.TradeMQService;
import cn.seckill.test.base.BaseTest;
import cn.seckill.util.Money;
import cn.seckill.util.UUIDUtil;

/**
 * 消息机制处理交易测试类
 * @author HuHui
 * @version $Id: TradeMQServiceTest.java, v 0.1 2017年1月7日 下午4:01:14 HuHui Exp $
 */
public class TradeMQServiceTest extends BaseTest {

    @Resource
    private TradeMQService tradeMQService;

    @Test
    public void testPay() {
        Assert.assertNotNull(tradeMQService);

        PayRequest request = new PayRequest();
        request.setOrderNo(UUIDUtil.geneId());
        request.setGoodsId(2L);
        request.setGoodsName("春运火车票");
        request.setBuyerId("106699001");
        request.setTotalAmount(new Money(166.6));

        boolean ret = tradeMQService.pay(request);

        Assert.assertTrue(ret);
    }

}
