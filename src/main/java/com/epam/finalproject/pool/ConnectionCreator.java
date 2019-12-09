package com.epam.finalproject.pool;

import com.epam.finalproject.manager.JdbcManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private static Logger logger = LogManager.getLogger();
    private final static String DRIVER = "path.driver";
    private final static String PATH_CONNECTION = "path.connection";
    private final static String USER = "user";
    private final static String PASSWORD = "password";

    ConnectionCreator() {
    }

    public ProxyConnection createConnection() {
        String driver = null;
        String pathConnection = null;
        String user = null;
        String password = null;
        try {
            driver = JdbcManager.getProperty(DRIVER);
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        Connection connection = null;
        try {
            pathConnection = JdbcManager.getProperty(PATH_CONNECTION);
            user = JdbcManager.getProperty(USER);
            password = JdbcManager.getProperty(PASSWORD);
            connection = DriverManager.getConnection(pathConnection,user,password);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        ProxyConnection proxyConnection = new ProxyConnection(connection);
        return proxyConnection;
    }
}