package com.luxoft.olshevchenko.bufferedstreams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
        if (fillBuffer() == -1) {
            return -1;
        }
        return buffer[position++];
    }

    @Override
    public int read(byte[] array) throws IOException {
        return read(array, 0, array.length);
    }

    @Override
    public int read(byte[] array, int offset, int length) throws IOException {
        if (buffer == null) {
            throw new IOException("Stream closed");
        }
        if (length == 0) {
            return 0;
        }
        int bufferCount = count - position;
        if (bufferCount == 0) {
            return inputStream.read(array, offset, length);
        } else {
            fillBuffer();
            if (bufferCount < length) {
                length = bufferCount;
            }
            System.arraycopy(buffer, position, array, offset, length);
            position += length;
            return length;
        }
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
