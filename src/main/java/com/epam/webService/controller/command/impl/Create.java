package com.epam.webService.controller.command.impl;

import com.epam.webService.controller.command.Command;
import com.epam.webService.service.BookService;
import com.epam.webService.service.exception.ServiceException;
import com.epam.webService.service.factory.ServiceFactory;
import com.epam.webService.util.converter.JsonConverter;
import com.epam.webService.util.converter.XmlConverter;
import com.epam.webService.util.httpParser.HttpRequest;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Evgeny on 13.04.2017.
 */
public class Create implements Command {

    @Override
    public String execute(HttpRequest request) throws IOException, ServiceException, JAXBException {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        String response = null;

        String contentType = request.getHeaders().get(HTTP_CONTENT_TYPE);
        if (contentType.equals(JSON_TYPE)) {
            bookService.create(JsonConverter.toJavaObj(request.getBody()));
            response = HTTP_HEADER_CONTENT_TYPE_APPLICATION_JSON;
        } else if (contentType.equals(XML_TYPE)) {
            bookService.create(XmlConverter.toJavaObj(request.getBody()));
            response = HTTP_HEADER_CONTENT_TYPE_APPLICATION_XML;
        }

        return response;
    }
}
