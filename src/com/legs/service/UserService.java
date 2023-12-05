package com.legs.service;



import com.legs.dao.UserDao;
import com.legs.pojo.Order;
import com.legs.pojo.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

//业务逻辑层
public class UserService {
    UserDao userDao = new UserDao();

    public User login(String username, String password) throws SQLException {
        User user = userDao.login(username, password);
        if (user == null) {
            throw new RuntimeException("账号或密码输入有误");
        }
        return user;
    }

    public List<User> adminSelectAllUser(String currentPageStr, String pageSizeStr) throws SQLException {
        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        currentPage = (currentPage - 1) * pageSize;
        return userDao.adminSelectAllUser(currentPage, pageSize);
    }
    public long adminSelectCountUser() throws SQLException {
        return userDao.adminSelectCountUser();
    }
    public int adminEditUser(String username, int type,String phone){
        int num = userDao.adminEditUser(username,type,phone);
        if (num == 0) {
            throw new RuntimeException("用户编辑失败！");
        }
        return num;
    }


    public int delUser(String username) {
        int num = userDao.delUser(username);
        if (num == 0) {
            throw new RuntimeException("用户删除失败！");
        }
        return num;
    }

}
