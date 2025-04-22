package com.homework2.config;

import com.homework2.entity.User;
import com.homework2.dto.UserRegisterRequest;
import com.homework2.mapper.UserMapper;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Aspect
@Component
public class UserDirectoryAspect {

  @Value("${storage.basePath}")
  private String basePath;

  @Autowired
  private UserMapper userMapper;

  @AfterReturning(
      pointcut   = "execution(* com.homework2.service.impl.UserImpl.register(..)) && args(userInfo)",
      returning  = "success",
      argNames   = "jp,userInfo,success"
  )
  public void onRegister(JoinPoint jp, UserRegisterRequest userInfo, boolean success) throws IOException {
    if (!success) return;

    // 注册成功后通过 email 再查一次完整的 User（能拿到 userId）
    User u = userMapper.findByEmail(userInfo.getEmail());
    if (u == null) return;

    // 在 basePath 下为这个用户建目录
    File dir = new File(basePath, "user_" + u.getUserId());
    FileUtils.forceMkdir(dir);

    // 更新数据库里的 path 字段
    userMapper.initPath(u.getUserId(), dir.getAbsolutePath());
  }
}
