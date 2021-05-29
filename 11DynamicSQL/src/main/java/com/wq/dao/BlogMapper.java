package com.wq.dao;

import com.wq.pojo.Blog;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    // 插入博客
    // 用注解Sql 不能设置数据类型 还是采用mapper.xml
    // @Insert("insert to blog(id, title, author, create_time, views) values (#{}, #{}, #{}, #{}, #{})")
    int addBlog(Blog blog);

    // 查询博客 使用map代入条件查询
    List<Blog> queryBlogIF(Map map);

    List<Blog> queryBlogIF2(Map map);
    // 使用Trim的set标签更新博客
    int updateBlogUsingSet(Map map);

    // 查询博客 使用map代入条件查询
    List<Blog> queryBlogChoose(Map map);

    // 查询id在集合[1,2]中的博客 (把数据库中的UUID改成1 2 3了)
    List<Blog> queryBlogForeach(Map map);

    List<Blog> queryBlogForeach2(Map map);
}
