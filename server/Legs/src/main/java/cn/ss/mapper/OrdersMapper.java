package cn.ss.mapper;

import cn.ss.pojo.Order;
import cn.ss.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrdersMapper {

    public List<Order> list(String sender,String receiver,Integer state,String task);


    void delete(List<String> ids);

    @Insert("insert into orders(id,task,price,date,state,sender) " +
            "values(#{id},#{task},#{price},#{date},#{state},#{sender})")
    void add(Order order);

    void edit(Order order);

    void deleteByUser(List<String> usernames);

}
