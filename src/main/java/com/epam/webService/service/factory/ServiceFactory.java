package com.epam.webService.service.factory;


import com.epam.webService.service.BookService;
import com.epam.webService.service.ResourceManagerService;
import com.epam.webService.service.impl.BookServiceImpl;
import com.epam.webService.service.impl.ResourceManagerServiceImpl;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final BookService implBookService = new BookServiceImpl();
    private final ResourceManagerService resourceManagerService = new ResourceManagerServiceImpl();

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public BookService getBookService() {
        return implBookService;
    }

    public ResourceManagerService getResourceManagerService() {
        return resourceManagerService;
    }
}
