package com.jump.zhu;

import com.jump.zhu.filter.SecondFilter;
import com.jump.zhu.servlet.SecondServlet;
import lombok.val;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@SpringBootApplication
@ServletComponentScan
//@MapperScan("com.jump.zhu.mapper")
public class BootApplication {
    public static void main(String[] args) {
        //ApplicationContext run = SpringApplication.run(BootApplication.class, args);
        SpringApplication springApplication = new SpringApplication(BootApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
    @Bean
    public ServletRegistrationBean getRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new SecondServlet());
        servletRegistrationBean.addUrlMappings("/second");
        return servletRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean getRegistractionBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SecondFilter());
        filterRegistrationBean.addUrlPatterns("/second");
        return filterRegistrationBean;
    }

    //@Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver mapping = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //java.lang.ArithmeticException
        System.out.println("SimpleMappingExceptionResolver");
        mappings.setProperty("java.lang.NullPointerException","error2");
        mapping.setExceptionMappings(mappings);
        return mapping;

    }



}
