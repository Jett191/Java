import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vo.Stu;
import vo.Student;

public class TestMybatis {

  private SqlSession session = null;

  @Before
  public void init() {
    InputStream is;
    try {
      is = Resources.getResourceAsStream("mybatis-config.xml");
      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
      session = factory.openSession();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @After
  public void close() {
    if (session != null) {
      session.close();
    }
  }

  @Test
  public void testSelectAllStudent() {
    if (session != null) {
      List<Student> list = session.selectList("com.mapper.StudentMapper.selectAllStudents");
      for (Student student : list) {
        System.out.println(student.toString());
      }
    }
  }

  @Test
  public void testSelectAllStu() {
    if (session != null) {
      List<Stu> list = session.selectList("com.mapper.StudentMapper.selectAllStu");
      for (Stu stu : list) {
        System.out.println(stu.toString());
      }
    }
  }

  @Test
  public void testSelectCount() {
    if (session != null) {
      int count = session.selectOne("com.mapper.StudentMapper.selectCount");
      System.out.println(count);
    }
  }

  @Test
  public void testSelectStuById() {
    if (session != null) {
      int aa = 2;
      Student stu = session.selectOne("com.mapper.StudentMapper.selectStuById", aa);
      System.out.println(stu);
    }
  }

  @Test
  public void testselectStuById2() {
    if (session != null) {
      int aa = 2;
      Student stu = session.selectOne("com.mapper.StudentMapper.selectStuById2", aa);
      System.out.println(stu);
    }
  }
}