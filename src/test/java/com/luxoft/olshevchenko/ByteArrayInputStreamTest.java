package com.luxoft.olshevchenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Shevchenko
 */
public class ByteArrayInputStreamTest {

    @Test
    public void testRead() {
        String content = "Hello";
        java.io.ByteArrayInputStream byteArrayInputStream
                = new java.io.ByteArrayInputStream(content.getBytes());
        assertEquals('H', (char) byteArrayInputStream.read());
        assertEquals('e', (char) byteArrayInputStream.read());
        assertEquals('l', (char) byteArrayInputStream.read());
        assertEquals('l', (char) byteArrayInputStream.read());
        assertEquals('o', (char) byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }


    @Test
    void read() {
    }


    @Test
    void close() {
    }

    @Test
    void testRead1() {
    }
}