<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加成绩 - ${student.name}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: bold;
        }
        input[type="text"],
        input[type="number"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        .btn-group {
            text-align: center;
            margin-top: 30px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin: 0 10px;
            transition: opacity 0.3s;
        }
        .btn-submit {
            background-color: #4CAF50;
            color: white;
        }
        .btn-back {
            background-color: #666;
            color: white;
        }
        .btn:hover {
            opacity: 0.9;
        }
        .student-info {
            text-align: center;
            margin-bottom: 30px;
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

        <form action="${pageContext.request.contextPath}/score/add" method="post">
            <input type="hidden" name="studentId" value="${student.id}">
            
            <div class="form-group">
                <label>科目：</label>
                <input type="text" name="subject" required>
            </div>
            
            <div class="form-group">
                <label>分数：</label>
                <input type="number" name="score" step="0.01" min="0" max="100" required>
            </div>
            
            <div class="form-group">
                <label>考试时间：</label>
                <input type="date" name="examTime" required>
            </div>
            
            <div class="btn-group">
                <button type="submit" class="btn btn-submit">提交</button>
                <button type="button" onclick="history.back()" class="btn btn-back">返回</button>
            </div>
        </form>
    </div>
</body>
</html> 