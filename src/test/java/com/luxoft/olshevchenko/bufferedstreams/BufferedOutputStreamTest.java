package com.luxoft.olshevchenko.bufferedstreams;

import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferedOutputStreamTest {
    private final String content = "Hello";
    private final String TEST_FILE_PATH = "src/test/resources/testFile.txt";
    BufferedOutputStream bufferedOutputStream;

    @BeforeEach
    void before() throws IOException {
        new File(TEST_FILE_PATH).createNewFile();
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(TEST_FILE_PATH));
    }

    @AfterEach
    void after() {
        new File(TEST_FILE_PATH).delete();
    }

    @Test
    @DisplayName("Test Write method without parameters")
    void testWriteWithoutParam() throws IOException {
        bufferedOutputStream.write('H');
        bufferedOutputStream.write('e');
        bufferedOutputStream.flush();
        bufferedOutputStream.write('l');
        bufferedOutputStream.write('l');
        bufferedOutputStream.write('o');
        bufferedOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        byte[] actual = new byte[5];
        fileInputStream.read(actual);
        assertEquals("Hello",new String(actual));
        assertEquals(-1,fileInputStream.read());
    }

    @Test
    @DisplayName("Test Write method with parameters")
    void testWriteWithParam() throws IOException {
        bufferedOutputStream.write(content.getBytes());
        bufferedOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        byte[] actual = new byte[5];
        fileInputStream.read(actual);
        assertEquals("Hello",new String(actual));
        assertEquals(-1,fileInputStream.read());
    }

    @Test
    @DisplayName("Test Write method with parameters and offset")
    void testWriteWithParamAndOffset() throws IOException {
        bufferedOutputStream.write(content.getBytes(),1,4);
        bufferedOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        byte[] actual = new byte[4];
        fileInputStream.read(actual);
        assertEquals("ello",new String(actual));
        assertEquals(-1,fileInputStream.read());
    }

    @Test
    @DisplayName("Test Write method with parameters and offset if content bigger than buffer")
    void testWriteWithParamAndOffsetIfContentBiggerThanBuffer() throws IOException {
        String content = "Hello java!";
        bufferedOutputStream.write(content.getBytes(),5,6);
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        byte[] actual = new byte[6];
        fileInputStream.read(actual);
        assertEquals(" java!",new String(actual));
        assertEquals(-1,fileInputStream.read());
    }

    @Test
    @DisplayName("Should throw IOExeption if flush method called after stream closed")
    void testClose() throws IOException {
        bufferedOutputStream.close();
        bufferedOutputStream.write('l');
        bufferedOutputStream.write('o');
        Assertions.assertThrows(IOException.class, bufferedOutputStream::flush);
    }


}
