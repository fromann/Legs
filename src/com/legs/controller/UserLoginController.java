package com.legs.controller;

import com.alibaba.fastjson.JSON;
import com.legs.common.ResultMap;
import com.legs.pojo.User;
import com.legs.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class UserLoginController extends HttpServlet {
    UserService userService=new UserService();
    ResultMap resultMap=new ResultMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException ,NullPointerException{
        try {
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            User user= userService.login(username,password);
            //利用session缓存，记录user
            HttpSession session=req.getSession();
            session.setAttribute("user",user);
            session.setAttribute("name",user.getName());
            session.setAttribute("username",user.getUsername());
            //响应给前端数据
            resultMap.setType(user.getType());
            resultMap.setStatus(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (RuntimeException e){
            resultMap.setStatus(false);
            resultMap.setMessage("账号或密码输入有误，请检查！");
        }
        //把返回结果类发送给前端
        //将对象转换为json格式的字符串
        String json= JSON.toJSONString(resultMap);
        resp.setCharacterEncoding("utf-8");
        //通过resp响应流响应给前端
        resp.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
