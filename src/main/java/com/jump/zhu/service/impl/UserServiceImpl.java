package com.jump.zhu.service.impl;

import com.jump.zhu.mapper.UserMapper;
import com.jump.zhu.pojo.User;
import com.jump.zhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> query() {
        return mapper.query();
    }

    @Override
    public Integer addUser(User user) {
        return mapper.addUser(user);
    }

    @Override
    public User queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return mapper.update(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return mapper.deleteUser(id);
    }
}
