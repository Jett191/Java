package com.example.dao;

import com.example.entity.User; // 导入用户实体类，表示用户数据模型
import org.springframework.data.jpa.repository.JpaRepository; // 导入JpaRepository接口，提供基本的CRUD操作
import org.springframework.stereotype.Repository; // 使用Repository注解标记为数据访问层

@Repository // 声明该接口为数据访问层，Spring会自动为其生成实现类
public interface UserDao extends JpaRepository<User, Integer> {

    // 根据用户名查找用户
    User findByUserName(String userName); // 通过用户名查询用户

    // 根据用户名和密码查找用户
    User findByUserNameAndUserPassword(String userName, String userPassword); // 通过用户名和密码查询用户
}
