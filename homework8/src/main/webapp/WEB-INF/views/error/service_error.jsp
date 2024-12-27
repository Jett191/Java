<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务错误</title>
    <style>
        .error-container {
            width: 800px;
            margin: 50px auto;
            text-align: center;
        }
        .error-message {
            color: #f44336;
            margin: 20px 0;
            padding: 20px;
            background-color: #ffebee;
            border-radius: 4px;
        }
        .back-link {
            color: #2196F3;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            border: 1px solid #2196F3;
            border-radius: 4px;
        }
        .back-link:hover {
            background-color: #2196F3;
            color: white;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>操作失败</h2>
        <div class="error-message">
            <p>${message}</p>
        </div>
        <a href="javascript:history.back()" class="back-link">返回上一页</a>
        <a href="${pageContext.request.contextPath}/" class="back-link">返回首页</a>
    </div>
</body>
</html> 