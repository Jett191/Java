<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
</head>
<body>
<h2>用户注册</h2>
<form action="${pageContext.request.contextPath}/api/user/register" method="post">
    <p>用户名：<input type="text" name="name" required/></p>
    <p>密码：  <input type="password" name="password" required/></p>
    <p>邮箱：  <input type="email" name="email" required/></p>
    <p><button type="submit">注册</button></p>
</form>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<p>已有账号？<a href="${pageContext.request.contextPath}/user/login">点击登录</a></p>
</body>
</html>
