package cn.ss.controller;

import cn.ss.pojo.Result;
import cn.ss.pojo.User;
import cn.ss.service.LoginService;
import cn.ss.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public Result login(String username, String password) {
        User login = loginService.login(username, password);
        if (login == null) {
            return Result.err("账号或密码输入有误");
        }

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", login.getUsername());
        claims.put("name", login.getName());
        claims.put("type", login.getType());
        claims.put("phone", login.getPhone());
        String jwt = JwtUtils.generateJwt(claims);
        redisTemplate.opsForValue().set(jwt, username, 86400, TimeUnit.SECONDS);

        return Result.success(jwt);
    }

    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        log.info(String.valueOf(claims));
        return Result.success(claims);
    }

}