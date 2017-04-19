package com.epam.webservice.service;

import com.epam.webservice.service.exception.ServiceException;

/**
 * Created by Evgeny on 15.02.2017.
 */
public interface ResourceManagerService {
    void initDAOResource() throws ServiceException;

    void clearDAOResource() throws ServiceException;
}
