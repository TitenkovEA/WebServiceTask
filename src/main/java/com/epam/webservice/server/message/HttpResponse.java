package com.epam.webservice.server.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny on 14.04.2017.
 */
public class HttpResponse {
    private static final String SEPARATOR = ": ";
    private static final String NEXT_LINE = "\n";
    private static final String SPACE = " ";

    public static final String STATUS_CODE_200_OK = "200 OK";
    public static final String STATUS_CODE_201_CREATED = "201 Created";
    public static final String STATUS_CODE_400_BAD_REQUEST = "400 Bad Request";
    public static final String STATUS_CODE_500_INTERNAL_SERVER_ERROR = "500 Internal Server Error";

    private String statusCode;
    private String version;
    private Map<String, String> headers;
    private String body;

    public HttpResponse() {
        headers = new HashMap<>();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpResponse addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder headerBuilder = new StringBuilder();
        for (Map.Entry<String, String> header :
                headers.entrySet()) {
            headerBuilder.append(header.getKey()).append(SEPARATOR).append(header.getValue()).append(NEXT_LINE);
        }
        return statusCode + SPACE + version + NEXT_LINE +
                headerBuilder.toString() + NEXT_LINE +
                body;
    }
}
