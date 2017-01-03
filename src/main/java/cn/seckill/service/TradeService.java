/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

import cn.seckill.request.PayRequest;

/**
 * 交易服务接口
 * @author HuHui
 * @version $Id: TradeService.java, v 0.1 2017年1月3日 下午4:23:54 HuHui Exp $
 */
public interface TradeService {

    /**
     * 支付接口
     * @param request  支付请求参数
     * @return         支付成功返回true,否则返回false
     */
    boolean pay(PayRequest request);

}
