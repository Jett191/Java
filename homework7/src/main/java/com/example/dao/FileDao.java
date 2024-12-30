package com.example.dao;

import com.example.entity.File; // 导入文件实体类，表示文件的相关数据
import org.springframework.data.jpa.repository.JpaRepository; // 导入JpaRepository接口，提供基本的CRUD操作
import org.springframework.stereotype.Repository; // 使用Repository注解标记为数据访问层
import java.util.List; // 导入List集合类，用于返回多个文件对象

@Repository // 声明该接口为数据访问层，Spring会为其自动生成实现类
public interface FileDao extends JpaRepository<File, Integer> {

    // 根据用户ID查找文件，并按下载次数降序排列
    List<File> findByUserIdOrderByDownCountDesc(Integer userId); // 根据用户ID查询文件并排序
}
