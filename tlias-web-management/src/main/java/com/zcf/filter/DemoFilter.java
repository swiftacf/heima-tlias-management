package com.zcf.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/emps/*")
@Slf4j
public class DemoFilter implements Filter {

    //1.初始化方法，web服务器启动的时候执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig){
        log.info("init初始化方法");
    }

    //2.核心方法，每次请求都会执行
    @Override
    public void doFilter(ServletRequest ServletRequest, ServletResponse ServletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求.....放行前");
        //放行
        filterChain.doFilter(ServletRequest, ServletResponse);

        log.info("拦截到了响应.......放行后");
    }

    //3.销毁方法，web服务器关闭的时候执行，只执行一次
    @Override
    public void destroy() {
        log.info("destroy销毁方法");
    }
}
