package com.epam.webService.server;

import com.sun.deploy.net.HttpRequest;

import javax.xml.ws.spi.http.HttpExchange;
import javax.xml.ws.spi.http.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Yauheni_Tsitsenkou on 4/12/2017.
 */
public class HttpServer {
    private static final int PORT = 8080;
    private static final int BACKLOG = 10;

    private HttpServer() {

    }

    public static void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getLocalHost());

            while (true) {
                Socket connection = serverSocket.accept();
                new Thread(new SocketHandler(connection));
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
                e.printStackTrace(); //TODO
            }
        }

        @Override
        public void run() {

        }
    }
}
