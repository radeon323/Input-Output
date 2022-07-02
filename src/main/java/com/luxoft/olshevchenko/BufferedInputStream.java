package com.luxoft.olshevchenko;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author Oleksandr Shevchenko
 */
public class BufferedInputStream extends InputStream {
    private final InputStream target;
    private static final int BUFFER_SIZE = 5;
    protected volatile byte[] buffer;
    protected int position;

    public BufferedInputStream(InputStream target) {
        this.target = target;
        buffer = new byte[BUFFER_SIZE];
    }

    @Override
    public int read() throws IOException {
        if (buffer == null) {
            throw new IOException("Stream closed");
        }
        if (position == BUFFER_SIZE || position == 0) {
            position = 0;
            getBytes();
        }
        if (buffer[position] != 0) {
            int current = buffer[position];
            position++;
            return current;
        }
        return -1;
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return read(bytes, 0, bytes.length);
    }

    @Override
    public int read(byte[] bytes, int offset, int length) throws IOException {
        int position = 0;
        if (length == 0) {
            return 0;
        }
        read();
        byte currentByte = this.buffer[position];
        if (currentByte == -1) {
            return -1;
        }
        bytes[offset] = currentByte;
        for (position = 1; position < length ; position++) {
            currentByte = (byte) read();
            if (currentByte == -1) {
                return -1;
            }
            bytes[offset + position] = currentByte;
        }
        return position;
    }

    @Override
    public void close() throws IOException {
        target.close();
        buffer = null;
    }


    private void getBytes() throws IOException {
        int bytes = target.read(buffer);
        if (bytes > 0) {
            System.arraycopy(buffer, 0, buffer, 0, bytes);
            for (int i = bytes; i < BUFFER_SIZE; i++) {
                buffer[i] = 0;
            }
        } else {
            Arrays.fill(buffer, (byte) 0);
        }
    }


}
