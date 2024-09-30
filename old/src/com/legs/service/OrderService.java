package com.legs.service;

import com.legs.dao.OrderDao;
import com.legs.pojo.Order;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderService {
    OrderDao orderDao = new OrderDao();

    public List<Order> adminSelectAllOrder(String currentPageStr, String pageSizeStr) throws SQLException {
        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        currentPage = (currentPage - 1) * pageSize;
        return orderDao.adminSelectAllOrder(currentPage, pageSize);
    }

    public long adminSelectCountOrder() throws SQLException {
        return orderDao.adminSelectCountOrder();
    }

    public int adminEditOrder(String id, String task,String price,int state){
        int num = orderDao.adminEditOrder(id,task,price,state);
        if (num == 0) {
            throw new RuntimeException("订单编辑失败！");
        }
        return num;
    }
    public List<Order> senderSelectAllOrder(String sender) throws SQLException {
        return orderDao.senderSelectAllOrder(sender);
    }
    public List<Order> receiverSelectAllOrder(String currentPageStr, String pageSizeStr) throws SQLException {
        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        currentPage = (currentPage - 1) * pageSize;
        return orderDao.receiverSelectAllOrder(currentPage, pageSize);
    }
    public long receiverSelectCountOrder() throws SQLException {
        return orderDao.adminSelectCountOrder();
    }
    public List<Order> receiverSelectMyOrder(String receiver) throws SQLException {
        return orderDao.receiverSelectMyOrder(receiver);
    }

    public int delOrder(String id) {
        int num = orderDao.delOrder(id);
        if (num == 0) {
            throw new RuntimeException("订单删除失败！");
        }
        return num;
    }

//接收订单
public int recOrder(String id,String receiver) {
    int num = orderDao.recOrder(id,receiver);
    if (num == 0) {
        throw new RuntimeException("订单接取失败！");
    }
    return num;
}
    //退订单（接收者退）
    public int backOrder(String id) {
        int num = orderDao.backOrder(id);
        if (num == 0) {
            throw new RuntimeException("退除订单失败!");
        }
        return num;
    }

    //添加订单（成功）
    public int addOrder(String task,String sender,String price) {
        String id = UUID.randomUUID().toString();
        String receiver ="";
        int state=0;

        Date date = new Date(new java.util.Date().getTime());

        Order order=new Order(id,task,sender,receiver,date,state,price);
        int num = orderDao.addOrder(order);

        if (num == 0) {
            throw new RuntimeException("类别添加失败，请联系管理员！");
        }
        return num;
    }

    public int senderEditOrder(String id, String task,String price){
        int num = orderDao.senderEditOrder(id,task,price);
        if (num == 0) {
            throw new RuntimeException("订单编辑失败，请联系管理员！");
        }
        return num;
    }
}
