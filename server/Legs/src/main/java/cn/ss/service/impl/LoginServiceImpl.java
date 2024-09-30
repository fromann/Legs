package cn.ss.service.impl;

import cn.ss.mapper.LoginMapper;
import cn.ss.pojo.User;
import cn.ss.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.userLogin(username, password);
    }
}
