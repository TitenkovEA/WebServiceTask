package com.epam.webservice.controller.exception;

/**
 * Created by Evgeny on 14.04.2017.
 */
public class ControllerException extends Exception {
    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }

    public ControllerException(Exception e) {
        super(e);
    }
}
