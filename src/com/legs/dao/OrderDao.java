package com.legs.dao;

import com.legs.common.JdbcBase;
import com.legs.pojo.Order;
import com.legs.pojo.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao{
    //管理员查询所有订单
    public List<Order> adminSelectAllOrder(int currentPage,int pageSize) throws SQLException {
        String sql = "select * from orders limit ?,?";
        Object[] obj = {currentPage,pageSize};
        List<Order> list = new ArrayList<>();
        ResultSet rs = JdbcBase.querySql(sql, obj);
        while (rs.next()) {
            Order order = new Order(
                    rs.getString("id"),
                    rs.getString("task"),
                    rs.getString("sender"),
                    rs.getString("receiver"),
                    rs.getDate("date"),
                    rs.getInt("state"),
                    rs.getString("price"));
            list.add(order);
        }
        return list;
    }

    public long adminSelectCountOrder() throws SQLException {
        String sql = "select count(*) from orders";
        ResultSet rs = JdbcBase.querySql(sql, null);
        long count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
    public int adminEditOrder(String id, String task,String price,int state) {
        String sql = "update orders set task= ?,price=?,state=? where id =?";
        Object[] obj = {task,price,state,id};
        int num = JdbcBase.updateSql(sql, obj);
        return num;
    }

    public List<Order> senderSelectAllOrder(String sender) throws SQLException {
        String sql = "select * from orders where sender= ?";
        Object[] obj = {sender};
        List<Order> list = new ArrayList<>();
        ResultSet rs = JdbcBase.querySql(sql, obj);
        while (rs.next()) {
            Order order = new Order(
                    rs.getString("id"),
                    rs.getString("task"),
                    rs.getString("sender"),
                    rs.getString("receiver"),
                    rs.getDate("date"),
                    rs.getInt("state"),
                    rs.getString("price"));
            list.add(order);
        }
        return list;
    }
    //接单者の广场 面板
    public List<Order> receiverSelectAllOrder(int currentPage,int pageSize) throws SQLException {
        String sql = "select * from orders where state = ? limit ?,?";
        Object[] obj = {"0",currentPage,pageSize};
        List<Order> list = new ArrayList<>();
        ResultSet rs = JdbcBase.querySql(sql, obj);
        while (rs.next()) {
            Order order = new Order(
                    rs.getString("id"),
                    rs.getString("task"),
                    rs.getString("sender"),
                    rs.getString("receiver"),
                    rs.getDate("date"),
                    rs.getInt("state"),
                    rs.getString("price"));
            list.add(order);
        }
        return list;
    }
    public long receiverSelectCountOrder() throws SQLException {
        String sql = "select count(*) from orders where state = '0'";
        ResultSet rs = JdbcBase.querySql(sql, null);
        long count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    //接单者の接收 面板
    public List<Order> receiverSelectMyOrder(String receiver) throws SQLException {
        String sql = "select * from orders where receiver = ?";
        Object[] obj = {receiver};
        List<Order> list = new ArrayList<>();
        ResultSet rs = JdbcBase.querySql(sql, obj);
        while (rs.next()) {
            Order order = new Order(
                    rs.getString("id"),
                    rs.getString("task"),
                    rs.getString("sender"),
                    rs.getString("receiver"),
                    rs.getDate("date"),
                    rs.getInt("state"),
                    rs.getString("price"));
            list.add(order);
        }
        return list;
    }

    //接收订单 （删除订单/接收者）
    public int recOrder(String id,String receiver) {
        String sql = "update orders set state= 1 ,receiver =? where id =?";
        Object[] obj = {receiver,id};
        int num = JdbcBase.updateSql(sql, obj);
        JdbcBase.close();
        return num;
    }
    //退订单 （退订单/接收者）
    public int backOrder(String id) {
        String sql = "update orders set state= 0 ,receiver = NULL where id =?";
        Object[] obj = {id};
        int num = JdbcBase.updateSql(sql, obj);
        JdbcBase.close();
        return num;
    }

    //添加订单（成功）
    public int addOrder(Order order) {
        String sql = "insert into Orders(id,task,sender,receiver,date,state,price) values(?,?,?,?,?,?,?)";
        Object[] obj = {order.getId(),order.getTask(),order.getSender(),order.getReceiver(),order.getDate(),order.getState(),order.getPrice()};
        int num = JdbcBase.updateSql(sql, obj);
        JdbcBase.close();
        return num;
    }


    public int delOrder(String id) {
        String sql = "delete from orders where id = ?";
        Object[] obj = {id};
        int num = JdbcBase.updateSql(sql, obj);
        JdbcBase.close();
        return num;
    }
    //删除订单


    public int senderEditOrder(String id, String task,String price) {
        String sql = "update orders set task= ?,price=? where id =?";
        Object[] obj = {task,price,id};
        int num = JdbcBase.updateSql(sql, obj);
        return num;
    }
}
