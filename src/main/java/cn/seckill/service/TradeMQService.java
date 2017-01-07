/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

import cn.seckill.request.PayRequest;

/**
 * 交易请求发送至MQ接口
 * @author HuHui
 * @version $Id: TradeMQService.java, v 0.1 2017年1月7日 下午3:48:19 HuHui Exp $
 */
public interface TradeMQService {

    boolean pay(PayRequest request);

}
