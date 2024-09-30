package com.legs.filter;



import com.legs.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/index.jsp")
public class IndexFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
         * 每打开一个浏览器，浏览器就会自动生成一个JSESSIONID
         * 服务器就会接收JSESSIONID的key值，利用key值生成对应的session
         * 此时如果关闭浏览器，打开新的浏览器，那么浏览器就会生成新的JSESSIONID,
         *   利用新的JSESSIONID去生成新的session
         * */
        //将请求与响应对象转换成带有HTTP协议的对象
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取session对象
        HttpSession session = req.getSession();
        //从session对象中获取用户的信息
        User user = (User) session.getAttribute("user");
        if (user == null) {//用户没有登录过
            //重定向
            resp.sendRedirect("login.jsp");
        } else {
            //放开过滤器，让请求访问到目标地址
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
