package com.homework1.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

  private Integer deptId;
  private String name;
  private String location;
  private Integer deleted;

}
