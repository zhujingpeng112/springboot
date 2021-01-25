package com.jump.zhu.boot;

import com.jump.zhu.bean.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * today
 * @author jump.zhu
 * @date   2021/1/19 22:18
*/
@RestController
@Data
public class UserController {
    @Value("${user.name}")
    private String userName;
    @Value("${user.age}")
    private Integer age;
    @Value("${user.addr}")
    private String address;

    @Autowired
    private User user;

    @GetMapping("/hello")
    public String hello(){
        return "Hello,"+userName + age + address + user;
    }
    
}
