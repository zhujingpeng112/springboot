package com.jump.zhu.controller;

import com.jump.zhu.pojo.User;
import com.jump.zhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * today
     * 基础跳转请求
     * @params String page
     * @return java.lang.String
     * @author jump.zhu
     * @date   2021/2/27 13:07
    */
    @RequestMapping("/user/{page}")
    public String toAdd(@PathVariable String page){
        return page;
    }


    @RequestMapping("/user/query")
    public List<User> query(){
        return userService.query();
    }

    @RequestMapping("/user/query1")
    public String query1(Model model){
        model.addAttribute("list",userService.query());
        return "user1";
    }

    @RequestMapping("/user/add")
    public String add(User user){
        userService.addUser(user);
        return "redirect:/user/query1";
    }

    @RequestMapping("/user/updateInfo")
    public String updateInfo(Integer id,Model model){
        model.addAttribute("user",userService.queryById(id));
        return "updateUser";
    }

    @RequestMapping("/user/update")
    public String update(User user){
        userService.updateUser(user);
        return "redirect:/user/query1";
    }

    @RequestMapping("/user/delete")
    public String delete(Integer id){
        userService.deleteUser(id);
        return "redirect:/user/query1";
    }
}
