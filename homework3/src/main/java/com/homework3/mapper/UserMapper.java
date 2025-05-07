package com.homework3.mapper;

import com.homework3.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

  @Select("SELECT id, name, age, sex, score, deleted FROM student WHERE deleted = 0")
  List<Student> findAll();

  /**
   * 插入一条新学生记录，自动回写主键 id 到 student.id
   */
  @Insert("INSERT INTO student(name, age, sex, score, deleted) " +
      "VALUES(#{name}, #{age}, #{sex}, #{score}, #{deleted})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insertStudent(Student student);

  @Update("UPDATE student SET deleted = 1 WHERE id = #{id}")
  int markDeleted(int id);

  @Update("UPDATE student "
      + "SET name = #{name}, age = #{age}, sex = #{sex}, score = #{score} "
      + "WHERE id = #{id}")
  int updateStudent(Student student);

}
