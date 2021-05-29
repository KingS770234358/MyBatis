package com.wq.pojo;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;

    // 一个老师对应多个学生 一个学生对应一个老师
    private int tid;
}
