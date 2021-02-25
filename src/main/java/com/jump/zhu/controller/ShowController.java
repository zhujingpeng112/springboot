package com.jump.zhu.controller;

import com.jump.zhu.bean.User;
import lombok.val;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class ShowController {
    String a;
    @RequestMapping("/showUser")
    public String showUser(Model model){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1,"zhangsan",22));
        list.add(new User(2,"lisi",23));
        list.add(new User(3,"wangwu",24));
        model.addAttribute("list",list);
        return "user";
    }

    @RequestMapping("/show")
    public String show(Model model){
        model.addAttribute("msg","早上好！！！！   1111");
        return "show1";
    }

    @RequestMapping("/show1")
    public String show1(Model model){
        model.addAttribute("msg","早上好！！！！   1111");
        a.toString();
        return "show1";
    }
    @RequestMapping("/show2")
    public String show2(Model model){
        int a=0;
        int b=2;
        int c=b/a;
        model.addAttribute("msg","早上好！！！！   1111");
        return "show1";
    }


    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView nullPointerExceptionHandler(Exception e){
        System.out.println("nullPointerExceptionHandler");        //1
        ModelAndView view = new ModelAndView();
        view.addObject("error",e.toString());
        view.setViewName("error1");
        return view;
    }




}
