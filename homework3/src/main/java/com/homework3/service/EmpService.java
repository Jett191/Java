package com.homework3.service;


import com.homework3.common.ApiResponse;
import com.homework3.dto.AllEmps;
import com.homework3.entity.Emp;
import java.util.List;

public interface EmpService {

  List<Emp> getAllEmps();


  Emp createEmp(Emp emp);


  boolean updateEmp(Emp emp);


  boolean deleteEmp(Integer id);

}
