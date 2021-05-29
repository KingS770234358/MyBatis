package com.wq.dao;

import com.wq.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    /*
        1.当需要传入引用对象为参数的时候 就不用加了
        2.传入[多个]基本数据类型为参数 所有参数前面必须加上@Param
        User getUserById(@Param("id") int id,@Param("name") String name);
        3.#{id} 里放的参数名 以这个注解里的为准 @Param("id")
          包括mapper.xml文件里的参数也要以接口里@Param()里定义的参数名进行取参
        4.当参数只有一个的时候mapper.xml文件里的参数#{}可以任意写
     */
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int userId);

    // 添加用户
    // 前面user (id,name,pwd)以数据库里的字段为准
    // 后面 values (#{id},#{name},#{pwd}) 以实体类里的属性名为准
    @Insert("insert into user (id,name,pwd) values (#{id},#{name},#{pwd})")
    int addUser(User user);

    // 修改
    @Update("update user set name=#{name}, pwd=#{pwd} where id=#{id}")
    int updateUser(User user);

    // 删除
    @Delete("delete from user where id=#{uid}")
    int deleteUser(@Param("uid") int id);



}
