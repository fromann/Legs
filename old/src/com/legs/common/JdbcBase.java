package com.legs.common;

import java.sql.*;

public class JdbcBase {
    static Connection conn = null;//连接对象
    static ResultSet rs = null;//返回结果集对象

    static {
        try {
            /*
            MySQL 8.0 以下版本com.mysql.jdbc.Driver
            MySQL 8.0 以上版本 com.mysql.cj.jdbc.Driver
            */
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //建立连接
    public static Connection getConn() {
        // 路径 账号 密码
        /*
         * "jdbc:mysql://localhost:3306/hotel?"
         * mysql 8
         * ?serverTimezone=UTC
         * mysql 5
         * ?characterEncoding=utf8&serverTimezone=UTC
         * */
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/legs?serverTimezone=UTC", "root", "123456");
            System.out.println("Successfully connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    //执行增删改sql语句
    public static int updateSql(String sql, Object[] obj) {
        int num = 0;
        try {
            conn = getConn();
            //连接对象创建执行sql窗口
            PreparedStatement pstm = conn.prepareStatement(sql);
            //判断Sql语句师傅需要参数 仅需要判断object 数组即可
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pstm.setObject(i + 1, obj[i]);//参数的意思挨个复制？的值

                }
            }
            //执行sql语句
            num = pstm.executeUpdate();//eU做增删改 查询用executeQuery()
            //返回受影响的行数
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    //执行查询sql语句
    public static ResultSet querySql(String sql, Object[] obj) {
        try {
            conn = getConn();
            //创建命令窗口
            PreparedStatement pstm = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pstm.setObject(i + 1, obj[i]);//参数的意思挨个复制？的值
                }
            }
            rs = pstm.executeQuery();//类似于返回集合之类的数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    //关闭流
    public static void close() {
        try {
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}