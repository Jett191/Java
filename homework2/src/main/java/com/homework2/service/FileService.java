package com.homework2.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  String uploadFile(MultipartFile file,Integer userId) throws Exception;
}
