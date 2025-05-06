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
          + "  f.file_id AS fileId, "
          + "  u.name AS userName, "
          + "  f.size, "
          + "  f.type, "
          + "  f.count,"
          + "  f.created_time AS createdTime, "
          + "  f.deleted "
          + "FROM `file` f "
          + "LEFT JOIN `user` u ON u.user_id = f.user_id "
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
  int insert(FileInfo fileInfo);


  @Select("SELECT file_id AS fileId, name,path "
      + "FROM file WHERE file_id = #{fileId}")
  FileInfoResponse selectFileById(@Param("fileId") Integer fileId);

  /**
   * 下载成功后，自增下载次数
   */
  @Update("UPDATE file "
      + "   SET `count` = `count` + 1 "
      + " WHERE file_id = #{fileId}")
  int incrementDownloadCount(@Param("fileId") Integer fileId);

  /**
   * 删除
   */
  @Update("UPDATE file "
      + "   SET deleted = 1 "
      + " WHERE file_id = #{fileId}")
  int softDeleteById(@Param("fileId") Integer fileId);

}
