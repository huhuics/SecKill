/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.seckill.request.PayRequest;
import cn.seckill.service.AsynTradeService;
import cn.seckill.test.base.BaseTest;
import cn.seckill.util.Money;
import cn.seckill.util.UUIDUtil;

/**
 * 异步交易测试类
 * @author HuHui
 * @version $Id: AsynTradeServiceTest.java, v 0.1 2017年1月5日 下午5:26:52 HuHui Exp $
 */
public class AsynTradeServiceTest extends BaseTest {

    @Resource
    private AsynTradeService asynTradeService;

    @Test
    public void testPay() throws Exception {
        Assert.assertNotNull(asynTradeService);

        PayRequest request = new PayRequest();
        request.setOrderNo(UUIDUtil.geneId());
        request.setGoodsId(2L);
        request.setGoodsName("春运火车票");
        request.setBuyerId("106699001");
        request.setTotalAmount(new Money(166.6));

        boolean ret = asynTradeService.pay(request);

        Assert.assertTrue(ret == Boolean.TRUE);
    }

}
