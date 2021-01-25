package com.jump.zhu.filter;

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter second befor....");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("doFilter second after....");
    }

    @Override
    public void destroy() {
        System.out.println("destroy....");
    }

}
