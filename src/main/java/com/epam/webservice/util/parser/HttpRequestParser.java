package com.epam.webservice.util.parser;

import com.epam.webservice.server.message.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny on 14.04.2017.
 */
public class HttpRequestParser {
    private static final String REQUEST_LINE_DELIMITER = "\\s";
    private static final String HEADER_DELIMITER = ":";
    private static final String NEW_LINE = "\n";
    private static final int METHOD_INDEX = 0;
    private static final int URI_INDEX = 1;
    private static final int HTTP_VERSION_INDEX = 2;
    private static final int HEADER_NAME_INDEX = 0;
    private static final int HEADER_VALUE_INDEX = 1;
    private static final int BEGIN_INDEX = 1;

    public static HttpRequest parse(String httpRequest) throws IOException { //TODO
        BufferedReader reader = new BufferedReader(new StringReader(httpRequest));
        HttpRequest request = new HttpRequest();

        /* Parse first http request line */
        String requestLine = reader.readLine();
        String[] requestLineParams = requestLine.split(REQUEST_LINE_DELIMITER);
        request.setMethod(requestLineParams[METHOD_INDEX]);
        request.setUri(requestLineParams[URI_INDEX]);
        request.setVersion(requestLineParams[HTTP_VERSION_INDEX]);

        /* Parse http request headers */
        Map<String, String> headers = new HashMap<>();
        String header = reader.readLine();
        String[] headerParams = null;
        String headerName = null;
        String headerValue = null;
        while (header != null && header.length() > 0) {
            headerParams = header.split(HEADER_DELIMITER);
            headerName = headerParams[HEADER_NAME_INDEX];
            headerValue = headerParams[HEADER_VALUE_INDEX].substring(BEGIN_INDEX);
            headers.put(headerName, headerValue);
            header = reader.readLine();
        }
        request.setHeaders(headers);

        /* Parse http request body */
        StringBuilder body = new StringBuilder();
        String bodyLine = reader.readLine();
        while (bodyLine != null) {
            body.append(bodyLine).append(NEW_LINE);
            bodyLine = reader.readLine();
        }
        request.setBody(body.toString());

        return request;
    }
}
