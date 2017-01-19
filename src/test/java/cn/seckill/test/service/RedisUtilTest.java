/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import org.junit.Test;

import cn.seckill.domain.Goods;
import cn.seckill.service.impl.TradeCacheServiceImpl;
import cn.seckill.test.base.BaseTest;
import cn.seckill.util.Money;
import cn.seckill.util.RedisUtil;

/**
 * 测试Redis工具类
 * @author HuHui
 * @version $Id: RedisUtilTest.java, v 0.1 2017年1月11日 下午4:10:15 HuHui Exp $
 */
public class RedisUtilTest extends BaseTest {

    @Test
    public void testSet() {

        Goods goods = new Goods();
        goods.setName("春运火车票");
        goods.setDescription("2017年春运火车票");
        goods.setTotalAmount(new Money(166.6));
        goods.setQuantity(10000L);

        RedisUtil.set(TradeCacheServiceImpl.key, goods);
    }

}
