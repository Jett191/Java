package com.homework1.dao;


import com.homework1.Entity.Employee;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeDao {

  // 查询所有员工
  @Select("SELECT emp_id, dept_id, name, salary, deleted FROM employee WHERE deleted = 0")
  List<Employee> findAll();

  // 添加员工
  @Insert("INSERT INTO employee (dept_id, name, salary, deleted) VALUES (#{deptId}, #{name}, #{salary}, 0)")
  @Options(useGeneratedKeys=true, keyProperty="empId", keyColumn="empId")
  int insertEmployee(Employee employee);

  // 根据部门查询员工
  @Select("SELECT emp_id, dept_id, name, salary, deleted FROM employee WHERE deleted = 0 AND dept_id = #{deptId}")
  List<Employee> findByDeptId(@Param("deptId") Integer deptId);

}
