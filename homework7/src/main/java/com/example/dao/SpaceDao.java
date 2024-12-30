package com.example.dao;

import com.example.entity.Space; // 导入空间实体类，表示用户的存储空间数据
import org.springframework.data.jpa.repository.JpaRepository; // 导入JpaRepository接口，提供基本的CRUD操作
import org.springframework.stereotype.Repository; // 使用Repository注解标记为数据访问层
import java.util.Optional; // 导入Optional类，用于封装可能为null的返回值

@Repository // 声明该接口为数据访问层，Spring会自动生成实现类
public interface SpaceDao extends JpaRepository<Space, Integer> {

    // 根据用户ID查找用户的空间信息，返回Optional包装的结果
    Optional<Space> findByUserId(Integer userId); // 查找指定用户ID的空间数据
}
