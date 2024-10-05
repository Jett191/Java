import dao.IStudent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vo.Student;

public class StudentTest {

  private SqlSession session = null;
  private IStudent iStudent;
  private List<Student> list;

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
  public void testSelectUser() {
    if (session != null) {
      iStudent = session.getMapper(IStudent.class); //装载接口
      // 创建 RowBounds 对象，这里设置偏移量为0，页面大小为10
      RowBounds rowBounds = new RowBounds(0, 10);
      list = iStudent.selectAllStudent(rowBounds);
      for (Student s : list) {
        System.out.println(s.toString());
      }
    }
  }
}



