package com.az.ww.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ���ݿ����ӳ�
 *
 * @author yuandl
 */
public class DBConnPool {
    private static DBConnPool instance = null;
    ComboPooledDataSource ds;
    PropertiesReader proReader = new PropertiesReader();

    private DBConnPool() throws Exception {
        this.ds = new ComboPooledDataSource();
        this.ds.setDriverClass(this.proReader.get("jdbc.driverClassName"));
        this.ds.setJdbcUrl(this.proReader.get("jdbc.url"));
        this.ds.setUser(this.proReader.get("jdbc.username"));
        this.ds.setPassword(this.proReader.get("jdbc.password"));
        this.ds.setInitialPoolSize(3);

        this.ds.setMaxPoolSize(10);

        this.ds.setAcquireIncrement(1);

        this.ds.setIdleConnectionTestPeriod(60);

        this.ds.setMaxIdleTime(25000);

        this.ds.setTestConnectionOnCheckout(true);
        this.ds.setTestConnectionOnCheckin(true);

        this.ds.setAcquireRetryAttempts(30);

        this.ds.setAcquireRetryDelay(1000);
        this.ds.setBreakAfterAcquireFailure(true);
    }

    public static final DBConnPool getInstance() {
        if (instance == null) {
            try {
                instance = new DBConnPool();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    public final synchronized Connection getConnection() {
        try {
            return this.ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void finalize() throws Throwable {
        DataSources.destroy(this.ds);

        super.finalize();
    }
}

