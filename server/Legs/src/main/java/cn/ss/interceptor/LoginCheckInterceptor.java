package cn.ss.interceptor;

import cn.ss.pojo.Result;
import cn.ss.utils.JwtUtils;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        setCorsHeaders(response);

        if (isPreflightRequest(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }

        String jwt = request.getHeader("token");
        if (!isValidToken(jwt)) {
            log.info("请求未携带token，用户未登录");
            response.getWriter().write(JSONObject.toJSONString(Result.err("NOT_LOGIN")));
            return false;
        }

        try {
            Claims claims = JwtUtils.parseJwt(jwt);
            request.setAttribute("claims", claims);
            if (isAdminRequest(request) && !isAdmin(claims)) {
                log.info("用户权限不足，当前用户不为管理员");
                response.getWriter().write(JSONObject.toJSONString(Result.err("NO_PERMISSION")));
                return false;
            }
        } catch (Exception e) {
            log.info("token解析失败，用户未登录");
            response.getWriter().write(JSONObject.toJSONString(Result.err("NOT_LOGIN")));
            return false;
        }

        log.info("用户已登录");
        return true;
    }

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    private boolean isPreflightRequest(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod());
    }

    private boolean isValidToken(String jwt) {
        if (!StringUtils.hasLength(jwt)) {
            return false;
        }
        String tokenInRedis = null;
        try {
            tokenInRedis = redisTemplate.opsForValue().get(jwt);
        } catch (Exception e) {
            log.info("redis服务异常");
            throw new RuntimeException(e);
        }
        return tokenInRedis != null;
    }

    private boolean isAdminRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("admin");
    }

    private boolean isAdmin(Claims claims) {
        return claims.get("type").equals(0);
    }
}