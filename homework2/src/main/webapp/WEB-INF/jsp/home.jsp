<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
</head>
<body>
<h2>欢迎，${user.name}！</h2>
<p>用户ID: ${user.userId}</p>
<p>邮箱: ${user.email}</p>
<p><a href="${pageContext.request.contextPath}/user/login">重新登录</a></p>
</body>
</html>
