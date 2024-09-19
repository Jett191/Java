import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vo.Emp;

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

  // 查询所有雇员
  @Test
  public void testSelectAllEmp() {
    if (session != null) {
      List<Emp> list = session.selectList("com.mapper.EmpMapper.selectAllEmp");
      for (Emp employee : list) {
        System.out.println(employee.toString());
      }
    }
  }

  // 查询部门30中的雇员
  @Test
  public void testSelectEmpByDeptno() {
    if (session != null) {
      List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpByDeptno", 30);
      for (Emp employee : list) {
        System.out.println(employee.toString());
      }
    }
  }

  // 查询雇员的姓名和工资和部门编号
  @Test
  public void testSelectEmpNameSalDeptno() {
    if (session != null) {
      List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameSalDeptno");
      for (Emp employee : list) {
        System.out.println(employee.toString());
      }
    }
  }

  //查询雇员的姓名、及雇佣时间
  @Test
  public void testSelectEmpNameHiredate() {
    if (session != null) {
      List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameHiredate");
      for (Emp employee : list) {
        System.out.println(employee.toString());
      }
    }
  }

  // 检索emp表中的部门编号及工种，并去掉重复行
  @Test
  public void testSelectDistinctDeptnoJob() {
    Map<Object, Object> map = session.selectMap("com.mapper.EmpMapper.selectDeptnoJob", "deptno");
    System.out.println(map);
  }

  // 检索emp表中的员工姓名及全年的月收入
  @Test
  public void testSelectEmpNameSal() {
    List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameSal");
    for (Emp employee : list) {
      System.out.println(employee.toString());
    }
  }

  //用姓名显示员工姓名，用年收入显示全年月收入
  @Test
  public void testSelectEmpNameSal2() {
    List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameSal2");
    for (Emp employee : list) {
      System.out.println("姓名：" + employee.getName() + " 年收入：" + employee.getSalary());
    }
  }

  // 检索月收入大于2000的员工姓名及月收入
  @Test
  public void testSelectEmpNameSal3() {
    List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameSal3");
    for (Emp employee : list) {
      System.out.println("姓名：" + employee.getName() + " 月收入：" + employee.getSalary());
    }
  }

  //检索月收入在1000元到2000元的员工姓名、月收入及雇佣时间
  @Test
  public void testSelectEmpNameSalHiredate() {
    List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameSalHiredate");
    for (Emp employee : list) {
      System.out.println("姓名：" + employee.getName() + " 月收入：" + employee.getSalary() + " 雇佣时间："
          + employee.getDate());
    }
  }

  //检索以S开头的员工姓名及月收入
  @Test
  public void testSelectEmpNameSal4() {
    List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameSal4");
    for (Emp employee : list) {
      System.out.println("姓名：" + employee.getName() + " 月收入：" + employee.getSalary());
    }
  }

  //检索emp表中月收入是800的或是1250的员工姓名及部门编号
  @Test
  public void testSelectEmpNameDeptno() {
    List<Emp> list = session.selectList("com.mapper.EmpMapper.selectEmpNameDeptno");
    for (Emp employee : list) {
      System.out.println("姓名：" + employee.getName() + " 部门编号：" + employee.getDid());
    }
  }
}
