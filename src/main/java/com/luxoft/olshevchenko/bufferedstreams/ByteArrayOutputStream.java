package com.luxoft.olshevchenko.bufferedstreams;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author Oleksandr Shevchenko
 */
public class ByteArrayOutputStream extends OutputStream {
    private byte[] buffer;
    private int position;

    public ByteArrayOutputStream() {
        this(5);
    }

    public ByteArrayOutputStream(int size) {
        this.buffer = new byte[size];
    }

    @Override
    public void write(int bit) {
        buffer[position] = (byte) bit;
        position++;
    }

    @Override
    public void write(byte[] array) throws IOException {
        write(array, 0, buffer.length);
    }

    @Override
    public void write(byte[] array, int offset, int length) throws IOException {
        if (length > buffer.length) {
            buffer = Arrays.copyOf(buffer, length);
        }
        System.arraycopy(array, offset, buffer, position, length);
        position += length;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public String toString() {
        return new String(buffer, 0, position);
    }


}
