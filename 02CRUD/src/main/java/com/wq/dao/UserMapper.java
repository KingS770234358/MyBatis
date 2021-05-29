package com.wq.dao;

import com.wq.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * 数据库中对象的封装
 * */
public interface UserMapper {
    // 查询全部用户
    List<User> getUserList();
    // 根据ID查询用户
    User getUserById(int id);
    User getUserByIdAndName(Map<String,Object> map);
    // 模糊查询
    List<User> getUserLike(String value);
    List<User> getUserLike2(String value);
    // 插入一个用户
    int addUser(User user);
    int addUser2(User user);
    int addUser3(User user);
    // 万能的map传参方式
    int addUser4(Map<String,Object> map);
    // 修改一个用户
    int updateUser(User user);
    // 删除一个用户
    int deleteUser(int id);
}
