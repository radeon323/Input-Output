package com.luxoft.olshevchenko.bufferedstreams;

import org.junit.jupiter.api.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Oleksandr Shevchenko
 */
public class BufferedInputStreamTest {
    private final String content = "Hello";
    private final BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(content.getBytes()));

    @Test
    @DisplayName("Test Read method without parameters")
    void testReadWithoutParam() throws IOException {
        String content = "HelloJ";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(content.getBytes()));
        assertEquals('H', bufferedInputStream.read());
        assertEquals('e', bufferedInputStream.read());
        assertEquals('l', bufferedInputStream.read());
        assertEquals('l', bufferedInputStream.read());
        assertEquals('o', bufferedInputStream.read());
        assertEquals('J', bufferedInputStream.read());
        assertEquals(-1, bufferedInputStream.read());
    }

    @Test
    @DisplayName("Test Read method with parameters")
    void testReadWithParam() throws IOException {
        byte[] array = new byte[5];
        assertEquals(5, bufferedInputStream.read(array));
        assertArrayEquals(content.getBytes(), array);
        assertEquals(-1, bufferedInputStream.read());
    }

    @Test
    @DisplayName("Test Read method with parameters and offset")
    void testReadWithParamAndOffset() throws IOException {
        byte[] array = new byte[6];
        assertEquals(5, bufferedInputStream.read(array, 1,5));
        assertEquals(0, array[0]);
        assertEquals('H', array[1]);
        assertEquals('e', array[2]);
        assertEquals('l', array[3]);
        assertEquals('l', array[4]);
        assertEquals('o', array[5]);
        assertEquals(-1, bufferedInputStream.read());
    }

    @Test
    @DisplayName("Should throw IOExeption if read method called after stream closed")
    void testClose() throws IOException {
        assertEquals('H', bufferedInputStream.read());
        assertEquals('e', bufferedInputStream.read());
        assertEquals('l', bufferedInputStream.read());
        bufferedInputStream.close();
        Assertions.assertThrows(IOException.class, bufferedInputStream::read);
    }


}