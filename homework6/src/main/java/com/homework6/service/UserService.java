package com.homework6.service;

import com.homework6.entity.User;
import java.util.Map;

public interface UserService {
    boolean register(User user);
    Map<String, Object> login(String username, String password);
}
