package com.epam.webService.util.httpParser;

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

    public static HttpRequest parse(String httpRequest) throws IOException { //TODO
        BufferedReader reader = new BufferedReader(new StringReader(httpRequest));
        HttpRequest request = new HttpRequest();

        /* Parse first http request line */
        String requestLine = reader.readLine();
        String[] requestLineParams = requestLine.split(REQUEST_LINE_DELIMITER);
        request.setMethod(requestLineParams[0]);
        request.setUri(requestLineParams[1]);
        request.setVersion(requestLineParams[2]);

        /* Parse http request headers */
        Map<String, String> headers = new HashMap<>();
        String header = reader.readLine();
        String[] headerParams = null;
        String headerName = null;
        String headerValue = null;
        while (header != null && header.length() > 0) {
            headerParams = header.split(HEADER_DELIMITER);
            headerName = headerParams[0];
            headerValue = headerParams[1].substring(1);
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
