package com.luxoft.olshevchenko.objectserialization;

import java.io.*;

/**
 * @author Oleksandr Shevchenko
 */
class DataMessageDaoTest extends MessageDaoTest{
    @Override
    protected MessageDao getMessageDao(OutputStream outputStream, InputStream inputStream) {
        return new DataMessageDao(outputStream, inputStream);
    }
}