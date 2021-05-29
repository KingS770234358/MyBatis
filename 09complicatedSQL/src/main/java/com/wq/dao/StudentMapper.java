package com.wq.dao;

import com.wq.pojo.Student;

import java.util.List;

public interface StudentMapper {

    public List<Student>getStudents();
    // 查询所有学生的信息, 以及他们对应的老师的名字！
    public List<Student>getStudents2();

}
