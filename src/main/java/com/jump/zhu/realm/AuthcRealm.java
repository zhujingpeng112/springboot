package com.jump.zhu.realm;


import com.jump.zhu.pojo2.User;
import com.jump.zhu.service.User2Service;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthcRealm extends AuthorizingRealm {
    @Autowired
    private User2Service service;


    /**
     * today  认证
     * @params No such property: code for class: Script1
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author jump.zhu
     * @date   2021/2/27 16:10
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("11111111");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        System.out.println("userName="+userName);
        User user = new User();
        user.setUsername(userName);
        List<User> list = service.query(user);
        if(list==null || list.size() != 1){
            //账号不存在
            return null;
        }
        user = list.get(0);

        return new SimpleAuthenticationInfo(user,
                user.getPassword(),
                new SimpleByteSource(user.getSalt()),"authcRealm");
    }

    /**
     * today  授权
     * @params No such property: code for class: Script1
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author jump.zhu
     * @date   2021/2/27 16:10
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("222");
        User user = (User) principalCollection.getPrimaryPrincipal();
        System.out.println("授权账号是："+user.getUsername());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("role");
        return info;
    }

}
