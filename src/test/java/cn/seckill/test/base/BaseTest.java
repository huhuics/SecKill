/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.base;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.seckill.dao.GoodsMapper;
import cn.seckill.domain.Goods;
import cn.seckill.util.Money;

/**
 * 测试基础类
 * @author HuHui
 * @version $Id: BaseTest.java, v 0.1 2017年1月3日 下午4:00:53 HuHui Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Spring-config.xml" })
public class BaseTest {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Resource
    protected GoodsMapper         goodsMapper;

    @Test
    public void testInsertGoods() {

        Goods goods = new Goods();
        goods.setName("春运火车票");
        goods.setDescription("2017年春运火车票");
        goods.setTotalAmount(new Money(166.6));
        goods.setQuantity(10000L);
        goods.setGmtCreate(new Date());
        goods.setGmtUpdate(new Date());

        goodsMapper.insert(goods);

    }

}
