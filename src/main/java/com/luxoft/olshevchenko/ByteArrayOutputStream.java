package com.luxoft.olshevchenko;

import java.io.IOException;
import java.io.OutputStream;

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
    public void write(byte[] bytes) {
        write(bytes, 0, buffer.length);
    }

    @Override
    public void write(byte[] bytes, int offset, int length) {
        System.arraycopy(bytes, offset, buffer, position, length);
        position = position + length;
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
