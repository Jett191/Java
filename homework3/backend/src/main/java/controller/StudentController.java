package controller;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dao.IStudent;
import vo.Student;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired
  private IStudent studentDao;

  @GetMapping
  public Map<String, Object> getStudentsWithPagination(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int pageSize) {
    int offset = (page - 1) * pageSize;
    RowBounds rowBounds = new RowBounds(offset, pageSize);
    List<Student> students = studentDao.selectStudent(rowBounds);

    Map<String, Object> response = new HashMap<>();
    response.put("students", students);
    response.put("currentPage", page);
    response.put("pageSize", pageSize);

    return response;
  }
}