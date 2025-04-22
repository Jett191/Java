package com.homework1.service.impl;

import com.homework1.Entity.Employee;
import com.homework1.dao.EmployeeDao;
import com.homework1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {

  @Autowired
  private EmployeeDao employeeDao;

  @Override
  public List<Employee> findAll() {
    return employeeDao.findAll();
  }

  @Override
  public int addEmployee(Employee employee) {
    return employeeDao.insertEmployee(employee);
  }

  @Override
  public List<Employee> findByDeptId(Integer deptId) {
    return employeeDao.findByDeptId(deptId);
  }
}
