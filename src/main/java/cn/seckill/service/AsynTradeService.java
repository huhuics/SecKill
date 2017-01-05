/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

import cn.seckill.request.PayRequest;

/**
 * 异步交易接口
 * @author HuHui
 * @version $Id: AsynTradeService.java, v 0.1 2017年1月5日 上午10:38:48 HuHui Exp $
 */
public interface AsynTradeService {

    /**
     * 支付接口
     * @param request  支付请求参数
     * @return         支付结果
     */
    Boolean pay(PayRequest request) throws Exception;

}
