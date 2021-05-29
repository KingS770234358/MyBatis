12.缓存

12.1 简介
连接数据库查询耗费资源
缓存:一次查询结果放入内存中可以直接获取到的地方
再次查询相同数据的时候,直接走缓存,不用连接数据库
[准确定义]:放在内存中的临时数据
====> 减少与数据库交互的次数, 减少系统开销,
提高查询效率, 解决高并发系统的性能问题
[缓存对数据库的要求]:经常查询,但不经常改变的数据库,
这也是一般数据库的特性
[详见背景png]
多个用户 多态服务器 解决方式存在读写的并发问题
用户       服务器                       数据库
用户 ----- 服务器 ----- 缓存服务器 ----- 数据库
用户       服务器                       数据库
[读写分离,主从复制]:保证多个数据库里面的数据一致

12.2 mybatis缓存
在mybatis核心配置文件中可以方便的定制和配置缓存
默认定义了两级缓存: [一级缓存] 和 [二级缓存]
·默认情况下只有[一级缓存]打开[SqlSession级别的缓存,也称本地缓存]
·二级缓存需要手动开启和配置,是基于[namespace级别,也就是接口,Mapper]的缓存
·扩展性, Mybatis定义了缓存接口Cache, 通过实现Cache接口来
 自定义[二级缓存]
-----------------------------------------------------
12.2.1 一级缓存
·与数据库同一次回话期间查询到的数据会放在本地缓存中
·以后如果要获取相同的数据,直接从缓存中拿, 没必要在查询数据库
[一次会话]:从数据库连接池中取得连接(SqlSession)直到SqlSession关闭连接返回池里

12.2.2 二级缓存
· 二级缓存也叫全局缓存
· 工作机制:
一个会话查询一条数据,这个数据就会被放在当前会话的一级缓存中;
若当前会话关闭,会话对应的一级缓存也没了,一级缓存中的数据保存到二级缓存中;
新的会话查询信息可以从二级缓存中获取内容;
不同的Mapper[namespace级别的查询]数据会放在自己对应的缓存Map中
接口:点击行号上的绿点可以展开这个接口的实现类[缓存策略]
public interface Cache {
    String getId(); // 缓存ID
    void putObject(Object var1, Object var2);// 放入
    Object getObject(Object var1); // 取出
    Object removeObject(Object var1); // 删除
    void clear(); // 清除
    int getSize(); // 获得大小
    default ReadWriteLock getReadWriteLock() {
        return null;
    }
}

12.3 测试
[1.要开启日志方便查看]
=======================
[2.一次会话中连续查询两次]
UserMapper umapper = sqlSession.getMapper(UserMapper.class);
User u = umapper.getUserById(1);
System.out.println(u);
System.out.println("===============================");
User u2 = umapper.getUserById(1);
System.out.println(u2);
System.out.println("通过u是否等于u2来判断两个对象是否是同一个对象");
System.out.println(u==u2);
[日志输出结果,只使用了一次Sql语句]
Opening JDBC Connection
Created connection 1293226111.
==>  Preparing: select * from user where id = ? 
==> Parameters: 1(Integer)
<==    Columns: id, name, pwd
<==        Row: 1, 狂神3.0, 99999
<==      Total: 1
User(id=1, name=狂神3.0, pwd=99999)
===============================
User(id=1, name=狂神3.0, pwd=99999)
通过u是否等于u2来判断两个对象是否是同一个对象
true
Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4d15107f]
Returned connection 1293226111 to pool.
[3.缓存失效的情况]
·查询不一样的东西
·所有的insert update delete操作都会刷新缓存, 原来缓存里的东西失效
 所有的增删改操作都有可能改变原有的数据, 所以必定会刷新缓存
·查询不同的Mapper.xml(不需要测试就知道)
·根据策略清除不需要的缓存, 也可以手动清除缓存
·缓存自身会不定时的进行刷新(没有刷新间隔)
·缓存会保存列表或者对象(无论查询方法返回哪种)的1024个引用
·[缓存会被视为读/写缓存,意味着获取到的对象并不是共享的,可以安全地被
调用者修改,而不影响其他调用者或线程所作的潜在修改]
======>[一级缓存小结]:一级缓存默认是开启的,只在一次SqlSession中有效,
也就是从拿到连接,到关闭连接这个时间区间内。
一级缓存相当于一个map,可以debug测试查看

[二级缓存测试]
在核心配置文件显式设置,在Mapper.xml中加入<cache/>即可
###############################################################################
# 只使用<cache/>时, 报错Cause: java.io.NotSerializableException: com.wq.pojo.User
# 要User类实现序列化接口 详见User类 public class User implements Serializable {
###############################################################################
也可以在Mapper.xml中单个语句标签里使用[useCache属性指定是否缓存]
关于<cache/>的一些自定义的设置
<cache 
eviction="FIFO"
flushInterval="60000"
size="512"
readOnly="true"/>
解析:创建一个FIFO缓存, 每隔60s刷新一次, 
最多可以存储结果对象或者列表512个引用
返回的对象默认是只读的,因此若对他们进行修改,可能会在不同线程中的调用者产生冲突
[清除缓存的策略:都实现了上述Cache接口的方法]
LRU(默认):移除最长时间不被使用的对象;
FIFO:先进先出, 按对象进入缓存的先后顺序移除;
SOFT:软引用-基于垃圾回收器状态和软引用规则移状态;
WEAK:弱引用-更积极的基于垃圾回收器状态和软引用规则移状态
Opening JDBC Connection
Created connection 1396721535.
==>  Preparing: select * from user where id = ? 
==> Parameters: 1(Integer)
<==    Columns: id, name, pwd
<==        Row: 1, 狂神3.0, 99999
<==      Total: 1
User(id=1, name=狂神3.0, pwd=99999)
Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@5340477f]
Returned connection 1396721535 to pool.
[Cache Hit Ratio com.wq.dao.UserMapper]: 0.5
User(id=1, name=狂神3.0, pwd=99999)
true
======>[二级缓存小结]:只要开启了二级缓存,在同一个Mapper下都有效
所有的数据都会先放在一级的缓存中
只有当SqlSession(会话)[提交或者关闭]的时候,才会提交[转存]到二级缓存中

