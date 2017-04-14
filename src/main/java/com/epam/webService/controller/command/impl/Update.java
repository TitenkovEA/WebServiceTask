package com.epam.webService.controller.command.impl;

import com.epam.webService.controller.command.Command;
import com.epam.webService.server.message.HttpRequest;
import com.epam.webService.server.message.HttpResponse;
import com.epam.webService.service.BookService;
import com.epam.webService.service.exception.ServiceException;
import com.epam.webService.service.factory.ServiceFactory;
import com.epam.webService.util.converter.JsonConverter;
import com.epam.webService.util.converter.XmlConverter;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Evgeny on 13.04.2017.
 */
public class Update implements Command {

    @Override
    public HttpResponse execute(HttpRequest request, HttpResponse response)
            throws IOException, ServiceException, JAXBException {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String contentType = request.getHeaders().get(HEADER_CONTENT_TYPE);
        if (contentType.equals(JSON_TYPE)) {
            bookService.update(JsonConverter.toJavaObj(request.getBody()));
        } else if (contentType.equals(XML_TYPE)) {
            bookService.update(XmlConverter.toJavaObj(request.getBody()));
        }

        response.setStatusCode(HttpResponse.STATUS_CODE_200_OK);
        return response;
    }
}
