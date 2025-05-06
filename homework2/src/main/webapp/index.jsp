
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
      /* 全局重置 */
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }
      body {
        font-family: "Helvetica Neue", Arial, sans-serif;
        background-color: #f0f2f5;
        color: #333;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        text-align: center;
      }
      /* 控制段落与链接的间距 */
      body > div {
        margin: 10px 0;
      }
      /* 链接按钮样式 */
      a {
        display: inline-block;
        padding: 8px 16px;
        margin-left: 8px;
        background-color: #007bff;
        color: #fff;
        text-decoration: none;
        border-radius: 4px;
        transition: background-color 0.3s ease;
      }
      a:hover {
        background-color: #0056b3;
      }
    </style>
</head>
<body>
<div><p><a href="${pageContext.request.contextPath}/user/register">注册</a></p></div>
<div><p><a href="${pageContext.request.contextPath}/user/login">登录</a></p></div>

</body>
</html>
