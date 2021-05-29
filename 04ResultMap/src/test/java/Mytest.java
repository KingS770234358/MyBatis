import com.wq.dao.UserMapper;
import com.wq.pojo.User;
import com.wq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
// 这里导包一定要导log4j下的logger
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

public class Mytest {
    // 在输出日志的类中加入相关语句
    Logger logger = Logger.getLogger(Mytest.class);
    @Test
    public void test(){
        logger.info("info:进入了test");
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User u = mapper.getUserById(1);
            /**
             * select * from user where id = #{idddd};
             * 类型处理器 无法将pwd映射成password
             * =select id, name, pwd from user where id = #{idddd};
             * 出现问题:User{id=1, name='狂神', password='null'}
             * 解决方法:
             * 方法一、sql语句中给字段起别名(起跟实体类中属性一样的名字)
             * 改成select id, name, pwd as password from user where id = #{idddd};
             * 方法二、使用resultMap详见笔记和mapper.xml
              */

            //sqlSession.commit();
            System.out.println(u);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testLog4j(){
        logger.debug("debug:进入了testLog4j");
        logger.info("info:进入了testLog4j");
        logger.error("error:进入了testLog4j");
    }
}
