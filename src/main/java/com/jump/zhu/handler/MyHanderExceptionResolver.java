package com.jump.zhu.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class MyHanderExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("全局的自定义异常处理触发了。。。。");
        ModelAndView mv = null;
        //view.
        if(e instanceof  NullPointerException){
            mv = new ModelAndView();
            mv.setViewName("error1");
            mv.addObject("error",e.toString());
        }
        return mv;
    }
}
