<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误页面</title>
    <style>
        .error-container {
            width: 800px;
            margin: 50px auto;
            text-align: center;
        }
        .error-message {
            color: #f44336;
            margin: 20px 0;
        }
        .back-link {
            color: #2196F3;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>发生错误</h2>
        <div class="error-message">
            <p>错误信息：${exception.message}</p>
            <p>请求地址：${url}</p>
        </div>
        <a href="${pageContext.request.contextPath}/" class="back-link">返回首页</a>
    </div>
</body>
</html> 