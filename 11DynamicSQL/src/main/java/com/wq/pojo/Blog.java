package com.wq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class Blog {
    // 这里的id是个string才对 使用uuid
    private String id;
    private String title;
    private String author;
    // 这里类的属性名 跟数据库中的字段名不一致 需要解决
    // mybatis-config.xml核心配置文件中settings的mapUnderscoreToCamelCase
    private Date createTime;
    private int views;

}
