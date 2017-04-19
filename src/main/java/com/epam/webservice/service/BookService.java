package com.epam.webservice.service;


import com.epam.webservice.bean.Book;
import com.epam.webservice.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public interface BookService {
    void create(Book book) throws ServiceException;
    List<Book> readAll() throws ServiceException;
    void update(Book book) throws ServiceException;
    void delete(Book book) throws ServiceException;
}
