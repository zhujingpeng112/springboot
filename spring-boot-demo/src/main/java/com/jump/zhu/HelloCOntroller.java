package com.jump.zhu;


import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCOntroller {
    @Autowired
    RedissonClient redissonClient;


    @GetMapping("say")
    public String say(){
        RBucket bu = redissonClient.getBucket("name");
        if(bu.get()==null){
            bu.set("jump.com");
        }
        return redissonClient.getBucket("name").get().toString();
    }

}
