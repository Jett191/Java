package com.homework2.dto;

import lombok.Data;

@Data
public class UserLoginInfoResponse {

  private Integer userId;
  private String name;
  private String email;
  private String path;
  private Double size;
  
}
