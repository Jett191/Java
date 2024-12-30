package com.example.dao;

import com.example.entity.FileKind; // 导入文件分类实体类，表示文件分类数据模型
import org.springframework.data.jpa.repository.JpaRepository; // 导入JpaRepository接口，提供基本的CRUD操作
import org.springframework.stereotype.Repository; // 使用Repository注解标记为数据访问层

@Repository // 声明该接口为数据访问层，Spring会自动为其生成实现类
public interface FileKindDao extends JpaRepository<FileKind, Integer> {
  // 此接口继承 JpaRepository，默认提供了基本的CRUD操作
}
