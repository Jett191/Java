<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
</head>
<body>
    <h2>添加学生</h2>
    <form action="${pageContext.request.contextPath}/student/add" method="post" enctype="multipart/form-data">
        <p>
            <label>学号：</label>
            <input type="text" name="studentNo" required>
        </p>
        <p>
            <label>姓名：</label>
            <input type="text" name="name" required>
        </p>
        <p>
            <label>性别：</label>
            <select name="gender">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </p>
        <p>
            <label>年龄：</label>
            <input type="number" name="age" required>
        </p>
        <p>
            <label>照片：</label>
            <input type="file" name="photoFile" accept="image/*">
        </p>
        <p>
            <button type="submit">提交</button>
            <button type="button" onclick="history.back()">返回</button>
        </p>
    </form>
</body>
</html> 