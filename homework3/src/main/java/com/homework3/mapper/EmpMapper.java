package com.homework3.mapper;

import com.homework3.entity.Emp;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EmpMapper {

  /** 查询全部未删除员工 */
  @Select("""
          SELECT emp_id AS empId, name, age, dept, salary, deleted
          FROM emp
          WHERE deleted = 0
          """)
  List<Emp> findAll();

  /** 根据 id 查询 */
  @Select("""
          SELECT emp_id AS empId, name, age, dept, salary, deleted
          FROM emp
          WHERE emp_id = #{id} AND deleted = 0
          """)
  Emp findById(@Param("id") Integer id);

  /** 新增，返回自增主键 */
  @Insert("""
          INSERT INTO emp (name, age, dept, salary, deleted)
          VALUES (#{name}, #{age}, #{dept}, #{salary}, #{deleted})
          """)
  @Options(useGeneratedKeys = true, keyProperty = "empId")
  int insert(Emp emp);

  /** 更新 */
  @Update("""
          UPDATE emp
          SET name = #{name},
              age  = #{age},
              dept = #{dept},
              salary = #{salary}
          WHERE emp_id = #{empId} AND deleted = 0
          """)
  int update(Emp emp);

  /** 逻辑删除 */
  @Update("""
          UPDATE emp
          SET deleted = 1
          WHERE emp_id = #{id} AND deleted = 0
          """)
  int logicalDelete(@Param("id") Integer id);
}
