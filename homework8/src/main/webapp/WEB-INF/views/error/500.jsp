<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服务器错误</title>
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
        <h2>服务器错误</h2>
        <div class="error-message">
            <p>服务器内部错误，请稍后重试</p>
        </div>
        <a href="${pageContext.request.contextPath}/" class="back-link">返回首页</a>
    </div>
</body>
</html> 