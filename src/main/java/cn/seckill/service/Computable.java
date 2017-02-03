/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

/**
 * 
 * @author HuHui
 * @version $Id: Computable.java, v 0.1 2017年2月3日 下午8:11:16 HuHui Exp $
 */
public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;

}
