/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.seckill.dao.GoodsMapper;
import cn.seckill.domain.Goods;
import cn.seckill.test.base.BaseTest;
import cn.seckill.util.Money;

/**
 * 
 * @author HuHui
 * @version $Id: GoodsServiceTest.java, v 0.1 2017年1月3日 下午5:11:32 HuHui Exp $
 */
public class GoodsServiceTest extends BaseTest {

    @Resource
    protected GoodsMapper goodsMapper;

    @Test
    public void testInsertGoods() {

        Goods goods = new Goods();
        goods.setName("春运火车票");
        goods.setDescription("2017年春运火车票");
        goods.setTotalAmount(new Money(166.6));
        goods.setQuantity(10000L);
        goods.setGmtCreate(new Date());
        goods.setGmtUpdate(new Date());

        Assert.assertTrue(goodsMapper.insert(goods) > 0);

    }

}
