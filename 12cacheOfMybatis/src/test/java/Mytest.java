import com.wq.dao.UserMapper;
import com.wq.pojo.User;
import com.wq.utils.MybatisUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
//import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class Mytest {
    @Test
    public void test_Cache(){

    }
    @Test
    public void test_getUserById(){

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            UserMapper umapper = sqlSession.getMapper(UserMapper.class);
            User u = umapper.getUserById(1);
            System.out.println(u);
//            // 更新数据库 测试缓存失效情况
//            umapper.updateUser(new User(2,"aaa","bbbb"));
            // sqlSession清理缓存, 测试缓存失效情况
            sqlSession.clearCache();
            System.out.println("===============================");
            User u2 = umapper.getUserById(1);
            System.out.println(u2);
            System.out.println("通过u是否等于u2来判断两个对象是否是同一个对象");
            System.out.println(u==u2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void test_secondLevel_Cache(){
        CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache_custom.xml");
        // Cache cache = cacheManager.getCache("defaultCache");
        // cacheManager.addCache("com.wq.dao.UserMapper");
        Cache cache = cacheManager.getCache("com.wq.dao.UserMapper");
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SqlSession sqlSession2 = MybatisUtil.getSqlSession();
        User u=null,u2=null;
        try{
            UserMapper umapper = sqlSession.getMapper(UserMapper.class);
            u = umapper.getUserById(1);
            System.out.println(u);
            u = umapper.getUserById(2);
            System.out.println(u);
            u = umapper.getUserById(3);
            u = umapper.getUserById(6);
            u = umapper.getUserById(56);
            u = umapper.getUserById(66);
            u = umapper.getUserById(86);
//            u = umapper.getUserById(89);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

        try{
            UserMapper umapper2 = sqlSession2.getMapper(UserMapper.class);
            u2 = umapper2.getUserById(1);
            System.out.println(u2);
            // 这里两个u u2不一定相等 但确实进行了一次Sql查询
            System.out.println(u==u2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession2.close();
        }

        System.out.println("缓存配置信息...");
        System.out.println("缓存中数据大小:"+cache.getSize());
        System.out.println("缓存中数据大小:"+cache.getStoreMBean());
        //System.out.println(cache.getCacheConfiguration());
        // 7. 刷新缓存
        // 将内存的数据保存到磁盘, 清空内存保存的数据
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }
}
