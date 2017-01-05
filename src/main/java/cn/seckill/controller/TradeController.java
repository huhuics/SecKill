/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import cn.seckill.enums.TradeStatusEnum;
import cn.seckill.request.PayRequest;
import cn.seckill.service.AsynTradeService;
import cn.seckill.service.TradeService;
import cn.seckill.util.LogUtil;
import cn.seckill.util.Money;
import cn.seckill.util.UUIDUtil;

/**
 * 交易Controller
 * @author HuHui
 * @version $Id: TradeController.java, v 0.1 2017年1月3日 下午5:45:02 HuHui Exp $
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);

    @Resource
    private TradeService        tradeService;

    @Resource
    private AsynTradeService    asynTradeService;

    @ResponseBody
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String pay(WebRequest webRequest) {

        LogUtil.info(logger, "收到支付请求");

        String result = TradeStatusEnum.FAILED.getCode();

        Map<String, String> paraMap = null;

        try {
            paraMap = getParameters(webRequest);

            LogUtil.info(logger, "支付报文原始参数paraMap={0}", paraMap);

            PayRequest payRequest = buildPayRequest(paraMap);
            TradeStatusEnum payRet = tradeService.pay(payRequest);

            result = payRet.getCode();
        } catch (Exception e) {
            LogUtil.error(e, logger, "支付失败,paraMap={0}", paraMap);
            result = TradeStatusEnum.FAILED.getCode();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "asynPay", method = RequestMethod.POST)
    public String asynPay(WebRequest webRequest) {
        LogUtil.info(logger, "收到异步支付请求");

        String result = TradeStatusEnum.FAILED.getCode();

        Map<String, String> paraMap = null;

        try {
            paraMap = getParameters(webRequest);

            LogUtil.info(logger, "异步支付报文原始参数paraMap={0}", paraMap);

            PayRequest payRequest = buildPayRequest(paraMap);
            Boolean payRet = asynTradeService.pay(payRequest);

            result = payRet.toString();
        } catch (Exception e) {
            LogUtil.error(e, logger, "支付失败,paraMap={0}", paraMap);
            result = Boolean.FALSE.toString();
        }

        return result;
    }

    /**
     * 获取所有请求参数，并用TreeMap保存
     * @param request
     * @return          返回的Map对象中，参数名是按照字典顺序排序
     */
    private Map<String, String> getParameters(WebRequest request) {

        Map<String, String> paraMap = new HashMap<String, String>();

        Iterator<String> nameItr = request.getParameterNames();

        if (nameItr != null) {
            while (nameItr.hasNext()) {
                String paraName = nameItr.next();
                String paraValue = request.getParameter(paraName);
                paraMap.put(paraName, paraValue);
            }
        }

        return paraMap;
    }

    private PayRequest buildPayRequest(Map<String, String> paraMap) {

        PayRequest payRequest = new PayRequest();
        payRequest.setBuyerId(Thread.currentThread().getName());

        String goodsIdStr = paraMap.get("goodsId");
        if (StringUtils.isNotBlank(goodsIdStr)) {
            payRequest.setGoodsId(Long.parseLong(goodsIdStr));
        }

        payRequest.setGoodsName(paraMap.get("goodsName"));
        payRequest.setOrderNo(UUIDUtil.geneId());

        String tatalAmountStr = paraMap.get("totalAmount");
        if (StringUtils.isNotBlank(tatalAmountStr)) {
            payRequest.setTotalAmount(new Money(Double.parseDouble(tatalAmountStr)));
        }

        return payRequest;
    }

}
