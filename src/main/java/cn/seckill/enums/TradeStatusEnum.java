/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 支付结果枚举
 * @author HuHui
 * @version $Id: TradeStatusEnum.java, v 0.1 2017年1月3日 下午9:01:28 HuHui Exp $
 */
public enum TradeStatusEnum {

    SUCCESS("success", "交易成功"),

    FAILED("failed", "交易失败"),

    BUSY("busy", "系统繁忙"),

    ;

    /** 枚举代码 */
    private String code;

    /** 枚举值 */
    private String desc;

    private TradeStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**

     * 根据代码获取枚举，如果code对应的枚举不存在，则返回null

     * @param code 枚举代码

     * @return     对应的枚举对象

     */
    public static TradeStatusEnum getByCode(String code) {
        for (TradeStatusEnum eachValue : TradeStatusEnum.values()) {
            if (StringUtils.equals(code, eachValue.getCode())) {
                return eachValue;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
