package com.example.dao;

import com.example.entity.Space; // 空间实体
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository 提供基本的 CRUD 操作
import org.springframework.stereotype.Repository; // Repository 注解标记为数据访问层
import java.util.Optional;

@Repository
public interface SpaceDao extends JpaRepository<Space, Integer> {
    // 根据用户ID查找空间信息
    Optional<Space> findByUserId(Integer userId);
}