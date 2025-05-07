package com.homework3.service;

import com.homework3.entity.Student;
import java.util.List;

public interface UserService {

  List<Student> getAllStudents();

  void addStudent(Student student);

  void deleteStudent(int id);

  void updateStudent(Student student);
}
