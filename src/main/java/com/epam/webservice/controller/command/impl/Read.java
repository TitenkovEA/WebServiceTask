package com.epam.webservice.controller.command.impl;

import com.epam.webservice.bean.Book;
import com.epam.webservice.controller.command.Command;
import com.epam.webservice.server.message.HttpRequest;
import com.epam.webservice.server.message.HttpResponse;
import com.epam.webservice.service.BookService;
import com.epam.webservice.service.exception.ServiceException;
import com.epam.webservice.service.factory.ServiceFactory;
import com.epam.webservice.util.converter.JsonConverter;
import com.epam.webservice.util.converter.XmlConverter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Evgeny on 13.04.2017.
 */
public class Read implements Command {
    private static final String HEADER_ACCEPT = "Accept";

    @Override
    public HttpResponse execute(HttpRequest request, HttpResponse response)
            throws IOException, ServiceException, JAXBException {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String acceptHeaderName = request.getHeaders().get(HEADER_ACCEPT);
        List<Book> bookList = bookService.readAll();
        if (acceptHeaderName.equals(JSON_TYPE)) {
            response.addHeader(HEADER_CONTENT_TYPE, JSON_TYPE);
            response.setBody(JsonConverter.toJson(bookList));
        } else if (acceptHeaderName.equals(XML_TYPE)) {
            response.addHeader(HEADER_CONTENT_TYPE, XML_TYPE);
            response.setBody(XmlConverter.toXml(bookList));
        }

        response.setStatusCode(HttpResponse.STATUS_CODE_200_OK);
        return response;
    }
}
