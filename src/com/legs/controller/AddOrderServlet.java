package com.legs.controller;

import com.alibaba.fastjson.JSON;
import com.legs.common.ResultMap;
import com.legs.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//添加订单（成功）
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    ResultMap resultMap = new ResultMap();
    OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String task = req.getParameter("task");
            String price = req.getParameter("price");
            String sender =req.getParameter("sender");
            orderService.addOrder(task,sender,price);
            resultMap.setStatus(true);
        }catch (Exception e){
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
