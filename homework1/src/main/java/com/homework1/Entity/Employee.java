package com.homework1.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  private Integer empId;
  private Integer deptId;
  private String name;
  private Integer salary;
  private Integer deleted;

}
