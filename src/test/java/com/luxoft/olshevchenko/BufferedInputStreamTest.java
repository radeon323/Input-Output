package com.luxoft.olshevchenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Oleksandr Shevchenko
 */
public class BufferedInputStreamTest {

    String content = "Hello";
    BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(content.getBytes()));
    java.io.BufferedInputStream originalBufferedInputStream = new java.io.BufferedInputStream(new ByteArrayInputStream(content.getBytes()));

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
        assertArrayEquals(content.getBytes(), bytes);
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