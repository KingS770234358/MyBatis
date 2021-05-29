package com.wq.dao;

import com.wq.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {
    // 接口的定义方式 不用public
    // 获取老师
    List<Teacher> getTeacher();

    //找出某个指定老师的所有信息以及他的所有学生
    Teacher getTeacherStudents(@Param("tid") int id);
    // 子查询方式
    Teacher getTeacherStudents2(@Param("tid") int id);
}
