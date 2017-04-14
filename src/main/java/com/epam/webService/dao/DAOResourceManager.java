package com.epam.webService.dao;


import com.epam.webService.dao.exception.DAOException;

/**
 * Created by Evgeny on 14.02.2017.
 */
public interface DAOResourceManager {
    void initConnectionPool() throws DAOException;

    void clearConnectionPool() throws  DAOException;
}
