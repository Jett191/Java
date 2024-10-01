package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import vo.Student;
import java.util.List;

@Mapper
public interface IStudent {
  List<Student> selectStudent(RowBounds rowBounds);
}