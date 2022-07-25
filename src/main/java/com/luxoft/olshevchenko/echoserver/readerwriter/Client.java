package com.luxoft.olshevchenko.echoserver.readerwriter;

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
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                if (Objects.equals(message, "")) {
                    continue;
                }
                bufferedWriter.write(message);
                bufferedWriter.flush();
                char[] buffer = new char[100];
                int count = bufferedReader.read(buffer);
                String messageFromServer = new String(buffer, 0, count);
                System.out.println(messageFromServer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
