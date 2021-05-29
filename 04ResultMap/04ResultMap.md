04在mapper.xml sql语句返回值的地方使用ResultMap类型(结果集映射)
解决实体类的属性名和数据库中字段名不一致的问题
新建一个项目,实体类的属性名和数据库中字段名不一致的情况
数据库中 id name pwd        pwd as password
实体类中 id name password 

· 使用结果集映射 
1.在mybatis-config.xml定义要映射类的全限定名的别名(User)
2.建立类和Map之间的映射 <!-- 完成属性都是单一类型的类的映射 --> 当属性有对象时,需要嵌套
id 是映射名 [type可以使mybatis-config.xml里的类的别名]
<resultMap id="UserMap" type="User">
[column对应数据库中的列名 property对应实体类中的属性名]
<!--    <result column="id" property="id"/>  -->
<!--    <result column="name" property="name"/>  -->
[可以只映射不一样的字段]
<!--    <result column="pwd" property="password"/>  -->
</resultMap>
3.在Sql语句中将要返回的 resultType 改成 resultMap
否则会报错:Caused by: java.lang.ClassNotFoundException: Cannot find class: UserMap
