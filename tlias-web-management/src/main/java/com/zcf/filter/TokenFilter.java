package com.zcf.filter;

import com.zcf.utils.CurrentHolder;
import com.zcf.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
@Component
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断是否是登录请求，如果路径包含/login，放行
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，如果不存在，返回错误信息
        if (token == null|| token.isEmpty()) {
            log.info("token不存在，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5.校验令牌，校验失败则返回错误信息
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工ID:{},将其存入ThreadLocal",empId);

        } catch (Exception e) {
            log.info("token校验失败，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6.校验通过放行
        log.info("token校验通过，放行");
        filterChain.doFilter(request, response);

        //7.移除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
