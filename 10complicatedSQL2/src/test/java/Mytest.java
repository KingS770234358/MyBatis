import com.wq.dao.TeacherMapper;
import com.wq.pojo.Student;
import com.wq.pojo.Teacher;
import com.wq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class Mytest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            TeacherMapper tmapper = sqlSession.getMapper(TeacherMapper.class);
            List<Teacher> teacherList = tmapper.getTeacher();
            for(Teacher t: teacherList){
                System.out.println(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_getTeacherStudents(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            TeacherMapper tmapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher t = tmapper.getTeacherStudents(1);
            System.out.println(t);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_getTeacherStudents2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            TeacherMapper tmapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher t = tmapper.getTeacherStudents2(1);
            System.out.println(t);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
