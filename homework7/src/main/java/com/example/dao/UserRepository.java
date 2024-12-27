package com.example.dao;

import com.example.entity.User; // 用户实体
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository 提供基本的 CRUD 操作
import org.springframework.stereotype.Repository; // Repository 注解标记为数据访问层

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 根据用户名查找用户
    User findByUserName(String userName);

    // 根据用户名和密码查找用户
    User findByUserNameAndUserPassword(String userName, String userPassword);
}