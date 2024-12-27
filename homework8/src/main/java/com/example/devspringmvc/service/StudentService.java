package com.example.devspringmvc.service;

import com.example.devspringmvc.model.Student;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    List<Student> findByName(String name);
    Student findById(Integer id);
    void save(Student student, MultipartFile photoFile);
    void update(Student student, MultipartFile photoFile);
    void deleteById(Integer id);
} 