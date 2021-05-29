package com.wq.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/***
 * 一个mybatis的工具类
 * 用来获得sqlSessionFactory实例
 * 从而构建执行sql语句的sqlSession
 */
public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory;
    static{
        // 获取sqlSessionFactory对象
        // 从resource目录算起
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过SqlSessionFactoryBuilder()构建一个sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    // 从sqlSessionFactory获得sqlSession实例 用来执行各种Sql语句
    public static SqlSession getSqlSession(){
        // openSession() 获得sqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
