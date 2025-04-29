<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <style>
      /* 简单的导航栏样式 */
      .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #f0f0f0;
        padding: 10px 20px;
        border-bottom: 1px solid #ccc;
        font-family: Arial, sans-serif;
      }
      .navbar .user-info span {
        margin-right: 15px;
        font-weight: bold;
      }
      .navbar .actions form {
        display: inline-block;
        margin: 0;
      }
      .navbar .actions input[type="file"] {
        vertical-align: middle;
      }
      .navbar .actions button,
      .navbar .actions a {
        margin-left: 8px;
        padding: 5px 10px;
        text-decoration: none;
        border: 1px solid #888;
        background-color: #fff;
        cursor: pointer;
      }
      .navbar .actions button:hover,
      .navbar .actions a:hover {
        background-color: #e8e8e8;
      }
      .notification {
        margin: 10px 20px;
        padding: 8px 12px;
        border-radius: 4px;
        font-family: Arial, sans-serif;
      }
      .notification.success {
        background-color: #e0f7e9;
        color: #257a38;
      }
      .notification.error {
        background-color: #fdecea;
        color: #a1281e;
      }
    </style>
</head>
<body>

<!-- 导航栏开始 -->
<div class="navbar">
    <!-- 左侧：用户信息 -->
    <div class="user-info">
        <span>👤 ${user.name}</span>
        <span>✉️ ${user.email}</span>
    </div>

    <!-- 右侧：文件上传 + 重新登录 -->
    <div class="actions">
        <form action="${pageContext.request.contextPath}/file/upload"
              method="post"
              enctype="multipart/form-data">
            <input type="file" name="file" required />
            <button type="submit">上传文件</button>
        </form>
        <a href="${pageContext.request.contextPath}/user/login">退出</a>
    </div>
</div>
<!-- 导航栏结束 -->
<!-- 上传结果提示 -->
<c:if test="${not empty message}">
    <div id="notification" class="notification ${success ? 'success' : 'error'}">
            ${message}
    </div>
    <script>
      // 页面渲染完成后 3 秒隐藏提示框
      setTimeout(function() {
        var notif = document.getElementById('notification');
        if (notif) {
          notif.style.display = 'none';
        }
      }, 3000);
    </script>
</c:if>

<!-- 下面是页面主体内容 -->


</body>
</html>
