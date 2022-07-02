package com.luxoft.olshevchenko;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferedOutputStreamTest {
    String content = "Hello";
    private final String TEST_FILE_PATH = "src/main/resources/testFile.txt";
    BufferedOutputStream bufferedOutputStream;
    java.io.BufferedOutputStream originalBufferedOutputStream;

    @BeforeEach
    void before() throws IOException {
        new File(TEST_FILE_PATH).createNewFile();
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(TEST_FILE_PATH));
        originalBufferedOutputStream = new java.io.BufferedOutputStream(new FileOutputStream(TEST_FILE_PATH));
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
        bufferedOutputStream.write('l');
        bufferedOutputStream.write('l');
        bufferedOutputStream.write('o');
        bufferedOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        assertEquals(content.getBytes()[0],fileInputStream.read());
        assertEquals(content.getBytes()[1],fileInputStream.read());
        assertEquals(content.getBytes()[2],fileInputStream.read());
        assertEquals(content.getBytes()[3],fileInputStream.read());
        assertEquals(content.getBytes()[4],fileInputStream.read());
        assertEquals(-1,fileInputStream.read());
    }

    @Test
    @DisplayName("Test Write method with parameters")
    void testWriteWithParam() throws IOException {
        bufferedOutputStream.write(content.getBytes());
        bufferedOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        assertEquals(content.getBytes()[0],fileInputStream.read());
        assertEquals(content.getBytes()[1],fileInputStream.read());
        assertEquals(content.getBytes()[2],fileInputStream.read());
        assertEquals(content.getBytes()[3],fileInputStream.read());
        assertEquals(content.getBytes()[4],fileInputStream.read());
        assertEquals(-1,fileInputStream.read());
    }

    @Test
    @DisplayName("Test Write method with parameters and offset")
    void testWriteWithParamAndOffset() throws IOException {
        bufferedOutputStream.write(content.getBytes(),1,4);
        bufferedOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        assertEquals(content.getBytes()[1],fileInputStream.read());
        assertEquals(content.getBytes()[2],fileInputStream.read());
        assertEquals(content.getBytes()[3],fileInputStream.read());
        assertEquals(content.getBytes()[4],fileInputStream.read());
        assertEquals(-1,fileInputStream.read());
    }

    @Test
    @DisplayName("Test Close method")
    void testClose() throws IOException {
        bufferedOutputStream.write('H');
        bufferedOutputStream.write('e');
        bufferedOutputStream.write('l');
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        FileInputStream fileInputStream = new FileInputStream(TEST_FILE_PATH);
        assertEquals(content.getBytes()[0],fileInputStream.read());
        assertEquals(content.getBytes()[1],fileInputStream.read());
        assertEquals(content.getBytes()[2],fileInputStream.read());
        bufferedOutputStream.write('l');
        bufferedOutputStream.write('o');
        Assertions.assertThrows(IOException.class, bufferedOutputStream::flush);
    }


}
