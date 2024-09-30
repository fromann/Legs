package cn.ss.service.impl;

import cn.ss.mapper.OrdersMapper;
import cn.ss.mapper.UsersMapper;
import cn.ss.pojo.Order;
import cn.ss.pojo.PageBean;
import cn.ss.pojo.User;
import cn.ss.service.UsersService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public PageBean page(Integer page, Integer pageSize, String username, String name, String phone, Integer type) {
        PageHelper.startPage(page, pageSize);
        List<User> userList = usersMapper.list(username, name, phone, type);
        Page<User> u = (Page<User>) userList;
        return new PageBean(u.getTotal(), u.getResult());
    }

    @Override
    public void delete(List<String> usernames) {
        ordersMapper.deleteByUser(usernames);
        usersMapper.delete(usernames);
    }

    @Override
    public void add(User user) {
        usersMapper.add(user);
    }

    @Override
    public void edit(User user) {
        usersMapper.edit(user);
    }
}
