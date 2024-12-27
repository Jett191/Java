<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${student.name}的成绩列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
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
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            border-radius: 4px;
            transition: opacity 0.3s;
            margin: 10px;
        }
        .btn-add {
            background-color: #4CAF50;
        }
        .btn-back {
            background-color: #2196F3;
        }
        .btn:hover {
            opacity: 0.9;
        }
        .actions {
            text-align: center;
            margin: 20px 0;
        }
        .no-scores {
            text-align: center;
            padding: 20px;
            color: #666;
            font-style: italic;
        }
        .student-info {
            text-align: center;
            margin-bottom: 20px;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 4px;
        }
        .student-name {
            font-size: 24px;
            color: #333;
            margin-bottom: 10px;
        }
        .student-no {
            color: #666;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="student-info">
            <div class="student-name">${student.name}</div>
            <div class="student-no">学号：${student.studentNo}</div>
        </div>
        
        <div class="actions">
            <a href="${pageContext.request.contextPath}/score/add/${student.id}" class="btn btn-add">添加成绩</a>
            <a href="${pageContext.request.contextPath}/student/list" class="btn btn-back">返回学生列表</a>
        </div>

        <c:if test="${not empty student.scores}">
            <table>
                <tr>
                    <th>科目</th>
                    <th>分数</th>
                    <th>考试时间</th>
                </tr>
                <c:forEach items="${student.scores}" var="score">
                    <tr>
                        <td>${score.subject}</td>
                        <td>${score.score}</td>
                        <td><fmt:formatDate value="${score.examTime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        
        <c:if test="${empty student.scores}">
            <div class="no-scores">
                暂无成绩记录
            </div>
        </c:if>
    </div>
</body>
</html> 