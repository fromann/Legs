package cn.ss.mapper;

import cn.ss.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UsersMapper {

    public List<User> list(String username, String name, String phone, Integer type);

    void delete(List<String> usernames);

    @Insert("insert into user(username,password,name,phone,type) " +
            "values(#{username},#{password},#{name},#{phone},#{type})")
    void add(User user);

    void edit(User user);
}
