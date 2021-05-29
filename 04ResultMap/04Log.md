6. 日志
6.1 日志工厂
如果一个数据库操作出现了异常,排错,日志就是最好的助手
曾经:sout 、 debug
现在:日志工厂
mybatis-config.xml
settings-setting-logImpl
·SLF4J
·LOG4J [掌握]
·LOG4J2
·JDK_LOGGING
·COMMONS_LOGGING
·STDOUT_LOGGING [掌握]
·NO_LOGGING
具体使用哪个日志,由mybatis-config.xml中的设置决定
############################
# ·STDOUT_LOGGING [掌握]
############################
标准日志输出,什么都不用配置
Opening JDBC Connection
Created connection 477533894.
Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1c7696c6]
==>  Preparing: select * from user where id = ?; 
==> Parameters: 1(Integer)
<==    Columns: id, name, pwd
<==        Row: 1, 狂神, 123456
<==      Total: 1
User{id=1, name='狂神', password='123456'}
Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1c7696c6]
Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1c7696c6]
Returned connection 477533894 to pool.

############################
# ·LOG4J [掌握]
############################
一、什么是log4j
log4j是Apache的一个开源项目,通过使用log4j可以控制日志信息
· 输出到[控制台、文件、GUI组件]
· 控制每一条日志的输出格式
· 通过定义每一条日志信息的级别,更加细致地控制日志的生成过程
· 通过配置文件灵活的配置,不影响应用的代码
1.先在父工程中导入包pom.xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
2.在resource中新增log4j的配置文件log4j.properties
3.在mybatis-config.xml中配置 LOG4J 日志工厂
4.log4j的使用(直接运行测试)
使用什么适配器
16:05:54,431 DEBUG LogFactory:105 - Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
16:05:54,434 DEBUG LogFactory:105 - Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
....
16:05:54,612 DEBUG JdbcTransaction:136 - Opening JDBC Connection
16:05:54,828 DEBUG PooledDataSource:424 - Created connection 125994398.
16:05:54,828 DEBUG JdbcTransaction:100 - Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@782859e]
16:05:54,833 DEBUG getUserById:143 - ==>  Preparing: select * from user where id = ?; 
16:05:54,873 DEBUG getUserById:143 - ==> Parameters: 1(Integer)
16:05:54,912 DEBUG getUserById:143 - <==      Total: 1
User{id=1, name='狂神', password='123456'}
16:05:54,924 DEBUG JdbcTransaction:122 - Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@782859e]
16:05:54,925 DEBUG JdbcTransaction:90 - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@782859e]
16:05:54,925 DEBUG PooledDataSource:381 - Returned connection 125994398 to pool.

# 输出DEBUG级别以上的日志
log4j.appender.logfile.Threshold = DEBUG
2020-01-22 16:19:45  [ main:0 ] - [ DEBUG ]  debug:进入了testLog4j
2020-01-22 16:19:45  [ main:4 ] - [ INFO ]  info:进入了testLog4j
2020-01-22 16:19:45  [ main:4 ] - [ ERROR ]  error:进入了testLog4j
# 输出INFO级别以上的日志
log4j.appender.logfile.Threshold = INFO
2020-01-22 16:21:10  [ main:4 ] - [ INFO ]  info:进入了testLog4j
2020-01-22 16:21:10  [ main:4 ] - [ ERROR ]  error:进入了testLog4j
但是控制台的输出:
16:21:10,546 DEBUG Mytest:43 - debug:进入了testLog4j
16:21:10,550  INFO Mytest:44 - info:进入了testLog4j
16:21:10,550 ERROR Mytest:45 - error:进入了testLog4j

简单使用:
[log4j不是针对mybatis使用的,任何需要日志的应用到可以使用
在mybatis的信息中也分为DEBUG INFO WARN ERROR FATAL多个级别,也会把日志信息输入到对应的日志文件中去
以下是在不使用mybatis的情况下使用log4j]
1.在要使用Log4j的类中,导入包
// 这里导包一定要导log4j下的logger
import org.apache.log4j.Logger;
###################################################
# 2.生成日志对象,加载参数为当前类的class
# Logger logger = Logger.getLogger(Mytest.class);
##################################################
##################3################
# ######### 输出日志的格式 #########
###################################
%-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
2020-01-22 16:31:34  [ main:4 ] - [ ERROR ]  error:进入了testLog4j
%m 输出代码指定的消息
%M 输出打印该条日志的方法名
%p 输出日志优先级 DEBUG INFO WARN ERROR FATAL [%5p 占位5个位子]
%r 输出自应用启动到输出该日志信息耗费的毫秒数
%c 输出所属的类目,通常就是所在类的全名
%t 输出产生该日志时间的线程名
%n 输出一个回车换行符,Windows平台为rn,Unix平台为n
%d 输出日志时间点的日期或时间,默认格式为ISO8601,也可以在其后指定格式%d{yyyy-MM-dd HH:mm:ss,SSS} 
%l 输出日志时间的发生位置,即在代码中的行号
[QC]是log信息的开头,可以为项目简称
[TS]DEBUG[main]

############################################################
# mybatis-config 设置之后是如何加载log4j的配置文件的
############################################################
(1) 自动加载配置文件
log4j启动时，默认会寻找source folder(resource)下的log4j.xml配置文件
若没有，会寻找source folder(resource)下的log4j.properties文件。然后加载配置
(2) 若配置文件名不为 log4j.xml log4j.properties则需要手动加载配置文件
PropertyConfigurator.configure("config/../../log4j.properties") 默认读取的是项目根目录的路径
(3)项目打成jar包时，一般不会把配置文件也打进jar包。
· 如果是第一种方式，直接将log4j.properties文件和生成的HelloWorld.jar放在同一目录下，项目就能顺利读取配置文件。
· 如果是第二种方式，要建立相应的config/../../文件夹，把配置文件放入其中
  再将config文件和生成的HelloWorld.jar放在同一目录下，项目就能顺利读取配置文件。
(4)思考：log4j.properties配置文件，配置简单，但不支持复杂过滤器filter.
log4j.xml虽然配置文件看似复杂，但支持复杂过滤器和Log4j的新特性。[推荐使用log4j.xml]




