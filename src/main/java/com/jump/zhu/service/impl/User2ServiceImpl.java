package com.jump.zhu.service.impl;

import com.jump.zhu.mapper.User2Mapper;
import com.jump.zhu.pojo2.User;
import com.jump.zhu.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class User2ServiceImpl implements User2Service {

    @Autowired
    private User2Mapper user2Mapper;

    @Override
    public List<User> query(User user) {
        return user2Mapper.query(user);
    }
}
