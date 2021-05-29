package com.wq.dao;

import com.wq.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    // 根据Id查询用户
    User getUserById(@Param("userId") int id);
    int updateUser(User user);
}
