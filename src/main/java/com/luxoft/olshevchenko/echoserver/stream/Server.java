package com.luxoft.olshevchenko.echoserver.stream;

import com.luxoft.olshevchenko.bufferedstreams.BufferedInputStream;
import com.luxoft.olshevchenko.bufferedstreams.BufferedOutputStream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Oleksandr Shevchenko
 */
public class Server {
    private static final int PORT = 3000;

    public static void main(String[] args) {
        runEchoServer();
    }

    private static void runEchoServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
             BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream())) {

            while (true) {
                StringBuilder stringBuilder = new StringBuilder();
                byte[] buffer = new byte[100];
                int count = inputStream.read(buffer);
                if (count != -1) {
                    String message = new String(buffer, 0, count);
                    stringBuilder.append("Echo reply: ");
                    stringBuilder.append(message);
                } else {
                    stringBuilder.append("End of InputStream");
                }
                String messageWithEcho = stringBuilder.toString();
                outputStream.write(messageWithEcho.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
