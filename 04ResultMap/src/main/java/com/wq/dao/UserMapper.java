package com.wq.dao;

import com.wq.pojo.User;

import java.util.List;

/**
 * 数据库中对象的封装
 * */
public interface UserMapper {
//    // 查询全部用户
//    List<User> getUserList();
    // 根据ID查询用户
    User getUserById(int id);
//    // 插入一个用户
//    int addUser(User user);
//    // 修改一个用户
//    int updateUser(User user);
//    // 删除一个用户
//    int deleteUser(int id);
}
