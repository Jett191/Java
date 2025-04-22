<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h2>Employee List</h2>
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

<hr/>
<!-- 添加员工 -->
<h3>Add a new Employee</h3>
<form action="${pageContext.request.contextPath}/employee/add" method="post">
    <label for="deptId">Dept ID:</label>
    <input type="text" id="deptId" name="deptId" />
    <br/><br/>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" />
    <br/><br/>
    <label for="salary">Salary:</label>
    <input type="text" id="salary" name="salary" />
    <br/><br/>
    <input type="submit" value="Add" />
</form>

<hr/>
<!-- 根据部门查询员工 -->
<h3>Query by Department</h3>
<form action="${pageContext.request.contextPath}/employee/byDept" method="get">
    <label for="queryDeptId">Dept ID:</label>
    <input type="text" id="queryDeptId" name="deptId" />
    <input type="submit" value="Search" />
</form>
</body>
</html>
