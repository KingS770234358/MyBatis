import com.wq.dao.BlogMapper;
import com.wq.pojo.Blog;
import com.wq.utils.IDutil;
import com.wq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class Mytest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            BlogMapper bmapper = sqlSession.getMapper(BlogMapper.class);
            // @AllArgsConstructor
            Blog b;
            b = new Blog(IDutil.getUUID(),"标题1","狂神", new Date(), 999);
            bmapper.addBlog(b);
            b = new Blog(IDutil.getUUID(),"标题2","ha神", new Date(), 889);
            bmapper.addBlog(b);
            b = new Blog(IDutil.getUUID(),"标题3","opp神", new Date(), 777);
            bmapper.addBlog(b);
        }catch (Exception e){

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_DynamicQuery(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            BlogMapper bmapper = sqlSession.getMapper(BlogMapper.class);
            Map<String,Object> params_map = new HashMap<String,Object>();
            params_map.put("title","标题");
            // params_map.put("author","狂神");
            List<Blog> blogList = bmapper.queryBlogIF(params_map);
            for(Blog b:blogList){
                System.out.println(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_IF2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            BlogMapper bmapper = sqlSession.getMapper(BlogMapper.class);
            Map<String,Object> params_map = new HashMap<String,Object>();
            params_map.put("title","标题");
            // params_map.put("author","狂神");
            List<Blog> blogList = bmapper.queryBlogIF2(params_map);
            for(Blog b:blogList){
                System.out.println(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_Choose2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            BlogMapper bmapper = sqlSession.getMapper(BlogMapper.class);
            Map<String,Object> params_map = new HashMap<String,Object>();
            //params_map.put("title","标题");
            params_map.put("author","ha神");
            params_map.put("views","777");
            List<Blog> blogList = bmapper.queryBlogChoose(params_map);
            for(Blog b:blogList){
                System.out.println(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_updateUsingSet(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            BlogMapper bmapper = sqlSession.getMapper(BlogMapper.class);
            // @AllArgsConstructor
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("title","标题w");
            map.put("author", "jjjjshen");
            map.put("id", "274e04ddcea14d6b8ef3f25d1703f45a");
            int updateReulst = bmapper.updateBlogUsingSet(map);
            System.out.println(updateReulst);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_queryBlogForeach(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            BlogMapper bmapper = sqlSession.getMapper(BlogMapper.class);
            // @AllArgsConstructor
            ArrayList<String> ids = new ArrayList<String>();
            ids.add("1");
            ids.add("3");
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("ids",ids);
            List<Blog> blogList = bmapper.queryBlogForeach(map);
            for(Blog b:blogList){
                System.out.println(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void test_queryBlogForeach2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            BlogMapper bmapper = sqlSession.getMapper(BlogMapper.class);
            // @AllArgsConstructor
            ArrayList<String> ids = new ArrayList<String>();
            ids.add("1");
            ids.add("3");
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("ids",ids);
            List<Blog> blogList = bmapper.queryBlogForeach2(map);
            for(Blog b:blogList){
                System.out.println(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
