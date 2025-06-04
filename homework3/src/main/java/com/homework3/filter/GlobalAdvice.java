package com.homework3.filter;

import com.homework3.common.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@CrossOrigin
@RestControllerAdvice
public class GlobalAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> converterType,
      org.springframework.http.server.ServerHttpRequest request,
      org.springframework.http.server.ServerHttpResponse response) {
    if (body instanceof ApiResponse) {
      return body;
    }
    return ApiResponse.ok(body);
  }

  @ExceptionHandler(RuntimeException.class)
  public ApiResponse<Void> handle(RuntimeException ex) {
    // 生产环境请记录日志
    return ApiResponse.fail(-1, ex.getMessage());
  }

}
