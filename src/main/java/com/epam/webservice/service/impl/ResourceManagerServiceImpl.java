package com.epam.webservice.service.impl;

import com.epam.webservice.dao.DAOResourceManager;
import com.epam.webservice.dao.exception.DAOException;
import com.epam.webservice.dao.factory.DAOFactory;
import com.epam.webservice.service.ResourceManagerService;
import com.epam.webservice.service.exception.ServiceException;

/**
 * Created by Evgeny on 15.02.2017.
 */
public class ResourceManagerServiceImpl implements ResourceManagerService {
    private static final String DAO_RESOURCE_INIT_ERROR = "DAOException while init DAO resource.";
    private static final String DAO_RESOURCE_CLEAR_ERROR = "DAOException while clear DAO resource.";

    public void initDAOResource() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOResourceManager daoResourceManager = daoFactory.getDaoResourceManager();
        try {
            daoResourceManager.initConnectionPool();
        } catch (DAOException e) {
            throw new ServiceException(DAO_RESOURCE_INIT_ERROR, e);
        }
    }

    public void clearDAOResource() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOResourceManager daoResourceManager = daoFactory.getDaoResourceManager();
        try {
            daoResourceManager.clearConnectionPool();
        } catch (DAOException e) {
            throw new ServiceException(DAO_RESOURCE_CLEAR_ERROR, e);
        }
    }
}
