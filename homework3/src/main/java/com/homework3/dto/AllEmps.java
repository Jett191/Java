package com.homework3.dto;

import com.homework3.entity.Emp;
import java.util.List;
import lombok.Data;

@Data
public class AllEmps {

  private List<Emp> empList;
}
