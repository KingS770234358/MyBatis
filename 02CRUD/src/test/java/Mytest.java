import com.wq.dao.UserMapper;
import com.wq.pojo.User;
import com.wq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mytest {
    @Test
    public void test(){
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();
            for(User u:userList){
                System.out.println(u);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }
    @Test
    public void testLike(){
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserLike("李");
            for(User u:userList){
                System.out.println(u);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }
    @Test
    public void testLike2(){
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserLike2("%光%");
            for(User u:userList){
                System.out.println(u);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }
    @Test
    public void test2(){
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            User user = userMapper.getUserById(2);
            System.out.println(user);
            // 4.使用完sqlsession要将它关闭
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void test3(){
        /***
         * 使用万能map传参 可任意控制参数/属性个数
         * #{}里的参数名要和map的键名一一对应
         */
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mapkey1","1");
            map.put("mapkey2","狂神");

            User user = userMapper.getUserByIdAndName(map);
            System.out.println(user);
            // 4.使用完sqlsession要将它关闭
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void test_insert(){
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            User u = new User(96,"李zhun","6663355");
            int insert_return = userMapper.addUser(u);
            System.out.println(insert_return);
            // 4.一定要提交事务
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 5.使用完sqlsession要将它关闭
            sqlSession.close();
        }
    }
    @Test
    public void test_insert2(){
        /***
         * 可以只插入部分属性
         */
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            User u = new User();
            u.setId(66);
            u.setName("理光");
            int insert_return = userMapper.addUser2(u);
            System.out.println(insert_return);
            // 4.一定要提交事务
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 5.使用完sqlsession要将它关闭
            sqlSession.close();
        }
    }
    @Test
    public void test_insert3(){
        /***
         *  使用类的方式对Sql进行传参,#{}里的参数名要和类的属性名一一对应
         */
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            User u = new User();
            u.setId(85);
            u.setName("理光");
            u.setPwd("995599");
            int insert_return = userMapper.addUser3(u);
            System.out.println(insert_return);
            // 4.一定要提交事务
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 5.使用完sqlsession要将它关闭
            sqlSession.close();
        }
    }
    @Test
    public void test_insert4(){
        /***
         * 使用万能map传参 可任意控制参数/属性个数
         * #{}里的参数名要和map的键名一一对应
         */
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userid", 747);
            map.put("username", "map传参方式");
            map.put("userpwd", "map password");
            int insert_return = userMapper.addUser4(map);
            System.out.println(insert_return);
            // 4.一定要提交事务
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 5.使用完sqlsession要将它关闭
            sqlSession.close();
        }
    }
    @Test
    public void test_update(){
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            User u = new User(9,"黧黑","9999999");
            int update_return = userMapper.updateUser(u);
            System.out.println(update_return);
            // 4.一定要提交事务
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 5.使用完sqlsession要将它关闭
            sqlSession.close();
        }
    }
    @Test
    public void test_delete(){
        // 1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // 2.获得接口实现
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行方法
            // User u = new User(9,"黧黑","9999999");
            int delete_return = userMapper.deleteUser(9);
            System.out.println(delete_return);
            // 4.一定要提交事务
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 5.使用完sqlsession要将它关闭
            sqlSession.close();
        }
    }
}
