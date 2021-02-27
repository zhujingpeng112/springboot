package com.jump.zhu.service;

import com.jump.zhu.pojo.User;

import java.util.List;

public interface UserService {
    List<User> query();
    Integer addUser(User user);
    User queryById(Integer id);
    Integer updateUser(User user);
    Integer deleteUser(Integer id);
}
