package com.wq.dao;

import com.wq.pojo.User;

import java.util.List;

/***
 * 用来操作实体的接口
 * 以后都要写成Mapper
 */
public interface UserDao {
    List<User> getUserList();
}
