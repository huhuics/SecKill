/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.seckill.service.Computable;
import cn.seckill.util.LogUtil;

/**
 * 
 * @author HuHui
 * @version $Id: ExpensiveFunction.java, v 0.1 2017年2月3日 下午8:12:47 HuHui Exp $
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

    private static final Logger logger = LoggerFactory.getLogger(ExpensiveFunction.class);

    @Override
    public BigInteger compute(String arg) throws InterruptedException {

        LogUtil.info(logger, "arg={0}", arg);

        Thread.sleep(2000);

        //在经过长时间的计算后
        return new BigInteger(arg);
    }

}
