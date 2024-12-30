package com.example.dao;

import com.example.entity.FileKind; // 文件分类实体
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository 提供基本的 CRUD 操作
import org.springframework.stereotype.Repository; // Repository 注解标记为数据访问层

@Repository
public interface FileKindDao extends JpaRepository<FileKind, Integer> {
  // JpaRepository 提供了对 FileKind 实体的基本数据操作，不需要手动实现
}