import com.wq.dao.UserDao;
import com.wq.dao.oldOperate_UserDaoImpl;
import com.wq.pojo.User;
import com.wq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class Mytest {
    @Test
    public void test(){
        // 旧的方式
        UserDao ooud =  new oldOperate_UserDaoImpl();
        ooud.getUserList();
        // Mybatis方式
        // 1.取得SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        try{
            // 2.执行方式一:传入要获得哪个接口的实现类 的接口
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            // 3.执行对应的方法
            List<User>userList = mapper.getUserList();
            for(User u:userList){
                System.out.println(u);
            }
        /*
             执行方式二(不建议使用):直接传入接口里的方法
             selectOne返回一个
             selectList返回一个列表
             selectMap返回一个Map
             ====>写死了
             这里使用了强转List<Object> ===> List<User>
         */
            List<User>userList2 = sqlSession.selectList("com.wq.dao.UserDao.getUserList");
            for(User u:userList2){
                System.out.println(u);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4.使用完sqlsession要将它关闭
            sqlSession.close();
        }
    }
}
