package com.luxoft.olshevchenko.objectserialization;

import com.luxoft.olshevchenko.objectserialization.entity.Message;

import java.io.IOException;

/**
 * @author Oleksandr Shevchenko
 */
public interface MessageDao {
    void save(Message message) throws IOException;
    Message load() throws IOException, ClassNotFoundException;
}
