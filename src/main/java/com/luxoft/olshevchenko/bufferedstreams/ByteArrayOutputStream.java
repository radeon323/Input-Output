package com.luxoft.olshevchenko.bufferedstreams;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author Oleksandr Shevchenko
 */
public class ByteArrayOutputStream extends OutputStream {
    protected volatile byte[] buffer;
    protected int position;

    public ByteArrayOutputStream() {
        this(5);
    }

    public ByteArrayOutputStream(int size) {
        buffer = new byte[size];
    }

    @Override
    public void write(int byt) {
        buffer[position] = (byte) byt;
        position++;
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        write(bytes, 0, buffer.length);
    }

    @Override
    public synchronized void write(byte[] bytes, int offset, int length) throws IOException {
        if (length >= buffer.length) {
            buffer = Arrays.copyOf(buffer, length);
        }
        System.arraycopy(bytes, offset, buffer, position, length);
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
