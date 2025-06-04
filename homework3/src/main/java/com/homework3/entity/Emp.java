package com.homework3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

  private Integer empId;
  private String name;
  private Integer age;
  private String dept;
  private String salary;
  private Integer deleted;

}
