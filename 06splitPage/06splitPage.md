7.分页
为什么要分页?
· 数据量太大的话,要差很久
· 减少数据的处理量

JDBC使用Limit分页
语法:SELECT * from user limit startIndex, pageSize;
[只传入一个参数的时候,是pageSize]
SELECT * from user limit pageSize;
[查询直到结束,曾经是个BUG,已经修复,不可使用]
SELECT * from user limit startIndex, -1;

使用Mybatis实现分页,核心S   QL语句
1.接口
2.Mapper.xml
3.测试

#############################
# 面向对象实现RowBounds
##############################
不使用sql语句实现分页
1.接口 
List<User> getUserByRowBounds();
2.mapper.xml
3.测试

#################################
# 分页插件-PageHelper(可以看文档)
#################################
1.父工程pom.xml中引入jar包
2.mybatis核心配置xml文件中配置插件<plugin>

==========================>本质都[Limit]
