package com.epam.webService.controller.command;

import com.epam.webService.service.exception.ServiceException;
import com.epam.webService.util.httpParser.HttpRequest;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Command {

    String HTTP_CONTENT_TYPE = "Content-type";

    String JSON_TYPE = "application/json";
    String XML_TYPE = "application/xml";

    String HTTP_HEADER_CONTENT_TYPE_APPLICATION_JSON = "Content-type: application/json\n";
    String HTTP_HEADER_CONTENT_TYPE_APPLICATION_XML = "Content-type: application/xml\n";

    String execute(HttpRequest request) throws IOException, ServiceException, JAXBException;
}
