package com.luxoft.olshevchenko.objectserialization;

import com.luxoft.olshevchenko.bufferedstreams.BufferedInputStream;
import com.luxoft.olshevchenko.bufferedstreams.BufferedOutputStream;
import com.luxoft.olshevchenko.objectserialization.entity.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class MessageDaoTest {
    private final Message message = new Message(new Date(), "Hello", 10);
    private static final String TEST_FILE_PATH = "src/test/resources/testFile.txt";
    OutputStream outputStream;
    InputStream inputStream;
    private MessageDao messageDao;

    @BeforeEach
    void before() throws IOException {
        File file = new File(TEST_FILE_PATH);
        if (!file.exists()) {
            new File(TEST_FILE_PATH).createNewFile();
        }
        outputStream = new BufferedOutputStream(new FileOutputStream(TEST_FILE_PATH));
        inputStream = new BufferedInputStream(new FileInputStream(TEST_FILE_PATH));
        messageDao = getMessageDao(outputStream, inputStream);
    }

    @AfterEach
    void after() throws IOException {
        File file = new File(TEST_FILE_PATH);
        file.delete();
        outputStream.close();
        inputStream.close();
    }

    protected abstract MessageDao getMessageDao(OutputStream outputStream, InputStream inputStream) throws IOException;

    @Test
    void testSaveAndLoad() throws IOException, ClassNotFoundException {
        messageDao.save(message);
        Message messageFromFile = messageDao.load();
        assertEquals(message.getDate(), messageFromFile.getDate());
        assertEquals(message.getMessage(), messageFromFile.getMessage());
        assertEquals(message.getAmount(), messageFromFile.getAmount());
    }
}
