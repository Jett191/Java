package com.homework2.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
  private String name;
  private String password;
  private String email;
}
