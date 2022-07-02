package com.luxoft.olshevchenko;

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
            writeBuffer();
        }
        buffer[position++] = (byte) byt;
    }

    @Override
    public void write(byte[] bytes) {
        write(bytes, 0, bytes.length);
    }

    @Override
    public void write(byte[] bytes, int offset, int length) {
        System.arraycopy(bytes, offset, buffer, position, length);
        position = position + length;
    }

    @Override
    public void flush() throws IOException {
        if (buffer == null) {
            throw new IOException("Stream closed");
        }
        writeBuffer();
        target.flush();
    }

    @Override
    public void close() throws IOException {
        target.close();
    }


    private void writeBuffer() throws IOException {
        if (position > 0) {
            target.write(buffer, 0, position);
            position = 0;
        }
    }


}
