package com.legs.pojo;

import java.util.Date;

public class Order {
    private String id;
    private String task;
    private String sender;
    private String receiver;
    private Date date;
    private int state;
    private String price;

    public Order() {
    }

    public Order(String id, String task, String sender, String receiver, Date date, int state, String price) {
        this.id = id;
        this.task = task;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.state = state;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
