package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import vo.Student;
import java.util.List;
import java.util.Map;

@Mapper
public interface IStudent {
  List<Student> selectAllStudent(RowBounds rowBounds);

  // 添加 selectStudent 方法声明
  List<Student> selectStudent(Map<String, Object> params);
}