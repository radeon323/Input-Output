package com.luxoft.olshevchenko.echoserver.stream;

import com.luxoft.olshevchenko.bufferedstreams.BufferedInputStream;
import com.luxoft.olshevchenko.bufferedstreams.BufferedOutputStream;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Oleksandr Shevchenko
 */
public class Client {
    private static final int PORT = 3000;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        runMessageSenderAndReceiver();
    }

    private static void runMessageSenderAndReceiver() {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream())) {

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                if (Objects.equals(message, "")) {
                    continue;
                }
                outputStream.write(message.getBytes());
                byte[] buffer = new byte[100];
                int count = inputStream.read(buffer);
                String messageFromServer = new String(buffer, 0, count);
                System.out.println(messageFromServer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
