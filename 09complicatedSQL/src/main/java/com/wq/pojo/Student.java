package com.wq.pojo;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;

    // 这里通过组合的方式 让学生关联一个老师
    private Teacher teacher;
}
