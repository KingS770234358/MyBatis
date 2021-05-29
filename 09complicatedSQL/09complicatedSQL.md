10. 多对一处理
多个学生 和 一个老师
· 对于学生这边而言, [关联] 多个学生[关联]一个老师
· 对于老师这边而言, [集合] 一个老师有多个学生

环境搭建
· 数据库
USE `mybatis`;
CREATE TABLE `teacher`(
 `id` INT(10) NOT NULL,
 `name` VARCHAR(30) DEFAULT NULL,
 PRIMARY KEY (`id`) 
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `teacher`(`id`,`name`) VALUES
(1,'齐老师')
CREATE TABLE `student`(
 `id` INT(10) NOT NULL,
 `name` VARCHAR(30) DEFAULT NULL,
 `tid` int(10) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `fktid` (`tid`),
 CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `student`(`id`,`name`,`tid`) VALUES
(1,'狂神','1'),
(2,'张三','1'),
(3,'李四','1'),
(4,'林俊杰','1'),
(5,'周杰伦','1')
Navicat 右键数据库 可逆向导出数据库到模型

编写数据库配置文件 database.properties
编写mybatis-config.xml核心配置文件 mybatis-config.xml
编写sqlSession工具类

编写数据库对应的书体类
编写实体类对应的接口
编写接口对应的Mapper
· [这里选择放在resource目录下 包名与接口所在包名最好相同 才能在target中合并]
error3.[本质上是使用注解开发 跟resource文件夹下.xml文件无关]
在mybatis-config.xml中注册实体类接口实现.xml文件时 使用resource属性一直失败
       最后只能使用class属性 
       <mapper class="com.wq.dao.TeacherMapper"/>
       <mapper class="com.wq.dao.StudentMapper"/>
error4.写mapper.xml文件的时候 namespace一定不要写错 是[接口的全限定名称]
       运行完之后,就会resource下和java下的同名包就会合并到target中
<mapper namespace="com.wq.dao.StudentMapper">
· [将mybatis-config.xml文件改编成Mapper.xml文件]
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
</configuration>
==========改成=========>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper>
</mapper>

error1.因为建实体类接口的时候 忘记com.wq.pojo 直接 wq.pojo导致注册的时候一直失败
error2.不管是否使用log4j 还是引入log4j的配置文件 (因为父工程导入了包) 否则会报错 log4j没有初始化

##################################################
# 提出新的需求:找出所有学生的老师的名字[多对一]
##################################################
sql 语句: select s.id, s.name, t.name from student s,teacher t where s.tid = t.id
在学生接口中新增方法
·子查询方法
·联表查询方法

##################################################
# 提出新的需求:找出所有学生的老师的名字[一对多]
##################################################


