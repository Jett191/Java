<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生成绩管理系统</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            width: 800px;
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 30px;
        }
        .menu {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 40px;
        }
        .menu-item {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-decoration: none;
            color: #333;
            transition: transform 0.3s;
        }
        .menu-item:hover {
            transform: translateY(-5px);
        }
        .menu-icon {
            width: 80px;
            height: 80px;
            background-color: #4CAF50;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
            color: white;
            font-size: 24px;
        }
        .menu-text {
            font-size: 16px;
            font-weight: bold;
        }
        .search-form {
            margin: 30px 0;
        }
        .search-input {
            padding: 12px;
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-right: 10px;
            font-size: 16px;
        }
        .search-button {
            padding: 12px 24px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .search-button:hover {
            background-color: #1976D2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>学生成绩管理系统</h1>
        <form class="search-form" action="${pageContext.request.contextPath}/student/search" method="get">
            <input type="text" name="name" class="search-input" placeholder="请输入学生姓名" value="${searchName}">
            <button type="submit" class="search-button">搜索</button>
        </form>
        <div class="menu">
            <a href="${pageContext.request.contextPath}/student/list" class="menu-item">
                <div class="menu-icon">👥</div>
                <span class="menu-text">学生管理</span>
            </a>
            <a href="${pageContext.request.contextPath}/student/add" class="menu-item">
                <div class="menu-icon">➕</div>
                <span class="menu-text">添加学生</span>
            </a>
        </div>
    </div>
</body>
</html>