package com.luxoft.olshevchenko.bufferedstreams;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Oleksandr Shevchenko
 */
public class ByteArrayInputStream extends InputStream {
    private byte[] buffer;
    private int position;

    public ByteArrayInputStream(byte[] array) {
        this.buffer = array;
    }

    @Override
    public int read() {
        if (position < buffer.length) {
            return buffer[position++];
        }
        return -1;
    }

    @Override
    public int read(byte[] array) {
        return read(array, 0, array.length);
    }

    @Override
    public int read(byte[] array, int offset, int length) {
        if (position == buffer.length) {
            return -1;
        }
        if (position >= length) {
            length = buffer.length-length;
        }
        System.arraycopy(buffer, position, array, offset, length);
        position += length;
        return length;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }


}
