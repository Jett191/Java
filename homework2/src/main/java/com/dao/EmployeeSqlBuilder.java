package com.dao;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

public class EmployeeSqlBuilder implements ProviderMethodResolver {

  public static String buildSelectEmployees(Map<String, Object> params) {
    return new SQL() {{
      SELECT("*");
      FROM("Employee");
      if (params.get("name") != null) {
        WHERE("name = #{name}");
      }
      if (params.get("job") != null) {
        WHERE("job = #{job}");
      }
      if (params.get("salaryMin") != null) {
        WHERE("salary >= #{salaryMin}");
      }
      if (params.get("salaryMax") != null) {
        WHERE("salary <= #{salaryMax}");
      }
      if (params.get("did") != null) {
        WHERE("did = #{did}");
      }
    }}.toString();
  }
}