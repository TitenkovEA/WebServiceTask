package com.epam.webservice.dao;


import com.epam.webservice.dao.exception.DAOException;

/**
 * Created by Evgeny on 14.02.2017.
 */
public interface DAOResourceManager {
    void initConnectionPool() throws DAOException;

    void clearConnectionPool() throws  DAOException;
}
