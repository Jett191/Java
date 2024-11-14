package com.dao;

import org.apache.ibatis.annotations.Select;

public interface IEmployee {

  @Select("SELECT * FROM employee WHERE id = #{id}")


}
