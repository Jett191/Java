package com.homework3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  private int id;
  private String name;
  private int age;
  private String sex;
  private String score;
  private Integer deleted;

}
