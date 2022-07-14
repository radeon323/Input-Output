package com.luxoft.olshevchenko.bufferedstreams;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Shevchenko
 */
public class ByteArrayInputStreamTest {
    private final String content = "Hello";
    private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

    @Test
    @DisplayName("Test Read method without parameters")
    void testReadWithoutParam() {
        assertEquals('H', byteArrayInputStream.read());
        assertEquals('e', byteArrayInputStream.read());
        assertEquals('l', byteArrayInputStream.read());
        assertEquals('l', byteArrayInputStream.read());
        assertEquals('o', byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    @DisplayName("Test Read method with parameters")
    void testReadWithParam() throws IOException {
        byte[] array = new byte[5];
        assertEquals(5, byteArrayInputStream.read(array));
        assertArrayEquals(content.getBytes(), array);
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    @DisplayName("Test Read method with parameters and offset")
    void testReadWithParamAndOffset() {
        byte[] array = new byte[6];
        assertEquals(5, byteArrayInputStream.read(array, 1,5));
        assertEquals(0, array[0]);
        assertEquals('H', array[1]);
        assertEquals('e', array[2]);
        assertEquals('l', array[3]);
        assertEquals('l', array[4]);
        assertEquals('o', array[5]);
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    @DisplayName("Test Close method in ByteArrayInputStream has no effect")
    void testClose() throws IOException {
        assertEquals('H', byteArrayInputStream.read());
        assertEquals('e', byteArrayInputStream.read());
        assertEquals('l', byteArrayInputStream.read());
        byteArrayInputStream.close();
        assertEquals('l', byteArrayInputStream.read());
        assertEquals('o', byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }


}