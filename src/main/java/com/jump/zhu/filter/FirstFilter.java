package com.jump.zhu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(urlPatterns = "/first")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter befor....");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("doFilter after....");
    }

    @Override
    public void destroy() {
        System.out.println("destroy....");
    }
}
