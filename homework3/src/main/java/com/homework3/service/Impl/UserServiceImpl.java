package com.homework3.service.Impl;

import com.homework3.entity.Student;
import com.homework3.mapper.UserMapper;
import com.homework3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


  @Autowired
  private UserMapper userMapper;

  @Override
  public List<Student> getAllStudents() {
    return userMapper.findAll();
  }


  @Override
  public void addStudent(Student student) {
    // 新插入的记录默认deleted=0
    if (student.getDeleted() == null) {
      student.setDeleted(0);
    }
    userMapper.insertStudent(student);
  }


  @Override
  public void deleteStudent(int id) {
    int rows = userMapper.markDeleted(id);
    if (rows != 1) {
      throw new RuntimeException("删除失败，找不到 id=" + id + " 的学生");
    }
  }

  @Override
  public void updateStudent(Student student) {
    int rows = userMapper.updateStudent(student);
    if (rows != 1) {
      throw new RuntimeException("更新失败，找不到 id=" + student.getId());
    }
  }

}
