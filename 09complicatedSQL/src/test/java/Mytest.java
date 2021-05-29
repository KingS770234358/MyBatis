import com.wq.dao.StudentMapper;
import com.wq.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.wq.dao.TeacherMapper;
import com.wq.pojo.Teacher;
import com.wq.utils.MybatisUtil;

import java.util.List;

public class Mytest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            TeacherMapper tmapper =  sqlSession.getMapper(TeacherMapper.class);
            Teacher t = tmapper.getTeacher(1);
            System.out.println(t);
        }catch (Exception e){

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_student(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            StudentMapper tmapper =  sqlSession.getMapper(StudentMapper.class);
            List<Student> stuList = tmapper.getStudents();
            for(Student stu: stuList){
                System.out.println(stu);
            }
        }catch (Exception e){

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test_student2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            StudentMapper tmapper =  sqlSession.getMapper(StudentMapper.class);
            List<Student> stuList = tmapper.getStudents2();
            for(Student stu: stuList){
                System.out.println(stu);
            }
        }catch (Exception e){

        }finally {
            sqlSession.close();
        }
    }
}
