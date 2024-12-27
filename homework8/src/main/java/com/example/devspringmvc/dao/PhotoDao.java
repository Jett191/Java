package com.example.devspringmvc.dao;

import com.example.devspringmvc.model.Photo;
import org.apache.ibatis.annotations.*;

/**
 * 照片数据访问接口
 */
@Mapper
public interface PhotoDao {
    /**
     * 保存照片信息
     * @param photo 照片对象
     * @return 影响的行数
     */
    @Insert("INSERT INTO photo (id, file_name, content_type, data) " +
            "VALUES (#{id}, #{fileName}, #{contentType}, #{data})")
    int insert(Photo photo);
    
    /**
     * 根据ID查询照片
     * @param id 照片ID
     * @return 照片对象
     */
    @Select("SELECT * FROM photo WHERE id = #{id}")
    Photo findById(String id);
    
    /**
     * 删除照片
     * @param id 照片ID
     * @return 影响的行数
     */
    @Delete("DELETE FROM photo WHERE id = #{id}")
    int deleteById(String id);
} 