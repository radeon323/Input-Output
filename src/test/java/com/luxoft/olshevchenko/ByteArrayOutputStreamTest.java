package com.luxoft.olshevchenko;

import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteArrayOutputStreamTest {

    String content = "Hello";
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//    java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();

    @Test
    @DisplayName("Test Write method without parameters")
    void testWriteWithoutParam() {
        byteArrayOutputStream.write('H');
        byteArrayOutputStream.write('e');
        byteArrayOutputStream.write('l');
        byteArrayOutputStream.write('l');
        byteArrayOutputStream.write('o');
        assertEquals(content, byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Test Write method with parameters")
    void testWriteWithParam() throws IOException {
        byteArrayOutputStream.write(content.getBytes());
        assertEquals(content, byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Test Write method with parameters and offset")
    void testWriteWithParamAndOffset() {
        byteArrayOutputStream.write(content.getBytes(),3,2);
        assertEquals("lo", byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Test Close method")
    void testClose() throws IOException {
        byteArrayOutputStream.write('H');
        byteArrayOutputStream.write('e');
        byteArrayOutputStream.write('l');
        byteArrayOutputStream.close();
        byteArrayOutputStream.write('l');
        byteArrayOutputStream.write('o');
        assertEquals(content, byteArrayOutputStream.toString());
    }


}