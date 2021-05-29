########################################
# 高频面试题
# Mysql引擎
# InnoDB底层原理
# 索引
# 索引优化
########################################

########################################
# 提出新的需求:找出某个指定老师的所有学生
########################################
errors:
1.collection property 忘记写上类的属性名
2.where s.tid = t.id and t.id = #{tid} s.tid 写成s.id导致只有一条数据

<!--=================2子查询方式实现=======================-->
<!-- 理解:最开始取调用getTeacherStudents2方法,但是它的返回结果resultMap里需要先执行另一个select
     getTeacherStudents2可以将目前执行的结果 传递给select(也就是下面的column="id")
     select执行完毕后返回了一个集合,所以resultMap中要指定javaType="ArrayList" ofType="Student"
     由此,得到了最终的执行结果
 -->
<select id="getTeacherStudents2" resultMap="TeacherStudents2">
    <!-- 因为使用了@Param("tid") 所以这里要写成 #{tid}-->
    select * from teacher where id = #{tid}
</select>
<!-- 结果映射 -->
<resultMap id="TeacherStudents2" type="Teacher">
    <!-- 这里要指定Teacher的id 否则id为0 -->
    <result column="id" property="id"/>
    <!-- ·上面之所以不需要写 javaType="ArrayList" 是因为上面是一个个取出来的 放进集合中
         ·这里之所以要写 是因为先执行了子查询查到所有的学生,当做一个集合结果返回给上层父语句
         ·这里column是指上面父层sql语句执行的结果取出id这个column作为 select=getStudentsByTeacherId的参数
         ·这里property 主要还是和select="getStudentsByTeacherId"语句的执行结果(javaType ofType)对应起来
    -->
    <collection property="students" column="id" select="getStudentsByTeacherId" javaType="ArrayList" ofType="Student" />
</resultMap>
<select id="getStudentsByTeacherId" resultType="Student">
    select * from student where tid = #{tid}
</select>