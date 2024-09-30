package com.legs.pojo;

public class User {
    private String username;
    private String password;
    private String name;
    private String phone;
    private int type;

    public User() {
    }

    public User(String username, String password, String name, String phone, int type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}


