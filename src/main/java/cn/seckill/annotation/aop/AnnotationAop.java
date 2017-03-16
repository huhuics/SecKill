/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.annotation.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.seckill.annotation.Handler;

/**
 * 切面类,处理标签动作
 * @author HuHui
 * @version $Id: AnnotationAop.java, v 0.1 2017年3月16日 下午9:22:52 HuHui Exp $
 */
@Aspect
@Component
public class AnnotationAop {

    @Around("execution( * cn.seckill.annotation.clazz.Worker.*(..))")
    public Object annotationAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("进入切面");

        Class<?> clazz = Class.forName("cn.seckill.annotation.clazz.Worker");

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getAnnotation(Handler.class) != null) {
                Handler handler = method.getAnnotation(Handler.class);
                System.out.println(handler.methodName() + ", " + handler.returnType() + ", " + handler.paraNums());
            }

        }

        return joinPoint.proceed();

    }

}
