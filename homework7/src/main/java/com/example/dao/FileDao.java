package com.example.dao;

import com.example.entity.File; // 文件信息实体
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository 提供基本的 CRUD 操作
import org.springframework.stereotype.Repository; // Repository 注解标记为数据访问层
import java.util.List;

@Repository
public interface FileDao extends JpaRepository<File, Integer> {
    // 根据用户ID按下载次数降序查找文件信息
    List<File> findByUserIdOrderByDownCountDesc(Integer userId);
}