package com.legs.controller;

import com.alibaba.fastjson.JSON;
import com.legs.common.ResultMap;
import com.legs.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminEditUserController")
public class AdminEditUserController extends HttpServlet {
    UserService userService = new UserService();
    ResultMap resultMap = new ResultMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String phone = req.getParameter("phone");
            String t = req.getParameter("type");
            Integer type= new Integer(t);
            //调用业务逻辑层
            userService.adminEditUser(username,type,phone);
            resultMap.setStatus(true);
        } catch (Exception e) {
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        String json = JSON.toJSONString(resultMap);
        resp.setCharacterEncoding("utf-8");
        //通过response响应流将json数据发送给前端
        resp.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
