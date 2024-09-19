import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import vo.Student;

public class IbatisTest01 {

  public static void main(String[] args) throws Exception {

    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
    SqlSession session = factory.openSession();

    // 从mapper中获取数据 读取sql语句
    List<Student> list = session.selectList("com.mapper.StudentMapper.selectAllStudents");
    for (Student student : list) {
      System.out.println(student.toString());
    }
    session.close();
  }


}