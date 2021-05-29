import com.wq.dao.UserMapper;
import com.wq.pojo.User;
import com.wq.utils.MybatisUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mytest {
    @Test
    public void test_split_page(){
        // 1、获取SqlSession
        // MybatisUtil.getSqlSession() 获得工厂构造器, 获得工厂, 获得SqlSession
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("startIndex",0);
            map.put("pageSize",2);
            List<User> userList = mapper.getUserByLimit(map);
            for(User u: userList){
                System.out.println(u);
            }
            // sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            sqlSession.close();
        }


    }

    @Test
    // 不推荐使用 了解为主
    public void test_getUserByRowBounds(){
        // 1、获取SqlSession
        // MybatisUtil.getSqlSession() 获得工厂构造器, 获得工厂, 获得SqlSession
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            // RowBounds实现                       startIndex pageSize
            RowBounds rowBoundsnew = new RowBounds(1,4);
            // 通过java代码层面实现分页
            // RowBounds依赖这种方式 第三个参数是它的值, 为null即可
            List<User> userList = sqlSession.selectList("com.wq.dao.UserMapper.getUserByRowBounds", null, rowBoundsnew);
            for(User u: userList){
                System.out.println(u);
            }
            // sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            sqlSession.close();
        }
    }
}
