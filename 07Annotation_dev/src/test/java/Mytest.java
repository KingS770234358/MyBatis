import com.wq.dao.UserMapper;
import com.wq.pojo.User;
import com.wq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.util.List;

/***
 * 日志配置文件自定义位置
 *
 */
public class Mytest {
    @Test
    public void test_Annotation(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 底层使用的主要是反射
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUsers();
            for (User u:userList){
                System.out.println(u);
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_getUserById(){

//        int configWatcherInterval = 10;
//        PropertyConfigurator.configureAndWatch("src/main/resources/log4j.properties", configWatcherInterval);

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 底层使用的主要是反射
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User u = userMapper.getUserById(1);
            System.out.println(u);

            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_addUser(){
//        int configWatcherInterval = 10;
//        PropertyConfigurator.configureAndWatch("log4j.properties", configWatcherInterval);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 底层使用的主要是反射
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User u = new User(1220,"wqf", "99999");
            int insertResult = userMapper.addUser(u);
            // sqlSession.commit(); // 因为SqlSession open时已经设置了默认开启提交事务
            System.out.println(insertResult);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_updateUser(){
//        int configWatcherInterval = 10;
//        PropertyConfigurator.configureAndWatch("log4j.properties", configWatcherInterval);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 底层使用的主要是反射
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User u = new User(1,"狂神3.0", "99999");
            int updateResult = userMapper.updateUser(u);
            // sqlSession.commit(); // 因为SqlSession open时已经设置了默认开启提交事务
            System.out.println(updateResult);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_deleteUser(){
//        int configWatcherInterval = 10;
//        PropertyConfigurator.configureAndWatch("log4j.properties", configWatcherInterval);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 底层使用的主要是反射
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int deleteResult = userMapper.deleteUser(80);
            // sqlSession.commit(); // 因为SqlSession open时已经设置了默认开启提交事务
            System.out.println(deleteResult);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
