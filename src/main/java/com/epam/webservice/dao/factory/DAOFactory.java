package com.epam.webservice.dao.factory;

import com.epam.webservice.dao.BookDAO;
import com.epam.webservice.dao.DAOResourceManager;
import com.epam.webservice.dao.impl.DAOResourceManagerImpl;
import com.epam.webservice.dao.impl.SQLBookDAO;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final BookDAO sqlBookDAO = new SQLBookDAO();
    private final DAOResourceManager daoResourceManager = new DAOResourceManagerImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public BookDAO getBookDAO() {
        return sqlBookDAO;
    }

    public DAOResourceManager getDaoResourceManager() {
        return daoResourceManager;
    }
}
