package com.legs.dao;



import com.legs.common.JdbcBase;
import com.legs.pojo.Order;
import com.legs.pojo.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//dao  数据访问层
public class UserDao {
    public User login(String username, String password) throws SQLException {
        User user=null;
        String sql="select * from user where username = ? and password = ? ";
        Object[] obj={username,password};
        ResultSet rs = JdbcBase.querySql(sql,obj);
        //解析结果集
        if(rs.next()){
            user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            );
        }
        return user;
    }

    public List<User> adminSelectAllUser(int currentPage,int pageSize) throws SQLException {
        String sql = "select * from user limit ?,?";
        Object[] obj = {currentPage,pageSize};
        List<User> list = new ArrayList<>();
        ResultSet rs = JdbcBase.querySql(sql, obj);
        while (rs.next()) {
            User user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5));
            list.add(user);
        }
        return list;
    }


    public long adminSelectCountUser() throws SQLException {
        String sql = "select count(*) from user";
        ResultSet rs = JdbcBase.querySql(sql, null);
        long count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }


    //删除订单
    public int delUser(String username) {
        String sql = "delete from user where username = ?";
        Object[] obj = {username};
        int num = JdbcBase.updateSql(sql, obj);
        JdbcBase.close();
        return num;
    }

    public int adminEditUser(String username, int type,String phone) {
        String sql = "update user set type= ?,phone=? where username =?";
        Object[] obj = {type,phone,username};
        int num = JdbcBase.updateSql(sql, obj);
        return num;
    }
//    //新增订单
//    public int addType(String tid, String tname, Date createdate) {
//        String sql = "insert into type(tid,createdate,tname) values(?,?,?)";
//        Object[] obj = {tid, createdate, tname};
//        int num = JdbcBase.updateSql(sql, obj);
//        JdbcBase.close();
//        return num;
//    }
//
//    //修改订单
//    public int editType(String tid, String tname, Date createdate) {
//        String sql = "update type set createdate= ?,tname=? where tid =?";
//        Object[] obj = {createdate, tname, tid};
//        int num = JdbcBase.updateSql(sql, obj);
//        return num;
//    }
}
