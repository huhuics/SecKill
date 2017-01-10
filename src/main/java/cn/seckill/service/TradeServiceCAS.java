/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

import cn.seckill.enums.TradeStatusEnum;
import cn.seckill.request.PayRequest;

/**
 * 公平锁交易实现接口
 * @author HuHui
 * @version $Id: TradeServiceCAS.java, v 0.1 2017年1月10日 下午7:48:20 HuHui Exp $
 */
public interface TradeServiceCAS {

    /**
     * 支付接口
     * @param request  支付请求参数
     * @return         支付结果枚举
     */
    TradeStatusEnum pay(PayRequest request);

}
