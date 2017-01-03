/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.request;

import cn.seckill.util.AssertUtil;
import cn.seckill.util.Money;

/**
 * 交易请求类
 * @author HuHui
 * @version $Id: PayRequest.java, v 0.1 2017年1月3日 下午4:26:28 HuHui Exp $
 */
public class PayRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -987971031892656631L;

    private String            orderNo;

    private Long              goodsId;

    private String            goodsName;

    private String            buyerId;

    private Money             totalAmount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public void validate() {

        AssertUtil.assertNotBlank(orderNo, "订单号不能为空");

        AssertUtil.assertNotBlank(goodsName, "商品名称不能为空");

        AssertUtil.assertNotBlank(buyerId, "购买人ID不能为空");

        AssertUtil.assertNotNull(goodsId, "商品ID不能为空");

        AssertUtil.assertNotNull(totalAmount, "商品总价不能为空");

    }

}
