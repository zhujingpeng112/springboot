package com.jump.zhu.controller;

import com.jump.zhu.bean.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ShowController {

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
        model.addAttribute("msg","早上好！！！！");
        return "show1";
    }

}
