/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.annotation.clazz;

import org.springframework.stereotype.Service;

import cn.seckill.annotation.Handler;

/**
 * 标签测试类
 * @author HuHui
 * @version $Id: Worker.java, v 0.1 2017年3月16日 下午9:18:20 HuHui Exp $
 */
@Service
public class Worker {

    @Handler(methodName = "getWorders", returnType = "int", paraNums = 1)
    public void getWorders(int num) {
        System.out.println("Worker.getWorders()=" + num);
    }

}
