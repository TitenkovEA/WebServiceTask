package com.epam.webService.dao.impl;

import com.epam.webService.dao.DAOResourceManager;
import com.epam.webService.dao.exception.DAOException;
import com.epam.webService.dao.util.connectionpool.ConnectionPool;
import com.epam.webService.dao.util.connectionpool.exception.ConnectionPoolException;

/**
 * Created by Evgeny on 14.02.2017.
 */
public class DAOResourceManagerImpl implements DAOResourceManager {
    private static final String CONNECTION_POOL_INIT_ERROR =
            "ConnectionPoolException while init connection pool resource.";
    private static final String CONNECTION_POOL_CLEAR_ERROR =
            "ConnectionPoolException while clear connection pool resource.";

    public void initConnectionPool() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            throw new DAOException(CONNECTION_POOL_INIT_ERROR, e);
        }
    }

    public void clearConnectionPool() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.clearConnectionQueue();
        } catch (ConnectionPoolException e) {
            throw new DAOException(CONNECTION_POOL_CLEAR_ERROR, e);
        }
    }
}
