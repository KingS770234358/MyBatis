package com.wq.dao;

import com.wq.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 原来没有使用mybatis框架之前的纯粹使用JDBC的操作
 * 在这个类里实现对User操作的接口
 * 1.建立数据库连接
 */
public class oldOperate_UserDaoImpl implements UserDao{
    public List<User> getUserList(){

        // 步骤1.创建连接器，用完即关
        Connection con;
        //数据库驱动，com.mysql.jdbc.Driver该字段不变
        String driver = "com.mysql.cj.jdbc.Driver";
        //本地数据库的URL mybatis
        String url = "jdbc:mysql://localhost:3306/mybatis?useSS=true&useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        //用户名
        String user = "root";
        //用户root的密码，这里是我设置的密码
        String password = "root";

        // 把数据包装一下
        List<User> dataList = new ArrayList<User>();
        try {
            //相当于 new com.mysql.jdbc.Driver()，在环境中创建此对象，如果没有该类，则抛出ClassNotFoundException。
            //如果catch的类别中中没有设置ClassNotFoundException，则会报错
            Class.forName(driver);
            //【正式开始连接数据库】
            //使用url、用户名、密码进行连接
            con = DriverManager.getConnection(url,user,password);
            if (!con.isClosed()){
                System.out.println("数据库连接成功");
            }
            //步骤2.通知数据库 false 开启事务
            con.setAutoCommit(false);
            //步骤3.准备statement,使用statement发送SQL语句
            Statement statement = con.createStatement();
            //步骤4.传入具体的sql语句,并执行
            //具体的操作语句
            String sql = "select * from user";
            //执行查询,返回结果集。ResultSet（结果集）是数据查询返回的一种对象
            ResultSet resultSet = statement.executeQuery(sql);

            //接收查询结果的属性
            int id;
            String name;
            String pwd;

            while (resultSet.next()){
                Map <String,Object> itemMap = new HashMap<String, Object>();
                //从结果集中获取到name、age、score
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                pwd = resultSet.getString("pwd");
                System.out.println("id："+id+" 姓名："+name+" 密码："+pwd);
                User u = new User(id,name,pwd);
                dataList.add(u);
            }
            //5.关闭资源 先开的后关
            resultSet.close();
            con.close();
            return dataList;

        } catch (SQLException e){
            System.out.println("数据库连接失败");
        } catch (ClassNotFoundException e){
            System.out.println("数据库驱动没有安装");

        }
        return dataList;
    }
}
