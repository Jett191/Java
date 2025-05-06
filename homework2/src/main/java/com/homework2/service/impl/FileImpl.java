package com.homework2.service.impl;

import com.homework2.dto.FileInfo;
import com.homework2.dto.FileInfoResponse;
import com.homework2.mapper.FileMapper;
import com.homework2.mapper.UserMapper;
import com.homework2.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileImpl implements FileService {
  @Autowired
  private FileMapper fileMapper;

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

    int uuid = Math.abs(UUID.randomUUID().hashCode());
    long bytes = file.getSize();
    double bytesMb = (double) bytes / (1024 * 1024);
    double size = Math.round(bytesMb * 100) / 100.0;

    FileInfo fileInfo = new FileInfo();
    fileInfo.setFileId(uuid);
    fileInfo.setName(file.getOriginalFilename());
    fileInfo.setPath(dest.getAbsolutePath());
    fileInfo.setSize(size);
    fileInfo.setUserId(userId);
    fileInfo.setDeleted(0);
    fileInfo.setType(file.getContentType());
    fileInfo.setCreatedTime(Instant.now());

    fileMapper.insert(fileInfo);

    return dest.getAbsolutePath();
  }

  @Override
  public List<FileInfoResponse> listFilesByUserId(Integer userId) {
    return fileMapper.findByUserId(userId);
  }

  @Override
  @Transactional
  public void downloadFile(Integer fileId, HttpServletResponse response) {
    try {
      // 1. 查询文件信息
      FileInfoResponse info = fileMapper.selectFileById(fileId);
      String filePath = info.getPath();
      String fileName = info.getName();

      File file = new File(filePath);
      if (!file.exists()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "文件不存在");
        return;
      }

      // 2. 设置响应头
      response.setContentType("application/octet-stream");
      String encodedName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
      response.setHeader("Content-Disposition",
          "attachment; filename*=UTF-8''" + encodedName);

      // 3. 拷贝文件到 response
      FileUtils.copyFile(file, response.getOutputStream());
      response.flushBuffer();

    } catch (IOException e) {
      // 4. 异常处理
      try {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            "下载出错：" + e.getMessage());
      } catch (IOException ignored) {}
    }
  }

}
