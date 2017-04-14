package com.epam.webService.dao;


import com.epam.webService.bean.Book;
import com.epam.webService.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public interface BookDAO {
    void create(Book book) throws DAOException;
    List<Book> readAll() throws DAOException;
    void update(Book book) throws DAOException;
    void delete(Integer bookId) throws DAOException;
}
