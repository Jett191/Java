package com.homework3.service.Impl;

import com.homework3.entity.Emp;
import com.homework3.mapper.EmpMapper;
import com.homework3.service.EmpService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

  private final EmpMapper empMapper;


  @Override
  public List<Emp> getAllEmps() {
    return empMapper.findAll();
  }


  @Override
  @Transactional
  public Emp createEmp(Emp emp) {
    validateForInsert(emp);
    emp.setDeleted(0);

    int rows = empMapper.insert(emp);
    if (rows != 1) {
      log.error("新增员工失败：{}", emp);
      throw new RuntimeException("新增员工失败，请稍后再试");
    }
    return emp;   // empId 已被 MyBatis 回填
  }


  @Override
  @Transactional
  public boolean updateEmp(Emp emp) {
    if (emp.getEmpId() == null) {
      throw new IllegalArgumentException("员工 ID 不能为空");
    }
    // 确认员工存在且未删除
    Emp dbEmp = empMapper.findById(emp.getEmpId());
    if (dbEmp == null) {
      throw new RuntimeException("员工不存在或已删除");
    }
    validateForUpdate(emp);

    int rows = empMapper.update(emp);
    if (rows != 1) {
      log.error("更新员工失败：{}", emp);
      throw new RuntimeException("更新失败，请稍后再试");
    }
    return true;
  }


  @Override
  @Transactional
  public boolean deleteEmp(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("ID 不能为空");
    }
    // 确认员工存在
    if (empMapper.findById(id) == null) {
      throw new RuntimeException("员工不存在或已删除");
    }

    int rows = empMapper.logicalDelete(id);
    if (rows != 1) {
      log.error("删除员工失败：id={}", id);
      throw new RuntimeException("删除失败，请稍后再试");
    }
    return true;
  }

  /* ===== 私有校验方法 ===== */
  private void validateForInsert(Emp emp) {
    if (emp == null)                    throw new IllegalArgumentException("参数不能为空");
    if (!StringUtils.hasText(emp.getName()))   throw new IllegalArgumentException("姓名不能为空");
    if (emp.getAge() == null || emp.getAge() <= 0)
      throw new IllegalArgumentException("年龄必须为正数");
    if (!StringUtils.hasText(emp.getDept()))   throw new IllegalArgumentException("部门不能为空");
    if (!StringUtils.hasText(emp.getSalary())) throw new IllegalArgumentException("薪资不能为空");
  }

  private void validateForUpdate(Emp emp) {
    validateForInsert(emp);
  }
}
