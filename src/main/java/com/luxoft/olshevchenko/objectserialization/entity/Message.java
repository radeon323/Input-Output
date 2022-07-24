package com.luxoft.olshevchenko.objectserialization.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Oleksandr Shevchenko
 */
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 8575799808933029326L;

    private Date date;
    private String message;
    private double amount;

    public Message() {
    }

    public Message(Date date, String message, double amount) {
        this.date = date;
        this.message = message;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Message{");
        sb.append("date=").append(date);
        sb.append(", message='").append(message).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
