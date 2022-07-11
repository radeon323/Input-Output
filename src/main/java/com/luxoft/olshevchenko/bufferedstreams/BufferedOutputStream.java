package com.luxoft.olshevchenko.bufferedstreams;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Oleksandr Shevchenko
 */
public class BufferedOutputStream extends OutputStream {
    private final OutputStream target;
    private static final int BUFFER_SIZE = 5;
    protected volatile byte[] buffer;
    protected int position;

    public BufferedOutputStream(OutputStream target) {
        this.target = target;
        buffer = new byte[BUFFER_SIZE];
    }

    @Override
    public void write(int byt) throws IOException {
        if (position == BUFFER_SIZE) {
            flush();
        }
        buffer[position++] = (byte) byt;
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        write(bytes, 0, bytes.length);
    }

    @Override
    public void write(byte[] bytes, int offset, int length) throws IOException {
        int freeSpace = buffer.length - position;
        if (freeSpace <= buffer.length) {
            flush();
            target.write(bytes, offset, length);
        } else {
            System.arraycopy(bytes, offset, buffer, position, length);
            position += length;
        }
    }

    @Override
    public void flush() throws IOException {
        if (buffer == null) {
            throw new IOException("Stream closed");
        }
        if (position > 0) {
            target.write(buffer, 0, position);
            position = 0;
        }
    }

    @Override
    public void close() throws IOException {
        try (target) {
            flush();
        }
    }


}
