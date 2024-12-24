package com.homework6.interceptor;

import com.homework6.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果是OPTIONS请求，直接放行
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            // 验证token
            if (jwtUtil.validateToken(token)) {
                // 将用户信息添加到请求属性中
                request.setAttribute("userId", jwtUtil.getUserIdFromToken(token));
                request.setAttribute("username", jwtUtil.getUsernameFromToken(token));
                return true;
            }
        }

        // token无效或不存在，返回401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
