package com.homework2.service;

import com.homework2.dto.FileInfoResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  String uploadFile(MultipartFile file,Integer userId) throws Exception;

  List<FileInfoResponse> listFilesByUserId(Integer userId);

  void downloadFile(Integer fileId, HttpServletResponse response);

  void deleteFile(Integer fileId) throws IOException;
}
