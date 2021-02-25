package com.jump.zhu.boot;

import com.jump.zhu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.query());
    }

}
