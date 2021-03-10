package com.jump.zhu;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@ConditionalOnClass(Redisson.class)  //装配条件，存在Redisson.classs时装配
@EnableConfigurationProperties(RedissonProperties.class)
@Configuration
public class RedissonAutoConfig {
    
    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties){
        Config config = new Config();
        String prefix="redis://";
        if(redissonProperties.isSsl()){
            prefix="rediss://";
        }
        SingleServerConfig singleServerConfig = config.useSingleServer().
                setAddress(prefix+redissonProperties.getHost()+":"+redissonProperties.getPort()).setConnectTimeout(redissonProperties.getTimeout())
                .setPassword(redissonProperties.getPassword());

        return Redisson.create(config);
    }
}
