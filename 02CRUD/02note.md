/**
*   基于mybatis实现增删改查
*   CRUD Create Retrieve Update Delete
*/
error1:mapper.xml重复绑定接口中的某个方法
Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. 
Cause: org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. The XML location is 'com/wq/dao/UserMapper.xml'. 
Cause: java.lang.IllegalArgumentException: Mapped Statements collection already contains value for com.wq.dao.StudentMapper.getUserList. 
please check com/wq/dao/UserMapper.xml and com/wq/dao/UserMapper.xml

####################################
#error2:往数据库中插入数据但是没有效果
#增删改需要提交事务!!!
####################################

error3:
namespace上一定写接口的全限定名 以.分割每一级
在mybatis-config.xml中注册的时候resource绑定路径才用/分割

error4:mapper.xml里面 语句要和标签匹配
查询语句要放在查询语句标签里面
插入语句要放在插入语句标签里面
...

error5:mybatis-config.xml中数据库的配置不能有一丝一毫的差错,多个"号也不可以
读控制台报错 要从下往上读

error6:SqlSession空指针异常
在MybatisUtil工具类中,在try块外面声明了 private static SqlSessionFactory sqlSessionFactory;
不要再在try 块里面再声明 private static SqlSessionFactory sqlSessionFactory;
因为 return sqlSessionFactory.openSession(); 
return的是最先声明的那个sqlSessionFactory获得的SqlSession

error7:target文件中输出的mapper.xml文件中存在乱码

error8:There is no getter for property named 'idddd' in 'class com.wq.pojo.Teacher'
<!-- insert不需要returnType参数 -->
<insert id="addUser3" parameterType="com.wq.pojo.Teacher">
    <!-- 当SQL语句的参数使用类来接收的时候,#{}中的参数名要和类名中的属性一致,
         属性数量可以不一致
     -->
    insert into mybatis.user (id, name,pwd) values (#{idddd},#{nameeee},#{pwdddd});
</insert>

新增功能步骤:
1.编写接口
2.在mapper.xml中添加sql语句
3.测试

万能Map方式给Sql语句传参
若实体类/或数据库中的表 字段或者参数过多,考虑使用map传参
 * 使用万能map传参 可任意控制参数/属性个数
 * #{}里的参数名要和map的键名一一对应
####################################
# #使用map传参可以完成任意的传参
####################################

<!-- 模糊查询1 在Sql语句拼接中使用通配符写死 -->
<select id="getUserLike" resultType="com.wq.pojo.Teacher">
    
    select * from user where name like "%"#{value}"%";
</select>
<!-- 模糊查询2 java代码执行的时候 传递通配符 -->
<select id="getUserLike2" resultType="com.wq.pojo.Teacher">
    select * from user where name like #{value};
</select>