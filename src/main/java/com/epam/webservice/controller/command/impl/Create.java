package com.epam.webservice.controller.command.impl;

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

/**
 * Created by Evgeny on 13.04.2017.
 */
public class Create implements Command {

    @Override
    public HttpResponse execute(HttpRequest request, HttpResponse response)
            throws IOException, ServiceException, JAXBException {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String contentType = request.getHeaders().get(HEADER_CONTENT_TYPE);
        if (contentType.equals(JSON_TYPE)) {
            bookService.create(JsonConverter.toJavaObj(request.getBody()));
        } else if (contentType.equals(XML_TYPE)) {
            bookService.create(XmlConverter.toJavaObj(request.getBody()));
        }

        response.setStatusCode(HttpResponse.STATUS_CODE_201_CREATED);
        return response;
    }
}
