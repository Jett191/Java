<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>Employee List</title>
  <style>
    /* 全局样式 */
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f4f4f9;
      margin: 20px;
      color: #333;
    }

    h2, h3 {
      color: #4a90e2;
      margin-bottom: 15px;
    }

    /* 表格样式 */
    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    table th, table td {
      padding: 10px;
      text-align: left;
      border: 1px solid #ddd;
    }

    table th {
      background-color: #4a90e2;
      color: #fff;
    }

    /* 表单样式 */
    form {
      background-color: #fff;
      padding: 15px;
      margin-bottom: 20px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    form p {
      margin-bottom: 10px;
    }

    form label {
      display: inline-block;
      width: 100px;
      font-weight: bold;
    }

    form input[type="text"] {
      width: calc(100% - 120px);
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    form input[type="submit"] {
      background-color: #4a90e2;
      color: #fff;
      border: none;
      padding: 10px 20px;
      border-radius: 4px;
      cursor: pointer;
    }

    form input[type="submit"]:hover {
      background-color: #357ab7;
    }

    /* 分隔线样式 */
    hr {
      border: 0;
      height: 1px;
      background: #ddd;
      margin: 30px 0;
    }
  </style>
</head>
<body>
<!-- 显示所有员工 -->
<h2>员工表</h2>
<table border="1">
  <tr>
    <th>empId</th>
    <th>deptId</th>
    <th>name</th>
    <th>salary</th>
  </tr>
  <c:forEach var="employee" items="${employees}">
    <tr>
      <td>${employee.empId}</td>
      <td>${employee.deptId}</td>
      <td>${employee.name}</td>
      <td>${employee.salary}</td>
    </tr>
  </c:forEach>
</table>

<hr />

<!-- 添加员工 -->
<h3>添加员工</h3>
<form action="${pageContext.request.contextPath}/employee/add" method="post">
  <p>
    <label for="deptId">部门ID:</label>
    <input type="text" id="deptId" name="deptId" />
  </p>
  <p>
    <label for="name">姓名:</label>
    <input type="text" id="name" name="name" />
  </p>
  <p>
    <label for="salary">薪资:</label>
    <input type="text" id="salary" name="salary" />
  </p>
  <p>
    <input type="submit" value="添加" />
  </p>
</form>

<hr />

<!-- 根据部门查询员工 -->
<h3>查询员工</h3>
<form action="${pageContext.request.contextPath}/employee/byDept" method="get">
  <label for="queryDeptId">部门ID:</label>
  <input type="text" id="queryDeptId" name="deptId" />
  <input type="submit" value="搜索" />
</form>

</body>
</html>
