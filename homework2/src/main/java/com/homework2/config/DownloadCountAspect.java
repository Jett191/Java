package com.homework2.config;

import com.homework2.mapper.FileMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DownloadCountAspect {
  @Autowired
  private FileMapper fileMapper;

  @AfterReturning(
      pointcut = "execution(* com.homework2.service.impl.FileImpl.downloadFile(..)) "
          + "&& args(fileId, response)",
      argNames = "fileId,response"
  )
  public void afterDownload(Integer fileId, HttpServletResponse response) {
    fileMapper.incrementDownloadCount(fileId);
  }
}
