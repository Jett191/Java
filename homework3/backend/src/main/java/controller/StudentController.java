package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dao.IStudent;
import vo.Student;
import org.apache.ibatis.session.RowBounds;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

  @Autowired
  private IStudent studentDao;

  @GetMapping
  public Map<String, Object> getStudentsWithPagination(
      @RequestParam(name = "page", defaultValue = "1") int currentPage,
      @RequestParam(defaultValue = "10") int pageSize) {
    // Calculate offset
    int offset = (currentPage - 1) * pageSize;
    RowBounds rowBounds = new RowBounds(offset, pageSize);
    List<Student> pagedStudents = studentDao.selectAllStudent(rowBounds);
    int totalStudents = studentDao.selectAllStudent(new RowBounds()).size();
    int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

    Map<String, Object> response = new HashMap<>();
    response.put("students", pagedStudents);
    response.put("currentPage", currentPage);
    response.put("pageSize", pageSize);
    response.put("totalPages", totalPages);
    response.put("totalItems", totalStudents);

    return response;
  }

  // 添加动态查询接口
  @GetMapping("/search")
  public List<Student> searchStudents(
      @RequestParam(required = false) Integer id,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer java,
      @RequestParam(required = false) Integer android,
      @RequestParam(required = false) Integer javaee) {

    Map<String, Object> params = new HashMap<>();
    if (id != null) params.put("id", id);
    if (name != null && !name.isEmpty()) params.put("name", name);
    if (java != null) params.put("java", java);
    if (android != null) params.put("android", android);
    if (javaee != null) params.put("javaee", javaee);

    return studentDao.selectStudent(params);
  }
}