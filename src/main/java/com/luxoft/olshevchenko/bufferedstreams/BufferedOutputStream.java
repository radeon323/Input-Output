package com.luxoft.olshevchenko.bufferedstreams;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Oleksandr Shevchenko
 */
public class BufferedOutputStream extends OutputStream {
    private final OutputStream outputStream;
    private static final int BUFFER_SIZE = 5;
    private byte[] buffer;
    private int position;

    public BufferedOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.buffer = new byte[BUFFER_SIZE];
    }

    @Override
    public void write(int byt) throws IOException {
        if (position == BUFFER_SIZE) {
            flush();
        }
        buffer[position++] = (byte) byt;
    }

    @Override
    public void write(byte[] array) throws IOException {
        write(array, 0, array.length);
    }

    @Override
    public void write(byte[] array, int offset, int length) throws IOException {
        int freeSpace = buffer.length - position;
        if (freeSpace <= buffer.length) {
            flush();
            outputStream.write(array, offset, length);
        } else {
            System.arraycopy(array, offset, buffer, position, length);
            position += length;
        }
    }

    @Override
    public void flush() throws IOException {
        if (buffer == null) {
            throw new IOException("Stream closed");
        }
        if (position > 0) {
            outputStream.write(buffer, 0, position);
            position = 0;
        }
    }

    @Override
    public void close() throws IOException {
        try (outputStream) {
            flush();
        }
    }


}
