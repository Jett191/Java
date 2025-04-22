package com.homework2.service;

import com.homework2.dto.UserLoginRequest;
import com.homework2.dto.UserRegisterRequest;
import com.homework2.dto.UserLoginInfoResponse;

public interface UserService {

  boolean register(UserRegisterRequest userInfo);


  UserLoginInfoResponse login(UserLoginRequest userInfo);
}
