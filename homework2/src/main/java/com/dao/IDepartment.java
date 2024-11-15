package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import vo.Department;
import vo.Employee;

public interface IDepartment {

  @Select("SELECT * FROM Department")
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "name", property = "name"),
      @Result(column = "location", property = "location"),
      @Result(property = "employees", column = "id",
          many = @Many(select = "com.dao.IEmployee.selectEmployeesByDepartmentId"))
  })
  List<Department> selectDepartmentsWithEmployees();

  @Select("SELECT * FROM Department")
  List<Department> selectAllDepartments();

  @Select("SELECT * FROM Department LIMIT #{offset}, #{limit}")
  List<Employee> selectDepartmentsByPage(@Param("offset") int offset, @Param("limit") int limit);

  @Insert("INSERT INTO Department(name, location) VALUES(#{name}, #{location})")
  int insertDepartment(Department department);

  @Delete("DELETE FROM Department WHERE id = #{id}")
  int deleteDepartmentById(@Param("id") Integer id);

  @Update("UPDATE Department SET name=#{name}, location=#{location} WHERE id=#{id}")
  int updateDepartment(Department department);
}
