package com.epam.webService.server;

import com.epam.webService.controller.Controller;
import com.epam.webService.controller.exception.ControllerException;
import com.epam.webService.server.message.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Yauheni_Tsitsenkou on 4/12/2017.
 */
public class HttpServer {
    private static final Logger logger = LogManager.getLogger(HttpServer.class);

    private static final int PORT = 8888;
    private static final int BACKLOG = 10;
    private static final String LOCAL_HOST = "127.0.0.1";

    private static final String HTTP_VERSION = "HTTP/1.1";

    private static final String HEADER_DATE = "Date";
    private static final String HEADER_SERVER = "Server";
    private static final String SERVER_NAME = "mySocketServer";

    private static final String HEADER_CONTENT_LENGTH = "Content-Length";

    private static final Controller controller = new Controller();
    public static final String NEXT_LINE = "\n";

    private HttpServer() {

    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getByName(LOCAL_HOST));
            controller.initResource();

            while (true) {
                Socket connection = serverSocket.accept();
                new Thread(new SocketHandler(connection)).start();
            }
        } catch (ControllerException | IOException e) {
            logger.error(e);
        }
    }

    private static class SocketHandler implements Runnable {
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;

        private SocketHandler(Socket socket) {
            this.socket = socket;
            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                logger.error(e);
            }
        }

        @Override
        public void run() {
            try {
                String request = readInputRequest();
                HttpResponse response = controller.executeCommand(request);
                writeResponse(response);
            } catch (IOException | ControllerException e) {
                logger.error(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }

        private String readInputRequest() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder request = new StringBuilder();

            String line = reader.readLine();
            while (reader.ready()) {
                request.append(line).append(NEXT_LINE);
                line = reader.readLine();
            }
            return request.toString();
        }

        private void writeResponse(HttpResponse response) throws IOException {
            response.setVersion(HTTP_VERSION);
            response.addHeader(HEADER_SERVER, SERVER_NAME)
                    .addHeader(HEADER_CONTENT_LENGTH, String.valueOf(response.getBody().length()))
                    .addHeader(HEADER_DATE, new Date().toString());

            outputStream.write(response.toString().getBytes());
            outputStream.flush();
        }
    }
}
