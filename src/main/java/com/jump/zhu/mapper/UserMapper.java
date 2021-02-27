package com.jump.zhu.mapper;

import com.jump.zhu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    public List<User> query();
    public Integer addUser(User user);
    public User queryById(Integer id);
    public Integer update(User user);
    public Integer deleteUser(Integer id);
}
