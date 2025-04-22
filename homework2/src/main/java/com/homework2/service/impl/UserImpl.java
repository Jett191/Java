package com.homework2.service.impl;


import com.homework2.entity.User;
import com.homework2.dto.UserLoginRequest;
import com.homework2.dto.UserRegisterRequest;
import com.homework2.mapper.UserMapper;
import com.homework2.dto.UserLoginInfoResponse;
import com.homework2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  @Transactional
  public boolean register(UserRegisterRequest userInfo) {
    if (userMapper.findByEmail(userInfo.getEmail()) != null) return false;

    User user = new User();
    user.setEmail(userInfo.getEmail());
    user.setName(userInfo.getName());
    user.setPassword(userInfo.getPassword());
    user.setSize(100.00);

    return userMapper.insert(user) > 0;
  }

  @Override
  public UserLoginInfoResponse login(UserLoginRequest userInfo) {

    if (userMapper.findByEmail(userInfo.getEmail()) == null) return null;

    User user = userMapper.findByEmail(userInfo.getEmail());

    UserLoginInfoResponse userLoginInfoResponse= new UserLoginInfoResponse();
    userLoginInfoResponse.setEmail(user.getEmail());
    userLoginInfoResponse.setName(user.getName());
    userLoginInfoResponse.setPath(user.getPath());
    userLoginInfoResponse.setSize(user.getSize());

    return  userLoginInfoResponse;
  }
}
