package com.epam.webservice.controller.command;

import com.epam.webservice.server.message.HttpRequest;
import com.epam.webservice.server.message.HttpResponse;
import com.epam.webservice.service.exception.ServiceException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Command {

    String HEADER_CONTENT_TYPE = "Content-type";

    String JSON_TYPE = "application/json";
    String XML_TYPE = "application/xml";

    HttpResponse execute(HttpRequest request, HttpResponse response)
            throws IOException, ServiceException, JAXBException;
}
