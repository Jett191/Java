import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

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

}
