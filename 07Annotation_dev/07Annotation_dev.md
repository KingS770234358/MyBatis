8.面向接口编程
[解耦  定义与实现的分离]
直接在接口的方法上写注解+SQL语句即可
不用接口对应的mapper.xml类,也不用在mybatis-config.xml mapper中注册
#######################################################
# [但要在mybatis-config.xml 绑定接口 也就是绑定这个注解] #
#######################################################

缺陷:
当存实体类的属性名和数据库中的字段名不一致的时候, [无法使用resultMap解决了]

可以打点DEBUG看下SqlSession里面都有哪些东西
configuration [可以发现 mybatis-config.xml核心配置文件中配置的东西都会在这里] 
executor
xxxMapper

DEBUG ==> step over 下一步
设置断点查看这种注解方式如何执行Sql语句
xxxMapper -> h -> methodCache -> key
######################################
[本质:主要使用的是反射!
底层:动态代理实现!]
######################################

· 实现CRUD(在工具类MybatisUtil创建的时候实现自动提交事务)
  在SqlSessionFactory.openSession()查看参数重载 传入true, 实现自动提交事务
  public interface SqlSessionFactory {
      SqlSession openSession();
      SqlSession openSession(boolean var1);
  DEBUG模式中,在sqlSession中的configuration中就有个autocommit的项
  
· 接口中重复定义某个方法 报错:
Caused by: java.lang.IllegalArgumentException: 
Mapped Statements collection already contains value for com.wq.dao.StudentMapper.updateUser. 
please check com/wq/dao/UserMapper.java (best guess) and com/wq/dao/UserMapper.java (best guess)

#################################
# sql中#{}取参和${}取参的区别
#################################
eg. delete from user where id = #{id, jdbcType=INTEGER}
· #{}取参时, sql解析的时候会自动加上" ", 当成字符串来解析, id = "id" 
  能够很大程度上防止sql注入
· ${}取参传入的数据直接显示在sql语句中, 上述语句解析为 id = id, 执行会报错
  无法防止sql注入, 一般用于传入数据库对象, 比如数据库表名
====> 尽量使用#{}取参