package com.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import vo.Department;
import vo.Employee;

public interface IEmployee {

  // 查询所有员工
  @Select("SELECT * FROM Employee")
  List<Employee> selectAllEmployees();

  // 分页查询员工
  @Select("SELECT * FROM Employee LIMIT #{offset}, #{limit}")
  List<Employee> selectEmployeesByPage(@Param("offset") int offset, @Param("limit") int limit);

  // 根据部门ID查询员工
  @Select("SELECT * FROM Employee WHERE did = #{did}")
  List<Employee> selectEmployeesByDepartmentId(Integer did);

  // 根据条件查询员工
  @SelectProvider(type = EmployeeSqlBuilder.class, method = "buildSelectEmployees")
  List<Employee> selectEmployeesByConditions(Map<String, Object> params);

  // 插入新员工
  @Insert("INSERT INTO Employee(name, job, date, salary, did) VALUES(#{name}, #{job}, #{date}, #{salary}, #{did})")
  int insertEmployee(Employee employee);

  // 根据ID删除员工
  @Delete("DELETE FROM Employee WHERE id = #{id}")
  int deleteEmployeeById(@Param("id") Integer id);

  // 更新员工信息
  @Update("UPDATE Employee SET name=#{name}, job=#{job}, date=#{date}, salary=#{salary}, did=#{did} WHERE id=#{id}")
  int updateEmployee(Employee employee);
}