package com.example.devspringmvc.dao;

import com.example.devspringmvc.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 学生数据访问接口
 * 使用MyBatis注解方式实现SQL映射
 */
@Mapper
public interface StudentDao {
    /**
     * 插入学生信息
     * @param student 学生对象
     * @return 影响的行数
     */
    @Insert("INSERT INTO student (student_no, name, gender, age, photo_id) " +
            "VALUES (#{studentNo}, #{name}, #{gender}, #{age}, #{photoId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 设置自动生成主键
    int insert(Student student);
    
    /**
     * 根据ID查询学生信息，包含成绩信息（一对多关系）
     * @param id 学生ID
     * @return 学生对象，包含成绩列表
     */
    @Select("SELECT * FROM student WHERE id = #{id}")
    @Results({
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "scores", column = "id",
            many = @Many(select = "com.example.devspringmvc.dao.ScoreDao.findByStudentId"))
    })
    Student findById(Integer id);
    
    /**
     * 查询所有学生
     * @return 学生列表
     */
    @Select("SELECT * FROM student")
    List<Student> findAll();
    
    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 影响的行数
     */
    @Update("UPDATE student SET name=#{name}, gender=#{gender}, " +
            "age=#{age}, photo_id=#{photoId} WHERE id=#{id}")
    int update(Student student);
    
    /**
     * 删除学生
     * @param id 学生ID
     * @return 影响的行数
     */
    @Delete("DELETE FROM student WHERE id = #{id}")
    int deleteById(Integer id);
    
    /**
     * 根据姓名模糊查询学生
     * @param name 学生姓名（支持模糊查询）
     * @return 匹配的学生列表
     */
    @Select("SELECT * FROM student WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Student> findByName(String name);
    
    /**
     * 根据学号查询学生
     * @param studentNo 学号
     * @return 学生对象
     */
    @Select("SELECT * FROM student WHERE student_no = #{studentNo}")
    Student findByStudentNo(String studentNo);
} 