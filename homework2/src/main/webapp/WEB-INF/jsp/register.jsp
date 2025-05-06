<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <style>
      /* 全局重置 */
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }
      /* 垂直居中布局 */
      body {
        font-family: "Helvetica Neue", Arial, sans-serif;
        background-color: #f0f2f5;
        color: #333;
        display: flex;
        flex-direction: column;        /* 垂直排列 */
        align-items: center;
        justify-content: center;
        min-height: 100vh;
      }
      /* 标题置顶，并增加与表单的间距 */
      h1 {
        font-size: 24px;
        margin-bottom: 20px;
      }
      /* 表单容器，下方留白给提示与链接 */
      form {
        background: #fff;
        padding: 24px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        width: 320px;
        margin-bottom: 20px;          /* 表单下方空隙 */
      }
      /* 表单内各行间距 */
      p {
        margin-bottom: 16px;
      }
      input[type="text"],
      input[type="password"] {
        width: 100%;
        padding: 8px 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
      }
      button {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s;
      }
      button:hover {
        background-color: #0056b3;
      }
      /* 错误提示（若有），居中并与链接同层次 */
      p[style*="color:red"] {
        margin-top: 12px;
        text-align: center;
      }
      /* 底部“已有账号？”链接 */
      body > p:last-of-type {
        font-size: 14px;
        text-align: center;
      }
      body > p:last-of-type a {
        color: #007bff;
        text-decoration: none;
      }
      body > p:last-of-type a:hover {
        text-decoration: underline;
      }
    </style>
</head>
<body>
<h1>用户注册</h1>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <p>用户名：<input type="text" name="name" required/></p>
    <p>密码：  <input type="password" name="password" required/></p>
    <p>邮箱：  <input type="text" name="email" required/></p>
    <p><button type="submit">注册</button></p>
</form>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<p>已有账号？<a href="${pageContext.request.contextPath}/user/login">点击登录</a></p>
</body>
</html>
