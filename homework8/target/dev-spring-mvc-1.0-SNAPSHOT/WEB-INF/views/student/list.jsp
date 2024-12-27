<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        .student-photo {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 50%;
            border: 2px solid #4CAF50;
            transition: transform 0.3s;
        }
        .student-photo:hover {
            transform: scale(1.1);
        }
        .photo-container {
            position: relative;
            display: inline-block;
        }
        .download-link {
            display: none;
            position: absolute;
            bottom: -30px;
            left: 50%;
            transform: translateX(-50%);
            background-color: #4CAF50;
            color: white;
            padding: 5px 10px;
            border-radius: 3px;
            text-decoration: none;
            font-size: 12px;
        }
        .photo-container:hover .download-link {
            display: block;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 15px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            display: inline-block;
            padding: 8px 16px;
            margin: 0 5px;
            text-decoration: none;
            color: white;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .btn-edit {
            background-color: #2196F3;
        }
        .btn-view {
            background-color: #4CAF50;
        }
        .btn-delete {
            background-color: #f44336;
        }
        .btn:hover {
            opacity: 0.9;
        }
        .search-container {
            text-align: center;
            margin: 20px 0;
        }
        .search-input {
            padding: 10px;
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-right: 10px;
        }
        .btn-search {
            background-color: #2196F3;
        }
        .add-new {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin: 20px 0;
        }
        .header-actions {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>学生列表</h2>
        <div class="search-container">
            <form action="${pageContext.request.contextPath}/student/search" method="get">
                <input type="text" name="name" placeholder="请输入学生姓名" value="${searchName}" class="search-input">
                <button type="submit" class="btn btn-search">搜索</button>
            </form>
        </div>
        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/student/add" class="add-new">添加新学生</a>
            <a href="${pageContext.request.contextPath}/" class="add-new">返回首页</a>
        </div>
        <table>
            <tr>
                <th>照片</th>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>
                        <div class="photo-container">
                            <c:if test="${not empty student.photoId}">
                                <img src="${pageContext.request.contextPath}/student/photo/${student.photoId}" 
                                     class="student-photo" alt="学生照片">
                                <a href="${pageContext.request.contextPath}/student/photo/download/${student.photoId}" 
                                   class="download-link">下载照片</a>
                            </c:if>
                        </div>
                    </td>
                    <td>${student.studentNo}</td>
                    <td>${student.name}</td>
                    <td>${student.gender}</td>
                    <td>${student.age}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/student/edit/${student.id}" class="btn btn-edit">编辑</a>
                        <a href="${pageContext.request.contextPath}/score/student/${student.id}" class="btn btn-view">查看成绩</a>
                        <form action="${pageContext.request.contextPath}/student/delete/${student.id}" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-delete" 
                                    onclick="return confirm('确定要删除该学生吗？这将同时删除该学生的所有成绩记录。')">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty students}">
            <div class="no-result">
                没有找到匹配的学生信息
            </div>
        </c:if>
    </div>
</body>
</html> 