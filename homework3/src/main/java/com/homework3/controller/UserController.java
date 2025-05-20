package com.homework3.controller;


import com.homework3.dto.DeleteDto;
import com.homework3.dto.StudentDto;
import com.homework3.entity.Student;
import com.homework3.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping("/students")
  public List<Student> listStudents() {
    return userService.getAllStudents();
  }

  @PostMapping("/addStudent")
  public StudentDto createStudent(@RequestBody Student student) {
    // 先插入
    userService.addStudent(student);

    // 构造 DTO 并返回
    StudentDto dto = new StudentDto();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setAge(student.getAge());
    dto.setSex(student.getSex());
    dto.setScore(student.getScore());
    dto.setDeleted(student.getDeleted());
    return dto;
  }

  @DeleteMapping("/deleteStudent/{id}")
  public DeleteDto deleteStudent(@PathVariable int id) {
    userService.deleteStudent(id);
    return new DeleteDto(id, "删除成功");
  }

  @PutMapping("/updateStudent/{id}")
  public StudentDto updateStudent(
      @PathVariable int id,
      @RequestBody Student student) {
    student.setId(id);
    userService.updateStudent(student);
    return new StudentDto(
        student.getId(),
        student.getName(),
        student.getAge(),
        student.getSex(),
        student.getScore(),
        student.getDeleted()
    );
  }

}
