package com.legs.controller;

import com.alibaba.fastjson.JSON;
import com.legs.common.ResultMap;
import com.legs.pojo.Order;
import com.legs.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/receiverSelectAllOrder")
public class ReceiverSelAllOrder extends HttpServlet {
    OrderService orderService = new OrderService();
    ResultMap resultMap = new ResultMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String currentPage=req.getParameter("currentPage");
            String pageSize=req.getParameter("pageSize");
            List<Order> list = orderService.receiverSelectAllOrder(currentPage,pageSize);
            resultMap.setList(list);
            Long total=orderService.receiverSelectCountOrder();
            resultMap.setTotal(total);
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
        doGet(req, resp);
    }
}
