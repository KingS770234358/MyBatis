12.动态SQL
·什么是动态SQL:动态SQL就是[根据不同的条件生成不同的SQL语句]
利用动态SQL可以摆脱[SQL语句拼接]的痛苦(不能忘记添加必要的空格, 去掉最后一个逗号)
动态的传入查询条件, 查询可以按[任意的数据库字段,可以一个 可以两个 多个 等等]进行筛选
[在SQL层面加入一些代码逻辑, 本质上还是在进行SQL语句的拼接]
建议:在mysql中先写出可用的完整sql语句, 在对应地去修改成为动态SQL实现通用

·主要学习一下语法
if
choose(when, otherwise)
trim(where, set)
foreach

·搭建环境
建立数据库
CREATE TABLE `blog`(
    `id` varchar(50) NOT NULL COMMENT '博客id',
    `title` varchar(100) NOT NULL COMMENT '博客标题',
    `author` varchar(30) NOT NULL COMMENT '博客作者',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `views` int(30) NOT NULL COMMENT '浏览量'
)ENGIN=InnoDB DEFAULT CHARSET=utf8

·创建一个基础工程
1.导包
2.编写配置文件
3.编写实体类
import lombok.Data;
// 选择Date包
import java.util.Date;
@Data
public class Blog {
    private int id;
    private String title;
    private String author;
    private Date createTime;
    private int views;
}
4.编写实体类对应的Mapper接口和Mapper.xml文件

####################################################################
# 解决 pojo中类属性 createTime 和数据库中字段 create_time不一致的情况
# mybatis-config.xml核心配置文件中settings的mapUnderscoreToCamelCase
####################################################################

################################################
#<!-- 父工程中scope限定了junit的作用范围
#     这里将它扩充到 compile 在main目录下也可以使用
#     compile test runtime provided system
#-->
#<dependency>
#    <groupId>junit</groupId>
#    <artifactId>junit</artifactId>
#    <version>4.13</version>
#    <scope>compile</scope>
#</dependency>
##################################################

· IF的使用 可以一个参数都不传入
<select id="queryBlogIF" parameterType="map" resultType="Blog">
    <!-- 这里的where 1=1是为了防止出现 where后面直接接上AND OR等连接词的情况 -->
    select * from mybatis.blog where 1=1
    <!-- 使用动态SQL语句进行拼接
         test可以理解为尝试进行一次判断
         test必须填写
    -->
    <if test="id!=null">
        and id=#{id}
    </if>
    <if test="title!=null">
        and title like "%"#{title}"%"
    </if>
</select>

· TRIM(where, set) 
[where标签的作用],[删除条件语句之间冗余的AND OR 等连接词]
当条件部份不为空的时候 才会插入一个where
[set标签的作用], 动态前置set关键词,[删除字段之间无关的逗号]
[trim爸爸的作用]:prefix指定内容 prefixOverrides 前缀去除 suffixOverrides 后缀去除
定制where的功能
<trim prefix="WHERE" prefixOverrides="AND |OR">
</trim>
定制set的功能
<trim prefix="SET" suffixOverrides=",">
</trim>
<select id="queryBlogIF2" parameterType="map" resultType="Blog">
    <!-- 使用where标签 代替不正规的where 1=1写法
         (去掉了第一个判断条件前面的and) -->
    select * from mybatis.blog
    <where>
        <if test="id!=null">
            id=#{id}
        </if>
        <if test="title!=null">
            and title like "%"#{title}"%"
        </if>
    </where>
</select>

· choose when otherwise otherwise的作用:至少传入一个参数
(类比java的switch语句 choose=> switch, when=>case, otherwise=>default)
来选择执行那个sql语句:
只会执行最先满足的那个语句,不执行其他语句
当所有的语句都不满足时,会走otherwise语句
    <select id="queryBlogChoose" parameterType="map" resultType="Blog">
        select * from mybatis.blog
        <where>
            <!-- 只会执行最先满足的那个语句,不执行其他语句
                 当所有的语句都不满足时,会走otherwise语句
            -->
            <choose>
                <when test="title!=null">
                    <!-- 使用了where标签 第一个不加and -->
                    title like "%"#{title}"%"
                </when>
                <when test="author!=null">
                    and author=#{author}
                </when>
                <!-- 不管怎样 都会带上这个条件查询 -->
                <otherwise>
                    and views=#{views}
                </otherwise>
            </choose>
        </where>
    </select>
    
· sql代码片段复用 
sql
将公共的部分抽取出来, 方便复用
[注意事项]:
· 最好基于单表来定义SQL片段
· [不要存在where set标签], 最好只包含一些简单的if判断
  where set等关键词跟具体的条件有关, 会降低代码片段的可复用性
<sql id="queryBlogChoose-choose">
    <choose>
        <when test="title!=null">
            <!-- 使用了where标签 第一个不加and -->
            title like "%"#{title}"%"
        </when>
        <when test="author!=null">
            and author=#{author}
        </when>
        <!-- 不管怎样 都会带上这个条件查询 -->
        <otherwise>
            and views=#{views}
        </otherwise>
    </choose>
</sql>

· foreach 对一个集合进行遍历, 通常是在构建IN条件语句的时候
eg: 只查询id为1 2 3 4的用户
select * from user where 1=1 and (id=1 OR id=2)
ids = [1,2,3,4] 是一个id的集合
index 是集合ids中元素的下标
item 是集合ids中的每个元素
open 起始符号
separator 分隔符号---->这里为了实现上述的等效效果 使用OR作为分隔符号
close 结束符号
<foreach collection="ids" index="index" item="id"
    open="(" separator="OR" close")">
    #{id}
</foreach>
<!--
    select * from user where 1=1 and (id=1 OR id=2)
    传入一个map为参数,map中保存一个数组/列表/list,取名为ids,
    用一个collection="ids"来接收
    日志语句Preparing: select * from mybatis.blog WHERE ( id = ? or id = ? )
 -->
<select id="queryBlogForeach" parameterType="map" resultType="Blog">
    select * from mybatis.blog
    <where>
        <foreach collection="ids" index="i" item="id"
                 open="and (" separator="or" close=")">
            id = #{id}
        </foreach>
    </where>
</select>

<!--
    用[]只是为了表示相对于上面的改动,代码中不包括中括号
    select * from user where id in (1,2)
    传入一个map为参数,map中保存一个数组/列表/list,取名为ids,
    用一个collection="ids"来接收
    日志语句Preparing: select * from mybatis.blog where id in (1,2)
 -->
<select id="queryBlogForeach2" parameterType="map" resultType="Blog">
    select * from mybatis.blog [where id in]
    [去掉where标签,也可以用上]
    <foreach collection="ids" index="i" item="id"
             open="(" [separator=","] close=")">
             [#{id}]
    </foreach>
</select>
