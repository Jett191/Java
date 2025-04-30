package com.homework2.service;

import com.homework2.dto.FileInfoResponse;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  String uploadFile(MultipartFile file,Integer userId) throws Exception;
  List<FileInfoResponse> listFilesByUserId(Integer userId);
}
