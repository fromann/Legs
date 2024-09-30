package cn.ss.service;

import cn.ss.pojo.PageBean;
import cn.ss.pojo.User;

import java.util.List;

public interface UsersService {
    PageBean page(Integer page, Integer pageSize, String username, String name, String phone, Integer type);

    void delete(List<String> usernames);

    void add(User user);

    void edit(User user);
}
