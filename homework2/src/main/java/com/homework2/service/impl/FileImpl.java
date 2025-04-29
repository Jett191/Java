package com.homework2.service.impl;

import com.homework2.mapper.UserMapper;
import com.homework2.service.FileService;
import java.io.File;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileImpl implements FileService {
  @Autowired
  private FileService fileService;

  @Autowired
  private UserMapper userMapper;

  @Override
  public String uploadFile(MultipartFile file, Integer userId) throws Exception {
    // 获取用户的存储路径
    String path = userMapper.findById(userId).getPath();
    // 创建文件对象
    File dest = new File(path, Objects.requireNonNull(file.getOriginalFilename()));
    // 上传文件
    file.transferTo(dest);
    return dest.getAbsolutePath();
  }

}
