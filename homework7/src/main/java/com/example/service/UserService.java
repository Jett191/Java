package com.example.service;

import com.example.dao.UserDao; // 导入用户数据访问层，处理与用户相关的数据库操作
import com.example.entity.Space; // 导入空间实体类，表示用户的存储空间数据
import com.example.entity.User; // 导入用户实体类，表示用户的基本信息
import org.springframework.beans.factory.annotation.Autowired; // 用于自动注入依赖
import org.springframework.stereotype.Service; // 标记该类为服务层组件
import org.springframework.transaction.annotation.Transactional; // 事务管理注解
import org.springframework.util.DigestUtils; // 导入MD5加密工具类，用于加密用户密码

@Service // 标记该类为服务层组件，处理用户相关的业务逻辑
@Transactional // 事务管理，确保操作的一致性和原子性
public class UserService {

    @Autowired
    private UserDao userDao; // 自动注入用户数据访问层
    @Autowired
    private SpaceService spaceService; // 自动注入空间服务，用于管理用户的存储空间

    // 用户注册方法
    public User register(User user) {
        // 检查用户名是否已存在
        if (userDao.findByUserName(user.getUserName()) != null) {
            throw new RuntimeException("用户名已存在"); // 如果用户名已存在，抛出异常
        }

        // 对用户密码进行MD5加密
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        User savedUser = userDao.save(user); // 保存用户信息到数据库

        // 创建用户默认存储空间
        Space space = new Space();
        space.setUserId(savedUser.getUserId()); // 设置用户ID
        space.setSpaceName(savedUser.getUserName() + "的空间"); // 设置空间名称为用户的名字
        space.setSpaceSize(104857600L); // 设置空间大小为100MB
        space.setUsedSpace(0L); // 初始已用空间为0
        spaceService.createSpace(space); // 保存空间信息

        return savedUser; // 返回保存后的用户信息
    }

    // 用户登录方法
    public User login(String userName, String password) {
        // 对密码进行MD5加密
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        // 查找匹配的用户并返回
        return userDao.findByUserNameAndUserPassword(userName, encryptedPassword);
    }
}
