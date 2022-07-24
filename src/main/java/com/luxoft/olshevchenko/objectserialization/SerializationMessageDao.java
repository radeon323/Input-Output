package com.luxoft.olshevchenko.objectserialization;

import com.luxoft.olshevchenko.objectserialization.entity.Message;

import java.io.*;

/**
 * @author Oleksandr Shevchenko
 */
public class SerializationMessageDao implements MessageDao {
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    public SerializationMessageDao(OutputStream outputStream, InputStream inputStream) throws IOException {
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.objectInputStream = new ObjectInputStream(inputStream);
    }

    @Override
    public void save(Message message) throws IOException {
        objectOutputStream.writeObject(message);
        objectOutputStream.close();
    }

    @Override
    public Message load() throws IOException, ClassNotFoundException {
        Message message = (Message) objectInputStream.readObject();
        objectInputStream.close();
        return message;
    }
}
