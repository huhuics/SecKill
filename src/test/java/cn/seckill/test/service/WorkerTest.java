/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.seckill.annotation.clazz.Worker;
import cn.seckill.test.base.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: WorkerTest.java, v 0.1 2017年3月16日 下午9:32:56 HuHui Exp $
 */
public class WorkerTest extends BaseTest {

    @Resource
    private Worker worker;

    @Test
    public void test() {
        worker.getWorders(5);
    }

}
