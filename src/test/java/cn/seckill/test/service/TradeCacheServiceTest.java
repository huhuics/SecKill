/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.seckill.enums.TradeStatusEnum;
import cn.seckill.request.PayRequest;
import cn.seckill.service.TradeCacheService;
import cn.seckill.test.base.BaseTest;
import cn.seckill.util.Money;
import cn.seckill.util.UUIDUtil;

/**
 * 
 * @author HuHui
 * @version $Id: TradeCacheServiceTest.java, v 0.1 2017年1月19日 下午3:52:49 HuHui Exp $
 */
public class TradeCacheServiceTest extends BaseTest {

    @Resource
    private TradeCacheService tradeCacheService;

    @Test
    public void testPay() {
        Assert.assertNotNull(tradeCacheService);

        PayRequest request = new PayRequest();
        request.setOrderNo(UUIDUtil.geneId());
        request.setGoodsId(2L);
        request.setGoodsName("春运火车票");
        request.setBuyerId("106699001");
        request.setTotalAmount(new Money(166.6));

        TradeStatusEnum ret = tradeCacheService.pay(request);

        Assert.assertTrue(ret == TradeStatusEnum.SUCCESS);
    }

}
