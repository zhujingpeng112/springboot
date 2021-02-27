package com.jump.zhu.config;

import com.jump.zhu.realm.AuthcRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;

@Configuration
public class ShiroConfig {
    private String hashAlgorithmName = "md5";
    private Integer hasIterations = 1024;
/**
 * today
 * @params No such property: code for class: Script1
 * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
 * @author jump.zhu
 * @date   2021/2/27 16:28
*/
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(hashAlgorithmName);
        matcher.setHashIterations(hasIterations);
        return matcher;
    }
/**
 * today
 * @params No such property: code for class: Script1
 * @return
 * @author jump.zhu
 * @date   2021/2/27 16:27
*/
    @Bean
    //@DependsOn("hashedCredentialsMatcher")
    public AuthcRealm authcRealm(HashedCredentialsMatcher matcher){
        AuthcRealm realm = new AuthcRealm();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**
     * today
     * @params No such property: code for class: Script1
     * @return org.apache.shiro.mgt.SecurityManager
     * @author jump.zhu
     * @date   2021/2/27 16:27
    */
    @Bean
    //@DependsOn("authcRealm")
    public SecurityManager securityManager(AuthcRealm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    /**
     * today 注册ShiroFilterFactoryBean
     * @params No such property: code for class: Script1
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author jump.zhu
     * @date   2021/2/27 16:27
    */
    @Bean
    //@DependsOn("securityManager")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager manager){
        ShiroFilterFactoryBean filter =  new ShiroFilterFactoryBean();
        filter.setSecurityManager(manager);
        System.out.println("444");
        filter.setLoginUrl("/login.do");
        filter.setSuccessUrl("/success.html");
        filter.setUnauthorizedUrl("/refuse.html");
        //设置过滤器链
        HashMap<String, String> map = new HashMap<>();
        map.put("/css/*","anon");
        map.put("/js/**","anon");
        map.put("/img/**","anon");
        map.put("/login","anon");
        map.put("/login.do","authc");
        map.put("/**","authc");
        filter.setFilterChainDefinitionMap(map);
        return filter;
    }
}
