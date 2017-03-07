/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.seckill.dy.ds;

/**
 * 
 * @author HuHui
 * @version $Id: DataSourceHolder.java, v 0.1 2017年3月7日 下午8:26:44 HuHui Exp $
 */
public class DataSourceHolder {

    private static final String              MASTER     = "master";

    private static final String              SLAVE      = "slave";

    private static final ThreadLocal<String> dataSource = new ThreadLocal<String>();

    /**
     * 设置数据源
     */
    private static void setDataSource(String dataSourceKey) {
        dataSource.set(dataSourceKey);
    }

    public static String getDatasource() {
        return dataSource.get();
    }

    public static void setMaster() {
        setDataSource(MASTER);
    }

    public static void setSlave() {
        setDataSource(SLAVE);
    }

    public static boolean isMaster() {
        return getDatasource() == MASTER;
    }

    public static boolean isSlave() {
        return getDatasource() == SLAVE;
    }

    public static void clearDataSource() {
        dataSource.remove();
    }

}
