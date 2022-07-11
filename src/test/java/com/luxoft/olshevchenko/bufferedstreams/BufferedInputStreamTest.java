package com.luxoft.olshevchenko.bufferedstreams;

import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Oleksandr Shevchenko
 */
public class BufferedInputStreamTest {

    String content = "Hello";
    BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(content.getBytes()));
//    java.io.BufferedInputStream bufferedInputStream = new java.io.BufferedInputStream(new java.io.ByteArrayInputStream(content.getBytes()));

    @Test
    @DisplayName("Test Read method without parameters")
    void testReadWithoutParam() throws IOException {
        assertEquals('H', bufferedInputStream.read());
        assertEquals('e', bufferedInputStream.read());
        assertEquals('l', bufferedInputStream.read());
        assertEquals('l', bufferedInputStream.read());
        assertEquals('o', bufferedInputStream.read());
        assertEquals(-1, bufferedInputStream.read());
    }

    @Test
    @DisplayName("Test Read method with parameters")
    void testReadWithParam() throws IOException {
        byte[] bytes = new byte[5];
        assertEquals(5, bufferedInputStream.read(bytes));
        assertArrayEquals(content.getBytes(), bytes);
        assertEquals(-1, bufferedInputStream.read());
    }

    @Test
    @DisplayName("Test Read method with parameters and offset")
    void testReadWithParamAndOffset() throws IOException {
        byte[] bytes = new byte[6];
        assertEquals(5, bufferedInputStream.read(bytes, 1,5));
        assertEquals(0, bytes[0]);
        assertEquals(content.getBytes()[0], bytes[1]);
        assertEquals(content.getBytes()[1], bytes[2]);
        assertEquals(content.getBytes()[2], bytes[3]);
        assertEquals(content.getBytes()[3], bytes[4]);
        assertEquals(content.getBytes()[4], bytes[5]);
        assertEquals(-1, bufferedInputStream.read());
    }

    @Test
    @DisplayName("Test Close method")
    void testClose() throws IOException {
        assertEquals('H', bufferedInputStream.read());
        assertEquals('e', bufferedInputStream.read());
        assertEquals('l', bufferedInputStream.read());
        bufferedInputStream.close();
        Assertions.assertThrows(IOException.class, bufferedInputStream::read);
    }


}