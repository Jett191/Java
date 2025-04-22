package com.homework2.dto;

import lombok.Data;

@Data
public class UserLoginRequest {

  private String password;
  private String email;

}
