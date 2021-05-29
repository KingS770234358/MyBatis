12.4 Mybatis缓存原理
PooledDataSource forcefully closed/removed all connections.
[Cache Hit Ratio com.wq.dao.UserMapper: 0.0]
Opening JDBC Connection
Created connection 684566052.
==>  Preparing: select * from user where id = ? 
==> Parameters: 1(Integer)
<==    Columns: id, name, pwd
<==        Row: 1, 狂神3.0, 99999
<==      Total: 1
User(id=1, name=狂神3.0, pwd=99999)
Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@28cda624]
Returned connection 684566052 to pool.
[Cache Hit Ratio com.wq.dao.UserMapper: 0.5]
User(id=1, name=狂神3.0, pwd=99999)
false

[Cache Hit Ratio com.wq.dao.UserMapper: 0.5]
说明是在二级缓存中查找,查找两次,命中一次,所以0.5

##############################################
#         自定义缓存 高级缓存 ehcache    redis
##############################################
ehcache是一种广泛使用的java分布式缓存, 主要面向通用缓存

在程序中使用它。
1.pom.xml中导入 mybatis-ehcache
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-ehcache
     导入使用ehcache需要的包
-->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.0.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12
     解决slf4j和log4j不兼容问题
-->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.21</version>
</dependency>
2.在mapper.xml文件中使用<cache type=""/>进行配置
<!-- 在当前Mapper.xml namespace下中使用二级缓存 -->
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>

或者进行[引用缓存]
<!-- 引用缓存：namespace：指定和哪个名称空间下的缓存一样 -->
<cache-ref namespace="com.atguigu.mybatis.dao.EmployeeMapper"/>

3.缓存的配置 (ehcache.xml)
#####################################################################
[· <cache name="com.wq.dao.UserMapper" />的name要
和<mapper namespace="com.wq.dao.UserMapper"/>的namespace取值一样
才能实现不同的mapper使用不同的缓存(缓存的指定)]
· 在主程序中查看是否存对了缓存
[从自定义的路径中加载配置文件]
CacheManager cacheManager = \
CacheManager.create("./src/main/resources/ehcache_custom.xml");
[添加一个缓存,设置为defaultCache中的设置(不能重名)]
Cache cache = cacheManager.getCache("defaultCache");
cacheManager.addCache("com.wq.dao.UserMapper");
[获得正在使用的缓存]
Cache cache = cacheManager.getCache("com.wq.dao.UserMapper");
[查看缓存中数量的大小]
cache.getSize()
[刷新缓存 将内存的数据保存到磁盘, 清空内存保存的数据 默认执行]
cache.flush();
[关闭缓存管理器 会删除保存在磁盘的缓存文件]
cacheManager.shutdown();
[自己实现Cache接口]
public class Mycache implements Cache{
[工作需要知道使用Redis做缓存数据库]
#####################################################################