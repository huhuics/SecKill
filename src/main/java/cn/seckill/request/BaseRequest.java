/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.request;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 请求基础类
 * @author HuHui
 * @version $Id: BaseRequest.java, v 0.1 2017年1月3日 下午4:26:55 HuHui Exp $
 */
public abstract class BaseRequest implements Serializable {

    /**  */
    private static final long serialVersionUID = -7551345343414077904L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public abstract void validate();

}
