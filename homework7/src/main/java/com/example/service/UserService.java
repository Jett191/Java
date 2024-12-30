package com.example.service;

import com.example.dao.UserDao; // 用户数据访问层
import com.example.entity.Space; // 空间实体
import com.example.entity.User; // 用户实体
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils; // MD5加密工具

@Service // 标记为服务层
@Transactional // 事务管理
public class UserService {

    @Autowired
    private UserDao userDao; // 注入用户数据访问层
    @Autowired
    private SpaceService spaceService; // 注入空间服务

    // 注册用户
    public User register(User user) {
        // 检查用户名是否已存在
        if (userDao.findByUserName(user.getUserName()) != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 加密用户密码
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        User savedUser = userDao.save(user); // 保存用户信息

        // 创建并保存用户空间
        Space space = new Space();
        space.setUserId(savedUser.getUserId());
        space.setSpaceName(savedUser.getUserName() + "的空间");
        space.setSpaceSize(5242880L); // 设置5MB空间大小
        space.setUsedSpace(0L); // 初始已用空间为0
        spaceService.createSpace(space); // 保存空间信息

        return savedUser; // 返回保存的用户
    }

    // 用户登录
    public User login(String userName, String password) {
        // 加密密码并进行验证
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        return userDao.findByUserNameAndUserPassword(userName, encryptedPassword); // 返回匹配的用户
    }
}