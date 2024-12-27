package com.example.devspringmvc.dao;

import com.example.devspringmvc.model.Score;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 成绩数据访问接口
 */
@Mapper
public interface ScoreDao {
    /**
     * 查询指定学生的所有成绩
     * @param studentId 学生ID
     * @return 成绩列表
     */
    @Select("SELECT * FROM score WHERE student_id = #{studentId}")
    List<Score> findByStudentId(Integer studentId);
    
    /**
     * 添加成绩记录
     * @param score 成绩对象
     * @return 影响的行数
     */
    @Insert("INSERT INTO score (student_id, subject, score, exam_time) " +
            "VALUES (#{studentId}, #{subject}, #{score}, #{examTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Score score);
    
    /**
     * 删除指定学生的所有成绩
     * @param studentId 学生ID
     * @return 影响的行数
     */
    @Delete("DELETE FROM score WHERE student_id = #{studentId}")
    int deleteByStudentId(Integer studentId);
} 