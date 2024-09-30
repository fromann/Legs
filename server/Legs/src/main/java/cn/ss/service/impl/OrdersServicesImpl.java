package cn.ss.service.impl;

import cn.ss.mapper.OrdersMapper;
import cn.ss.pojo.Order;
import cn.ss.pojo.PageBean;
import cn.ss.service.OrdersService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersServicesImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String sender, String receiver, Integer state,String task) {
        PageHelper.startPage(page, pageSize);
        List<Order> orderList = ordersMapper.list(sender,receiver,state,task);
        Page<Order> o = (Page<Order>) orderList;
        return new PageBean(o.getTotal(), o.getResult());
    }



    @Override
    public void delete(List<String> ids) {
        ordersMapper.delete(ids);
    }

    @Override
    public void add(Order order) {
        order.setDate(LocalDateTime.now());
        order.setId(UUID.randomUUID().toString());
        order.setState(0);
        ordersMapper.add(order);
    }

    @Override
    public void edit(Order order) {
        ordersMapper.edit(order);
    }
}
