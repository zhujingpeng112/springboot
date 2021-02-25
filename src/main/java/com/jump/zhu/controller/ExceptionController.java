package com.jump.zhu.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/e")
public class ExceptionController {
    String a;
    @RequestMapping("/show1")
    public String show1(Model model){
        model.addAttribute("msg","早上好！！！！   1111");
        a.toString();
        return "show1";
    }
}
