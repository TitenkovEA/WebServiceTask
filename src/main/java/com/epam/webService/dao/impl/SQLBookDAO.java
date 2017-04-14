package com.epam.webService.dao.impl;

import com.epam.webService.bean.Book;
import com.epam.webService.dao.BookDAO;
import com.epam.webService.dao.exception.DAOException;
import com.epam.webService.dao.util.JDBCResourceManager;
import com.epam.webService.dao.util.connectionpool.ConnectionPool;
import com.epam.webService.dao.util.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class SQLBookDAO implements BookDAO {
    private static final Logger logger = LogManager.getLogger(SQLBookDAO.class);

    private static final String ID_COLUMN = "id";
    private static final String TITLE_COLUMN = "title";
    private static final String AUTHOR_COLUMN = "author";

    private static final String SQL_ADD_BOOK_QUERY = "INSERT INTO book (title, author) VALUES (?, ?)";

    private static final String SQL_GET_ALL_BOOKS_QUERY = "SELECT * FROM book";
    private static final String SQL_UPDATE_BOOK_QUERY = "UPDATE book SET title = ?, author = ? WHERE id = ?";
    private static final String SQL_DELETE_BOOK_QUERY = "DELETE FROM book WHERE id = ?";

    private static final String EXECUTION_ERROR = "Error while executing query!";

    @Override
    public void create(Book book) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.takeConnection()) {
            preparedStatement = connection.prepareStatement(SQL_ADD_BOOK_QUERY);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(EXECUTION_ERROR, e);
        } finally {
            try {
                JDBCResourceManager.closeJdbcResources(preparedStatement, null);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public List<Book> readAll() throws DAOException {
        List<Book> newsList = new LinkedList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Statement statement = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection()) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_ALL_BOOKS_QUERY);
            while (resultSet.next()) {
                newsList.add(getBookFromResultSetRow(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(EXECUTION_ERROR, e);
        } finally {
            try {
                JDBCResourceManager.closeJdbcResources(statement, resultSet);
            } catch (SQLException e) {
                logger.error(e);
            }
        }

        return new ArrayList<>(newsList);
    }

    @Override
    public void update(Book book) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.takeConnection()) {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOK_QUERY);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getId());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(EXECUTION_ERROR, e);
        } finally {
            try {
                JDBCResourceManager.closeJdbcResources(preparedStatement, null);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public void delete(Integer bookId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.takeConnection()){
            preparedStatement = connection.prepareStatement(SQL_DELETE_BOOK_QUERY);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(EXECUTION_ERROR, e);
        } finally {
            try {
                JDBCResourceManager.closeJdbcResources(preparedStatement, null);
            } catch (SQLException e) {
                logger.error(e);
            }
        }

    }

    private Book getBookFromResultSetRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        String title = resultSet.getString(TITLE_COLUMN);
        String author = resultSet.getString(AUTHOR_COLUMN);

        return new Book(id, title, author);
    }
}
