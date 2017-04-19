package com.epam.webservice.service.impl;


import com.epam.webservice.bean.Book;
import com.epam.webservice.dao.BookDAO;
import com.epam.webservice.dao.exception.DAOException;
import com.epam.webservice.dao.factory.DAOFactory;
import com.epam.webservice.service.BookService;
import com.epam.webservice.service.exception.ServiceException;

import java.util.List;


/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class BookServiceImpl implements BookService {
    private static final String INCORRECT_BOOK_ERROR = "Incorrect book. NullPointer error!";

    @Override
    public void create(Book book) throws ServiceException {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) {
            throw new ServiceException(INCORRECT_BOOK_ERROR);
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        try {
            BookDAO bookDAO = daoFactory.getBookDAO();
            bookDAO.create(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> readAll() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Book> bookList = null;
        try {
            BookDAO bookDAO = daoFactory.getBookDAO();
            bookList = bookDAO.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return bookList;
    }

    @Override
    public void update(Book book) throws ServiceException {
        if (book == null || book.getId() == null || book.getTitle() == null || book.getAuthor() == null) {
            throw new ServiceException(INCORRECT_BOOK_ERROR);
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        try {
            BookDAO bookDAO = daoFactory.getBookDAO();
            bookDAO.update(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Book book) throws ServiceException {
        if (book == null || book.getId() == null) {
            throw new ServiceException(INCORRECT_BOOK_ERROR);
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        try {
            BookDAO bookDAO = daoFactory.getBookDAO();
            bookDAO.delete(book.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
