import com.dao.IDepartment;
import com.dao.IEmployee;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vo.Department;
import vo.Employee;

public class TestEmployee {

  // SqlSession对象，用于执行SQL语句
  private SqlSession session = null;

  // 初始化方法，在每个测试方法执行前执行
  @Before
  public void init() {
    InputStream is;
    try {
      // 读取MyBatis配置文件
      is = Resources.getResourceAsStream("mybatis-config.xml");
      // 构建SqlSessionFactory
      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
      // 打开一个新的SqlSession
      session = factory.openSession();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 关闭方法，在每个测试方法执行后执行
  @After
  public void close() {
    if (session != null) {
      session.close();
    }
  }

  // 测试查询所有员工
  @Test
  public void testSelectAllEmployees() {
    // 获取IEmployee接口的映射器
    IEmployee mapper = session.getMapper(IEmployee.class);
    // 查询所有员工
    List<Employee> employees = mapper.selectAllEmployees();
    // 打印每个员工的信息
    for (Employee employee : employees) {
      System.out.println(employee);
    }
  }

  // 测试分页查询员工
  @Test
  public void testSelectEmployeesByPage() {
    // 获取IEmployee接口的映射器
    IEmployee mapper = session.getMapper(IEmployee.class);
    int offset = 0;
    int limit = 30;
    // 分页查询员工
    List<Employee> employees = mapper.selectEmployeesByPage(offset, limit);
    // 打印每个员工的信息
    for (Employee employee : employees) {
      System.out.println(employee);
    }
  }

  // 测试查询部门及其员工
  @Test
  public void testSelectDepartmentsWithEmployees() {
    // 获取IDepartment接口的映射器
    IDepartment mapper = session.getMapper(IDepartment.class);
    // 查询所有部门及其员工
    List<Department> departments = mapper.selectDepartmentsWithEmployees();
    // 打印每个部门及其员工的信息
    for (Department department : departments) {
      System.out.println("部门ID：" + department.getId());
      System.out.println("部门名称：" + department.getName());
      System.out.println("部门位置：" + department.getLocation());
      System.out.println("员工列表：");
      List<Employee> employees = department.getEmployees();
      if (employees != null) {
        for (Employee employee : employees) {
          System.out.println("  员工ID：" + employee.getId());
          System.out.println("  姓名：" + employee.getName());
          System.out.println("  职位：" + employee.getJob());
          System.out.println("  入职日期：" + employee.getDate());
          System.out.println("  工资：" + employee.getSalary());
          System.out.println("  --------------------");
        }
      }
      System.out.println("---------------------------");
    }
  }

  // 测试根据条件查询员工
  @Test
  public void testSelectEmployeesByConditions() {
    // 获取IEmployee接口的映射器
    IEmployee mapper = session.getMapper(IEmployee.class);
    // 设置查询条件
    Map<String, Object> params = new HashMap<>();
    params.put("name", "李璐");
    params.put("job", "美工");
    params.put("salaryMin", 5000);
    params.put("salaryMax", 10000);

    // 根据条件查询员工
    List<Employee> employees = mapper.selectEmployeesByConditions(params);
    // 打印每个员工的信息
    for (Employee employee : employees) {
      System.out.println(employee);
    }
  }

  // 测试插入员工
  @Test
  public void testInsertEmployee() {
    // 获取IEmployee接口的映射器
    IEmployee mapper = session.getMapper(IEmployee.class);
    // 创建一个新的员工对象
    Employee employee = new Employee();
    employee.setName("王五");
    employee.setJob("销售经理");
    employee.setDate("2023-04-01");
    employee.setSalary(12000);
    employee.setDid(3); // 假设部门ID为 3
    // 插入员工
    int result = mapper.insertEmployee(employee);
    session.commit(); // 提交事务
    System.out.println("插入结果 受影响的行数：" + result);
  }

  // 测试删除员工
  @Test
  public void testDeleteEmployeeById() {
    // 获取IEmployee接口的映射器
    IEmployee mapper = session.getMapper(IEmployee.class);
    int idToDelete = 2101;
    // 删除员工
    int result = mapper.deleteEmployeeById(idToDelete);
    session.commit();
    System.out.println("删除结果 受影响的行数：" + result);
  }

  // 测试更新员工
  @Test
  public void testUpdateEmployee() {
    // 获取IEmployee接口的映射器
    IEmployee mapper = session.getMapper(IEmployee.class);
    // 创建一个新的员工对象
    Employee employee = new Employee();
    employee.setId(2);
    employee.setName("Alan");
    employee.setJob("高级工程师");
    employee.setDate("2023-02-01");
    employee.setSalary(15000);
    employee.setDid(2);
    // 更新员工信息
    int result = mapper.updateEmployee(employee);
    session.commit();
    System.out.println("更新结果 受影响的行数：" + result);
  }
}