package com.epam.webService.controller;

import com.epam.webService.controller.command.Command;
import com.epam.webService.controller.exception.ControllerException;
import com.epam.webService.service.ResourceManagerService;
import com.epam.webService.service.exception.ServiceException;
import com.epam.webService.service.factory.ServiceFactory;
import com.epam.webService.util.httpParser.HttpRequest;
import com.epam.webService.util.httpParser.HttpRequestParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public final class Controller {
    private static final CommandProvider provider = new CommandProvider();
    private static final String COMMAND_NAME = "COMMAND_NAME";

    public String executeCommand(String httpRequest) throws ControllerException {
        String response = null;
        try {
            HttpRequest request = HttpRequestParser.parse(httpRequest);
            Command command = provider.getCommand(request.getMethod());
            response = command.execute(request);
        } catch (IOException | ServiceException | JAXBException e) {
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
