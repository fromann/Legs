package cn.ss.service;

import cn.ss.pojo.Order;
import cn.ss.pojo.PageBean;
import cn.ss.pojo.User;

import java.util.List;

public interface OrdersService {

    PageBean page(Integer page, Integer pageSize, String sender, String receiver, Integer state,String task);

    void delete(List<String> ids);

    void add(Order order);

    void edit(Order order);
}
