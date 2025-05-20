package com.homework3.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

  private int code;     // 0 成功，其它失败
  private String msg;   // 描述
  private T data;       // 数据


  public static <T> ApiResponse<T> ok(T data) {
    return new ApiResponse<>(0, "success", data);
  }

  public static ApiResponse<Void> fail(int code, String msg) {
    return new ApiResponse<>(code, msg, null);
  }

}
