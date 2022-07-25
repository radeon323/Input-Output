package com.luxoft.olshevchenko.echoserver.readerwriter;

import java.io.*;
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
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            while (true) {
                StringBuilder stringBuilder = new StringBuilder();
                char[] buffer = new char[100];
                int count = bufferedReader.read(buffer);
                if (count != -1) {
                    String message = new String(buffer, 0, count);
                    stringBuilder.append("Echo reply: ");
                    stringBuilder.append(message);
                } else {
                    stringBuilder.append("End of InputStream");
                }
                String messageWithEcho = stringBuilder.toString();
                bufferedWriter.write(messageWithEcho);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
