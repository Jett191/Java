package com.homework2.mapper;

import com.homework2.dto.FileInfo;
import com.homework2.dto.FileInfoResponse;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileMapper {
  @Select(
      "SELECT "
          + "  f.name, "
          + "  u.name AS userName, "           // 将 user 表的 name 字段映射为 DTO 的 userName
          + "  f.size, "
          + "  f.type, "
          + "  f.count,"
          + "  f.created_time AS createdTime, "
          + "  f.deleted "
          + "FROM `file` f "
          + "LEFT JOIN `user` u ON u.user_id = f.user_id "  // 通过 user_id 关联 user 表
          + "WHERE f.user_id = #{userId} "
          + "  AND f.deleted = 0"
  )
  List<FileInfoResponse> findByUserId(@Param("userId") Integer userId);


  /**
   * 插入一条文件记录，返回受影响的行数
   */
  @Insert(
      "INSERT INTO `file` " +
          "  (file_id, user_id, name, path, size, type, created_time, deleted,count) " +
          "VALUES " +
          "  (#{fileId}, #{userId}, #{name}, #{path}, #{size}, #{type}, #{createdTime}, #{deleted}, #{count})"
  )
  // 既然你自己生成 UUID，就不需要自动回填，这里可以去掉 @Options
  int insert(FileInfo fileInfo);

  // 你已有的 findByUserId(...) 方法…

}
