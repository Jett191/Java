package com.homework1.controller;

import com.homework1.Entity.Employee;
import com.homework1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;


  @GetMapping("/list")
  public String listAllEmployees(Model model) {
    List<Employee> employees = employeeService.findAll();
    model.addAttribute("employees", employees);
    return "employeeList";
  }


  @PostMapping("/add")
  public String addEmployee(Employee employee) {
    employeeService.addEmployee(employee);
    // 添加完后重定向到员工列表
    return "redirect:/employee/list";
  }


  @GetMapping("/byDept")
  public String findByDept(@RequestParam("deptId") Integer deptId, Model model) {
    List<Employee> employees = employeeService.findByDeptId(deptId);
    model.addAttribute("employees", employees);
    // 与显示所有员工公用同一个视图页面
    return "employeeList";
  }
}
