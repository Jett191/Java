<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>用户登录</title>
  <style>
    /* 全局重置 */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }
    /* 垂直布局，标题-表单-底部链接 */
    body {
      font-family: "Helvetica Neue", Arial, sans-serif;
      background-color: #f0f2f5;
      color: #333;
      display: flex;
      flex-direction: column;       /* 垂直堆叠 */
      align-items: center;
      justify-content: center;
      min-height: 100vh;
    }
    /* 标题与表单之间的间距 */
    h2 {
      font-size: 24px;
      margin-bottom: 20px;
      text-align: center;
    }
    /* 表单卡片风格，下方留空给提示和链接 */
    form {
      background: #fff;
      padding: 24px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      width: 320px;
      margin-bottom: 20px;          /* 表单下方空隙 */
    }
    /* 表单内字段间距 */
    p {
      margin-bottom: 16px;
    }
    /* 输入框样式 */
    input[type="text"],
    input[type="password"] {
      width: 100%;
      padding: 8px 12px;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 14px;
    }
    /* 按钮样式 */
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
    /* 错误提示保留红色，仅调整居中和间距 */
    p[style*="color:red"] {
      margin-top: 12px;
      text-align: center;
    }
    /* 底部“还没注册？”链接 */
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
<h2>用户登录</h2>
<form action="${pageContext.request.contextPath}/user/login" method="post">
  <p>邮箱：<input type="text" name="email" required/></p>
  <p>密码：  <input type="password" name="password" required/></p>
  <p><button type="submit">登录</button></p>
</form>
<c:if test="${not empty error}">
  <p style="color:red;">${error}</p>
</c:if>
<p>还没注册？<a href="${pageContext.request.contextPath}/user/register">去注册</a></p>
</body>
</html>
