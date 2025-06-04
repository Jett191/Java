package com.homework3.controller;

import com.homework3.common.ApiResponse;
import com.homework3.dto.AllEmps;
import com.homework3.entity.Emp;
import com.homework3.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmpController {

  @Autowired
  EmpService empService;

  @GetMapping("/allEmp")
  public List<Emp> listStudents(){
    return empService.getAllEmps();
  }

  @PostMapping
  public Emp addEmp(@RequestBody Emp emp) {
    return empService.createEmp(emp);
  }


  @PutMapping("/{id}")
  public Boolean editEmp(@PathVariable Integer id,
      @RequestBody Emp emp) {
    emp.setEmpId(id);           // 保证主键一致
    return empService.updateEmp(emp);
  }


  @DeleteMapping("/{id}")
  public Boolean removeEmp(@PathVariable Integer id) {
    return empService.deleteEmp(id);
  }

}
