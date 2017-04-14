package com.epam.webService.server;

import com.epam.webService.controller.Controller;
import com.epam.webService.controller.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Yauheni_Tsitsenkou on 4/12/2017.
 */
public class HttpServer {
    private static final Logger logger = LogManager.getLogger(HttpServer.class);

    private static final int PORT = 8080;
    private static final int BACKLOG = 10;

    private static final String HTTP_VERSION = "HTTP/1.1";

    private static final String CODE_SUCCESS = "200 OK";
    private static final String CODE_SERVER_ERROR = "500 SERVER ERROR";

    private static final String NEXT_LINE = "\n";

    private static final String HEADER_DATE = "Date: ";
    private static final String HEADER_SERVER = "Server: MySocketServer\n";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length: ";

    private static final Controller controller = new Controller();

    private HttpServer() {

    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getLocalHost());
            controller.initResource();

            while (true) {
                Socket connection = serverSocket.accept();
                new Thread(new SocketHandler(connection));
            }
        } catch (ControllerException | IOException e) {
            logger.error(e);
        }
    }

    private static class SocketHandler implements Runnable {
        private Socket clientSocket;
        private InputStream inputStream;
        private OutputStream outputStream;

        private SocketHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
            } catch (IOException e) {
                logger.error(e);
            }
        }

        @Override
        public void run() {
            try {
                String request = readInputRequest();
                String response = controller.executeCommand(request);
                writeResponse(CODE_SUCCESS, response);
            } catch (IOException | ControllerException e) {
                logger.error(e);
                try {
                    writeResponse(CODE_SERVER_ERROR, Arrays.toString(e.getStackTrace()));
                } catch (IOException e1) {
                    logger.error(e);
                }
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }

        private String readInputRequest() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder request = new StringBuilder();

            String line = reader.readLine();
            while (line != null) {
                request.append(line);
                line = reader.readLine();
            }

            return request.toString();
        }

        private void writeResponse(String code, String response) throws IOException {
            String httpResponse =
                    HTTP_VERSION + code + NEXT_LINE +
                    HEADER_DATE + new Date().toString() + NEXT_LINE +
                    HEADER_SERVER +
                    HEADER_CONTENT_LENGTH + response.length() + NEXT_LINE +
                    response;

            outputStream.write(httpResponse.getBytes());
            outputStream.flush();
        }
    }
}
