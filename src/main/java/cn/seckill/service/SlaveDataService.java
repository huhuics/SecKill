/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.service;

import cn.seckill.domain.Goods;

/**
 * 
 * @author HuHui
 * @version $Id: SlaveDataService.java, v 0.1 2017年3月7日 下午9:09:10 HuHui Exp $
 */
public interface SlaveDataService {

    /**
     * 从历史库读取数据
     * @param id
     * @return
     */
    Goods getGoodsFromSlave(Long id);

}
