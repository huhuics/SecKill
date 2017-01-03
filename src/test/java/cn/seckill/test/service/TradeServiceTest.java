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
import cn.seckill.service.TradeService;
import cn.seckill.test.base.BaseTest;
import cn.seckill.util.Money;
import cn.seckill.util.UUIDUtil;

/**
 * 交易测试
 * @author HuHui
 * @version $Id: TradeServiceTest.java, v 0.1 2017年1月3日 下午5:08:42 HuHui Exp $
 */
public class TradeServiceTest extends BaseTest {

    @Resource
    private TradeService tradeService;

    @Test
    public void testPay() {
        Assert.assertNotNull(tradeService);

        PayRequest request = new PayRequest();
        request.setOrderNo(UUIDUtil.geneId());
        request.setGoodsId(1L);
        request.setGoodsName("春运火车票");
        request.setBuyerId("106699001");
        request.setTotalAmount(new Money(166.6));

        TradeStatusEnum ret = tradeService.pay(request);

        Assert.assertTrue(ret == TradeStatusEnum.SUCCESS);
    }
}
