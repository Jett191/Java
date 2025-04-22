<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>用户登录</title>
</head>
<body>
<h2>用户登录</h2>
<form action="${pageContext.request.contextPath}/user/login" method="post">
  <p>用户名：<input type="text" name="name" required/></p>
  <p>密码：  <input type="password" name="password" required/></p>
  <p><button type="submit">登录</button></p>
</form>
<c:if test="${not empty error}">
  <p style="color:red;">${error}</p>
</c:if>
<p>还没注册？<a href="${pageContext.request.contextPath}/user/register">去注册</a></p>
</body>
</html>
