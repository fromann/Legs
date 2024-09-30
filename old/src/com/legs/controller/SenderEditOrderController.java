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
@WebServlet("/senderEditOrderController")
public class SenderEditOrderController extends HttpServlet {
    OrderService orderService = new OrderService();
    ResultMap resultMap = new ResultMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String task = req.getParameter("task");
            String price = req.getParameter("price");
            System.out.println(task+","+price);
            //调用业务逻辑层
            orderService.senderEditOrder(id,task,price);
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
