package com.jump.zhu.mapper;

import com.jump.zhu.pojo2.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface User2Mapper {
    public List<User> query(User user);
}
