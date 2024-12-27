<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑学生信息</title>
</head>
<body>
    <h2>编辑学生信息</h2>
    <form action="${pageContext.request.contextPath}/student/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${student.id}">
        <p>
            <label>学号：</label>
            <input type="text" name="studentNo" value="${student.studentNo}" required>
        </p>
        <p>
            <label>姓名：</label>
            <input type="text" name="name" value="${student.name}" required>
        </p>
        <p>
            <label>性别：</label>
            <select name="gender">
                <option value="男" ${student.gender == '男' ? 'selected' : ''}>男</option>
                <option value="女" ${student.gender == '女' ? 'selected' : ''}>女</option>
            </select>
        </p>
        <p>
            <label>年龄：</label>
            <input type="number" name="age" value="${student.age}" required>
        </p>
        <p>
            <label>照片：</label>
            <input type="file" name="photoFile" accept="image/*">
            <c:if test="${not empty student.photoId}">
                <br>
                <img src="${pageContext.request.contextPath}/student/photo/${student.photoId}" 
                     style="width: 100px; height: 100px; object-fit: cover;" alt="当前照片">
            </c:if>
        </p>
        <p>
            <button type="submit">保存</button>
            <button type="button" onclick="history.back()">返回</button>
        </p>
    </form>
</body>
</html> 