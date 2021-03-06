package com.epam.webservice.controller;

import com.epam.webservice.controller.command.Command;
import com.epam.webservice.controller.exception.ControllerException;
import com.epam.webservice.server.message.HttpRequest;
import com.epam.webservice.server.message.HttpResponse;
import com.epam.webservice.service.ResourceManagerService;
import com.epam.webservice.service.exception.ServiceException;
import com.epam.webservice.service.factory.ServiceFactory;
import com.epam.webservice.util.parser.HttpRequestParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public final class Controller {
    private static final CommandProvider provider = new CommandProvider();

    public HttpResponse executeCommand(String httpRequest) throws ControllerException {
        HttpResponse response = new HttpResponse();
        try {
            HttpRequest request = HttpRequestParser.parse(httpRequest);
            Command command = provider.getCommand(request.getMethod());
            response = command.execute(request, response);
        } catch (IOException | JAXBException e) {
            response.setStatusCode(HttpResponse.STATUS_CODE_400_BAD_REQUEST);
            throw new ControllerException(e);
        } catch (ServiceException e) {
            response.setStatusCode(HttpResponse.STATUS_CODE_500_INTERNAL_SERVER_ERROR);
            throw new ControllerException(e);
        }

        return response;
    }

    public void initResource() throws ControllerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResourceManagerService resourceManagerService = serviceFactory.getResourceManagerService();

        try {
            resourceManagerService.initDAOResource();
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    public void clearResource() throws ControllerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResourceManagerService resourceManagerService = serviceFactory.getResourceManagerService();

        try {
            resourceManagerService.clearDAOResource();
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
