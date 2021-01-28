package com.jump.zhu.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "user")
public class User {


    private Integer id;

    private String userName;

    private Integer age;

    private String addr;

    public User() {
    }

    public User(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

}
