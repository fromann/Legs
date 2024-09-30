package cn.ss.service;


import cn.ss.pojo.User;

public interface LoginService {
    public User login(String username, String password);
}
