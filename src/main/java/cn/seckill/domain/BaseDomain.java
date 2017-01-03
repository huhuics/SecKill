/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 基础domain对象
 * @author HuHui
 * @version $Id: BaseDomain.java, v 0.1 2017年1月3日 下午9:29:47 HuHui Exp $
 */
public class BaseDomain implements Serializable {

    /**  */
    private static final long serialVersionUID = -9002325507053444620L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
