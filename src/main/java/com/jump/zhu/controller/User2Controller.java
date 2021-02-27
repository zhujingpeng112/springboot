package com.jump.zhu.controller;

import com.jump.zhu.pojo2.User;
import com.jump.zhu.service.User2Service;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user2")
public class User2Controller {
    @Autowired
    private User2Service user2Service;
    @RequestMapping("/query")
    public List<User> query(){
        return user2Service.query(null);
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","aaa",1024);
        System.out.println(md5Hash);
    }
}
