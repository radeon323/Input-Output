package com.luxoft.olshevchenko;

import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Shevchenko
 */
public class ByteArrayInputStreamTest {

    String content = "Hello";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
//    java.io.ByteArrayInputStream byteArrayInputStream = new java.io.ByteArrayInputStream(content.getBytes());

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
        byte[] bytes = new byte[5];
        assertEquals(5, byteArrayInputStream.read(bytes));
        assertArrayEquals(content.getBytes(), bytes);
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    @DisplayName("Test Read method with parameters and offset")
    void testReadWithParamAndOffset() {
        byte[] bytes = new byte[6];
        assertEquals(5, byteArrayInputStream.read(bytes, 1,5));
        assertEquals(0, bytes[0]);
        assertEquals(content.getBytes()[0], bytes[1]);
        assertEquals(content.getBytes()[1], bytes[2]);
        assertEquals(content.getBytes()[2], bytes[3]);
        assertEquals(content.getBytes()[3], bytes[4]);
        assertEquals(content.getBytes()[4], bytes[5]);
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    @DisplayName("Test Close method")
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