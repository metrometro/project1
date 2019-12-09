package com.epam.finalproject.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static Logger logger = LogManager.getLogger();
    private final static int MAX_CONNECTION_SIZE = 5;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();
    private LinkedList<ProxyConnection> connectionsList = new LinkedList<ProxyConnection>();

    private ConnectionPool() {
        addConnections();
    }

    private static class ThreadSafeConnectionPool {
        public static ConnectionPool connectionPool = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ThreadSafeConnectionPool.connectionPool;
    }

    public ProxyConnection getConnection() {
        try {
            lock.lock();
            if (connectionsList.isEmpty()) {
                condition.await();
            }
            ProxyConnection proxyConnection = connectionsList.getLast();
            connectionsList.removeLast();
            return proxyConnection;
        } catch (InterruptedException e) {
            logger.error(e);
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void releaseConnection(Connection connection) {
        try {
            lock.lock();
            connectionsList.addFirst((ProxyConnection)connection);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void destroyPool() {
        try {
            lock.lock();
            while (connectionsList.isEmpty()) {
                connectionsList.removeFirst();
            }
        } finally {
            lock.unlock();
        }
    }

    private void addConnections() {
        try {
            lock.lock();
            ConnectionCreator connectionCreator = new ConnectionCreator();
            ProxyConnection proxyConnection = null;
            while (connectionsList.size() < MAX_CONNECTION_SIZE) {
                proxyConnection = connectionCreator.createConnection();
                connectionsList.add(proxyConnection);
            }
        } finally {
            lock.unlock();
        }
    }
}
