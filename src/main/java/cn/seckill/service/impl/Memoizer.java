/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.seckill.service.Computable;

/**
 * 缓存的实现
 * @author HuHui
 * @version $Id: Memoizer.java, v 0.1 2017年2月3日 下午8:15:14 HuHui Exp $
 */
public class Memoizer<A, V> implements Computable<A, V> {

    private static final Logger               logger = LoggerFactory.getLogger(Memoizer.class);

    private final ConcurrentMap<A, Future<V>> cache  = new ConcurrentHashMap<A, Future<V>>();

    private final Computable<A, V>            c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {

                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };

                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run(); //在这里将调用c.compute
                }

                try {
                    return f.get();
                } catch (Exception e) {
                    cache.remove(arg);
                }

            }
        }
    }
}
