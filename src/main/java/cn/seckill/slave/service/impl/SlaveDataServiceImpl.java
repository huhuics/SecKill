/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.slave.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.seckill.dao.GoodsMapper;
import cn.seckill.domain.Goods;
import cn.seckill.service.SlaveDataService;
import cn.seckill.util.LogUtil;

/**
 * 从Slave库获取数据
 * @author HuHui
 * @version $Id: SlaveDataServiceImpl.java, v 0.1 2017年3月7日 下午9:08:56 HuHui Exp $
 */
@Service
public class SlaveDataServiceImpl implements SlaveDataService {

    private static final Logger logger = LoggerFactory.getLogger(SlaveDataServiceImpl.class);

    @Resource
    private GoodsMapper         goodsMapper;

    @Override
    public Goods getGoodsFromSlave(Long id) {

        LogUtil.info(logger, "[Slave]收到查询goods请求,id={0}", id);

        Goods goods = goodsMapper.selectByPrimaryKey(id);

        LogUtil.info(logger, "[Slave]查询goods结果,goods={0}", goods);

        return goods;
    }

}
