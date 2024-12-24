package com.homework6.service.impl;

import com.homework6.entity.User;
import com.homework6.entity.Space;
import com.homework6.mapper.UserMapper;
import com.homework6.mapper.SpaceMapper;
import com.homework6.service.UserService;
import com.homework6.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private SpaceMapper spaceMapper;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    @Transactional
    public boolean register(User user) {
        // 检查用户名是否已存在
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (userMapper.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("邮箱已被注册");
        }
        
        // 对密码进行MD5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        
        // 插入用户信息
        if (userMapper.insert(user) > 0) {
            // 为新用户创建存储空间
            Space space = new Space();
            space.setUserId(user.getId());
            return spaceMapper.insert(space) > 0;
        }
        return false;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        
        // 对密码进行MD5加密
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        
        // 查询用户
        User user = userMapper.findByUsernameAndPassword(username, encryptedPassword);
        
        if (user != null) {
            // 使用JWT生成token
            String token = jwtUtil.generateToken(user.getUsername(), user.getId());
            
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("token", token);
            result.put("userId", user.getId());
            result.put("username", user.getUsername());
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        
        return result;
    }
}
