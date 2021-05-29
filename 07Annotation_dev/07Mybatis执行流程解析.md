#########
#总结步骤:
#########
0.要在父工程或者自身的工程pom.xml导入依赖
1.首先创建resource目录下,mybatis-config.xml核心配置文件,配置连接数据库所需要的基本信息
2.创建具体的pojo实体类
3.创建pojo实体类相关操作的接口
4.创建实现接口的mapper.xml文件(完成接口实现类 和 接口的绑定)
5.将mapper.xml文件注册到mybatis-config.xml核心配置文件的mapperRegistry
6.获得SqlSession对象
· 首先创建 SqlSessionFactoryBuilder 工厂构造器对象 new SqlSessionFactoryBuilder()
  底层XMLConfigBuilder解析配置文件====>configuration所有的配置信息
  
· 将核心配置文件mybatis-config.xml资源以输入流的形式获得
· 将核心配置文件输入流传入工厂构造器的Build方法流构造工厂[工厂为了构造SqlSession是单例的 一直存在]
inputStream = Resources.getResourceAsStream(resource);
new SqlSessionFactoryBuilder().build(inputStream);
· 工厂构造SqlSession
sqlSessionFactory.openSession();
7.从sqlSession中获得相应的接口,执行接口的方法
  transaction事务缓存管理器<-----|
  创建executor执行器            |
  napperInterface里实现CRUD---->|
  查看是否执行成功-------------->|
  提交事务
  关闭


