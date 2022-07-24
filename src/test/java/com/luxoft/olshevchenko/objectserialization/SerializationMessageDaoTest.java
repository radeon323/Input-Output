package com.luxoft.olshevchenko.objectserialization;

import java.io.*;

/**
 * @author Oleksandr Shevchenko
 */
class SerializationMessageDaoTest extends MessageDaoTest{
    @Override
    protected MessageDao getMessageDao(OutputStream outputStream, InputStream inputStream) throws IOException {
        return new SerializationMessageDao(outputStream, inputStream);
    }
}