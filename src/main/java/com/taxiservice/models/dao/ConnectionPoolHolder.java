package com.taxiservice.models.dao;

import com.taxiservice.utils.BundleManagers.ConfigurationManager;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(ConfigurationManager.getProperty("jdbc_driver"));
                    ds.setUrl(ConfigurationManager.getProperty("db_url"));
                    ds.setUsername(ConfigurationManager.getProperty("db_user"));
                    ds.setPassword(ConfigurationManager.getProperty("db_password"));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    //ds.setDefaultAutoCommit(false);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }

}
