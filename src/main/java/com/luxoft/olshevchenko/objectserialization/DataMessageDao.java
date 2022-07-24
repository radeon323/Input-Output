package com.luxoft.olshevchenko.objectserialization;

import com.luxoft.olshevchenko.objectserialization.entity.Message;

import java.io.*;
import java.util.Date;

/**
 * @author Oleksandr Shevchenko
 */
public class DataMessageDao implements MessageDao {
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;

    public DataMessageDao(OutputStream outputStream, InputStream inputStream) {
        this.dataOutputStream = new DataOutputStream(outputStream);
        this.dataInputStream = new DataInputStream(inputStream);
    }

    @Override
    public void save(Message message) throws IOException {
        long date = message.getDate().getTime();
        String textMessage = message.getMessage();
        int textMessageLength = textMessage.length();
        double amount = message.getAmount();

        dataOutputStream.writeLong(date);
        dataOutputStream.writeInt(textMessageLength);
        dataOutputStream.writeBytes(textMessage);
        dataOutputStream.writeDouble(amount);
        dataOutputStream.close();
    }

    @Override
    public Message load() throws IOException {
        Date date = new Date(dataInputStream.readLong());
        int textMessageLength = dataInputStream.readInt();
        byte[] messageBytesArray = new byte[textMessageLength];
        dataInputStream.readFully(messageBytesArray);
        double amount = dataInputStream.readDouble();

        Message message = new Message(date, new String(messageBytesArray), amount);
        dataInputStream.close();
        return message;
    }
}
