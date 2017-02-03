/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import cn.seckill.service.Computable;
import cn.seckill.service.impl.ExpensiveFunction;
import cn.seckill.service.impl.Memoizer;

/**
 * 测试缓存实现
 * @author HuHui
 * @version $Id: MemoizerTest.java, v 0.1 2017年2月3日 下午8:32:54 HuHui Exp $
 */
public class MemoizerTest {

    private final Computable<String, BigInteger> expensiveFunction = new ExpensiveFunction();

    private final Computable<String, BigInteger> memoizer          = new Memoizer<String, BigInteger>(expensiveFunction);

    @Test
    public void testMemoizer() throws InterruptedException {

        BigInteger ret = memoizer.compute("89");

        Assert.assertTrue(ret.equals(new BigInteger("89")));
    }

}
