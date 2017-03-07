/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.seckill.dao.GoodsMapper;
import cn.seckill.domain.Goods;
import cn.seckill.service.SlaveDataService;
import cn.seckill.test.base.BaseTest;
import cn.seckill.util.LogUtil;

/**
 * 测试切换Slave数据库
 * @author HuHui
 * @version $Id: SlaveDataServiceTest.java, v 0.1 2017年3月7日 下午9:14:30 HuHui Exp $
 */
public class SlaveDataServiceTest extends BaseTest {

    @Resource
    private SlaveDataService slaveDataService;

    @Resource
    protected GoodsMapper    goodsMapper;

    @Test
    public void testFromSlave() {

        Assert.assertNotNull(slaveDataService);

        Goods goods = null;

        goods = goodsMapper.selectByPrimaryKey(5L);

        LogUtil.info(logger, "goods={0}", goods);

        goods = slaveDataService.getGoodsFromSlave(5L);

        goods = goodsMapper.selectByPrimaryKey(5L);

        LogUtil.info(logger, "goods={0}", goods);
    }
}
