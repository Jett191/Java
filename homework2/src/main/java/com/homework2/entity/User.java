package com.homework2.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private Integer userId;
  private String name;
  private String password;
  private String email;
  private String path;
  private Double size;
  private Integer deleted;

}
