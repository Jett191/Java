package com.homework6.mapper;

import com.homework6.entity.Space;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SpaceMapper {
    @Insert("INSERT INTO space(user_id, total_space, used_space) VALUES(#{userId}, 52428800, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Space space);

    @Select("SELECT * FROM space WHERE user_id = #{userId}")
    Space findByUserId(Integer userId);
}
