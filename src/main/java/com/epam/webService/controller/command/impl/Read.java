package com.epam.webService.controller.command.impl;

import com.epam.webService.bean.Book;
import com.epam.webService.controller.command.Command;
import com.epam.webService.service.BookService;
import com.epam.webService.service.exception.ServiceException;
import com.epam.webService.service.factory.ServiceFactory;
import com.epam.webService.util.converter.JsonConverter;
import com.epam.webService.util.converter.XmlConverter;
import com.epam.webService.util.httpParser.HttpRequest;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Evgeny on 13.04.2017.
 */
public class Read implements Command {

    private static final String EMPTY_LINE = "\n";

    @Override
    public String execute(HttpRequest request) throws IOException, ServiceException, JAXBException {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        StringBuilder responseBuilder = new StringBuilder();

        String contentType = request.getHeaders().get(HTTP_CONTENT_TYPE);
        List<Book> bookList = bookService.readAll();
        if (contentType.equals(JSON_TYPE)) {
            responseBuilder.append(HTTP_HEADER_CONTENT_TYPE_APPLICATION_JSON);
            responseBuilder.append(EMPTY_LINE);
            responseBuilder.append(JsonConverter.toJson(bookList));
        } else if (contentType.equals(XML_TYPE)) {
            responseBuilder.append(HTTP_HEADER_CONTENT_TYPE_APPLICATION_XML);
            responseBuilder.append(EMPTY_LINE);
            responseBuilder.append(XmlConverter.toXml(bookList));
        }

        return responseBuilder.toString();
    }
}
