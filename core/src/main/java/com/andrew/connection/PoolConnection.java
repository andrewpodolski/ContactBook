package com.andrew.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class PoolConnection {
    private static final Logger logger = Logger.getLogger(PoolConnection.class);

    private static PoolConnection poolInstance;
    private static BasicDataSource basicDataSource = new BasicDataSource();

    private PoolConnection() {
        try {
            assert false;
            InputStream inputStream = PoolConnection.class.getClassLoader().getResourceAsStream("database.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            basicDataSource.setDriverClassName(properties.getProperty(("jdbc.driver")));
            basicDataSource.setUrl(properties.getProperty("jdbc.url"));
            basicDataSource.setUsername(properties.getProperty("jdbc.login"));
            basicDataSource.setPassword(properties.getProperty("jdbc.password"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
            e.printStackTrace();
        }
    }

    public static PoolConnection getInstance() {
        if (poolInstance == null) {
            poolInstance = new PoolConnection();
        }
        return poolInstance;
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
    public static boolean isConnectionExists() {
        final String CHECK_SQL_QUERY = "SELECT 1";
        boolean isConnected = false;
        try {
            final PreparedStatement statement = PoolConnection.getInstance().getConnection().prepareStatement(CHECK_SQL_QUERY);
            isConnected = true;
        } catch (SQLException | NullPointerException e) {
            logger.error(e);
        }
        return isConnected;
    }

}