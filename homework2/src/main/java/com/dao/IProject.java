package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vo.Employee;

public interface IProject {


  @Select("SELECT * FROM Project")
  List<Employee> selectAllProjects();

  @Select("SELECT * FROM Project LIMIT #{offset}, #{limit}")
  List<Employee> selectProjectsByPage(@Param("offset") int offset, @Param("limit") int limit);


}
