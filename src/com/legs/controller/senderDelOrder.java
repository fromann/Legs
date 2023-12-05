package com.legs.controller;

import com.alibaba.fastjson.JSON;
import com.legs.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.legs.service.OrderService;
import com.legs.common.ResultMap;

@WebServlet("/senderDelOrder")
public class senderDelOrder extends HttpServlet {
    OrderService orderService=new OrderService();
    ResultMap resultMap=new ResultMap();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id=req.getParameter("id");
            orderService.delOrder(id);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        //把返回结果类发送给前端
        //将对象转换为json格式的字符串
        String json= JSON.toJSONString(resultMap);
        resp.setCharacterEncoding("utf-8");
        //通过resp响应流响应给前端
        resp.getWriter().print(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
