package com.legs.controller;

import com.alibaba.fastjson.JSON;
import com.legs.common.ResultMap;
import com.legs.service.OrderService;
import com.legs.common.ResultMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backOrderSerclet")
public class BackOrderSerclet extends HttpServlet {
    OrderService orderService=new OrderService();
    ResultMap resultMap=new ResultMap();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String rid = request.getParameter("rid");

            orderService.backOrder(rid);
            resultMap.setStatus(true);

        } catch (Exception e) {
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());

        }

        String json = JSON.toJSONString(resultMap);
        response.setCharacterEncoding("utf-8");
        //通过response响应流将json数据发送给前端
        response.getWriter().print(json);
    }
}
