/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.dy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.seckill.dy.ds.DataSourceHolder;
import cn.seckill.util.LogUtil;

/**
 * 通过AOP拦截执行读slave数据库
 * @author HuHui
 * @version $Id: SlaveDBAspect.java, v 0.1 2017年3月7日 下午8:59:28 HuHui Exp $
 */
@Aspect
@Component
public class SlaveDBAspect {

    private static final Logger logger = LoggerFactory.getLogger(SlaveDBAspect.class);

    @Around("execution( * cn.seckill.slave.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        LogUtil.info(logger, "开始切换到Slave库");
        try {
            DataSourceHolder.setSlave();
            return pjp.proceed(); //continue on the intercepted method
        } finally {
            DataSourceHolder.clearDataSource();
            LogUtil.info(logger, "结束切换到Slave库");
        }
    }

}
