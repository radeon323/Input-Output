package com.luxoft.olshevchenko.bufferedstreams;

import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Oleksandr Shevchenko
 */
class ByteArrayOutputStreamTest {
    private final String content = "Hello";
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

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
    void testWriteWithParamAndOffset() throws IOException {
        String content = "Hello java!";
        byteArrayOutputStream.write(content.getBytes(),3,8);
        assertEquals("lo java!", byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Test Close method in ByteArrayOutputStream has no effect")
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