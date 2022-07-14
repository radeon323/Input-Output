package com.luxoft.olshevchenko.bufferedstreams;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Oleksandr Shevchenko
 */
public class BufferedInputStream extends InputStream {
    private final InputStream inputStream;
    private static final int BUFFER_SIZE = 5;
    private byte[] buffer;
    private int position;
    private int count;

    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        this.buffer = new byte[BUFFER_SIZE];
    }

    @Override
    public int read() throws IOException {
        if (buffer == null) {
            throw new IOException("Stream closed");
        }
        fillBuffer();
        if (count != -1) {
            return buffer[position++];
        }
        return count;
    }

    @Override
    public int read(byte[] array) throws IOException {
        return read(array, 0, array.length);
    }

    @Override
    public int read(byte[] array, int offset, int length) throws IOException {
        if (length == 0) {
            return 0;
        }
        if (fillBuffer() == -1) {
            return -1;
        }
        if (length > buffer.length) {
            inputStream.read(array, offset, length);
        } else {
            System.arraycopy(buffer, position, array, offset, length);
            position += length;
        }
        return position;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        buffer = null;
    }

    private int fillBuffer() throws IOException {
        if (position == count) {
            count = inputStream.read(buffer);
            position = 0;
        }
        return count;
    }


}
