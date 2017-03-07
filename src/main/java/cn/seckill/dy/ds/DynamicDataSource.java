/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.dy.ds;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author HuHui
 * @version $Id: DynamicDataSource.java, v 0.1 2017年3月7日 下午8:20:28 HuHui Exp $
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /** 主库 */
    private DataSource master;

    /** 从库 */
    private DataSource slave;

    @Override
    protected Object determineCurrentLookupKey() {

        return DataSourceHolder.getDatasource();
    }

    public DataSource getMaster() {
        return master;
    }

    public void setMaster(DataSource master) {
        this.master = master;
    }

    public DataSource getSlave() {
        return slave;
    }

    public void setSlave(DataSource slave) {
        this.slave = slave;
    }

}
