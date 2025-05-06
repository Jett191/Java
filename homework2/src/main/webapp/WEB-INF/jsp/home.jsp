<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <style>
      /* 全局重置 & 容器 */
      * { margin: 0; padding: 0; box-sizing: border-box; }
      body {
        font-family: "Helvetica Neue", Arial, sans-serif;
        background-color: #f7f9fc;
        color: #333;
        line-height: 1.6;
      }
      .container {
        max-width: 1000px;
        margin: 20px auto;
        padding: 0 20px;
      }

      /* 导航栏 */
      .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #ffffff;
        padding: 12px 24px;
        border-radius: 8px;
        box-shadow: 0 2px 6px rgba(0,0,0,0.05);
        margin-bottom: 24px;
      }
      .navbar .user-info span {
        margin-right: 16px;
        font-weight: 500;
      }
      .navbar .actions form,
      .navbar .actions a {
        display: inline-block;
        vertical-align: middle;
      }
      .navbar .actions input[type="file"] {
        margin-right: 8px;
      }
      .navbar .actions button,
      .navbar .actions a {
        padding: 6px 14px;
        border: 1px solid #ccc;
        border-radius: 4px;
        background-color: #fafafa;
        text-decoration: none;
        color: #333;
        font-size: 14px;
        cursor: pointer;
        transition: background-color .2s, box-shadow .2s;
      }
      .navbar .actions button:hover,
      .navbar .actions a:hover {
        background-color: #f0f0f0;
        box-shadow: 0 1px 3px rgba(0,0,0,0.1);
      }

      /* 提示框 */
      .notification {
        max-width: 1000px;
        margin: 0 auto 20px;
        padding: 12px 16px;
        border-radius: 4px;
        font-size: 14px;
        animation: fadeIn 0.3s ease-out;
      }
      .notification.success {
        background-color: #e6f7e8;
        color: #2a7f3e;
        border: 1px solid #b9e2c4;
      }
      .notification.error {
        background-color: #fff2f0;
        color: #c92c2c;
        border: 1px solid #f5c2c7;
      }
      @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-10px); }
        to { opacity: 1; transform: translateY(0); }
      }

      /* 表格 */
      .file-table {
        width: 100%;
        border-collapse: collapse;
        background: #fff;
        box-shadow: 0 2px 6px rgba(0,0,0,0.05);
        border-radius: 8px;
        overflow: hidden;
      }
      .file-table thead {
        background-color: #f0f4f8;
      }
      .file-table th,
      .file-table td {
        padding: 12px 16px;
        text-align: left;
        font-size: 14px;
      }
      .file-table th {
        font-weight: 600;
        color: #555;
      }
      .file-table tbody tr {
        border-bottom: 1px solid #eee;
        transition: background-color .2s;
      }
      .file-table tbody tr:nth-child(even) {
        background-color: #fafafa;
      }
      .file-table tbody tr:hover {
        background-color: #f1f5fa;
      }

      /* 标题 */
      .section-title {
        font-size: 18px;
        font-weight: 600;
        margin-bottom: 12px;
        color: #333;
      }

      /* 空状态 */
      .empty-message {
        text-align: center;
        color: #777;
        padding: 40px 0;
        font-size: 16px;
      }
    </style>
</head>
<body>
<div class="container">
    <!-- 导航栏 -->
    <div class="navbar">
        <div class="user-info">
            <span>👤 ${user.name}</span>
            <span>✉️ ${user.email}</span>
        </div>
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

    <!-- 提示框 -->
    <c:if test="${not empty message}">
        <div id="notification"
             class="notification ${success ? 'success' : 'error'}">
                ${message}
        </div>
        <script>
          setTimeout(function() {
            var notif = document.getElementById('notification');
            if (notif) notif.style.display = 'none';
          }, 3000);
        </script>
    </c:if>

    <!-- 文件列表 -->
    <c:if test="${not empty files}">
        <div class="section-title">我的文件</div>
        <table class="file-table">
            <thead>
            <tr>
                <th>文件名</th>
                <th>上传者</th>
                <th>大小 (MB)</th>
                <th>类型</th>
                <th>上传时间</th>
                <th>下载量</th>           <!-- 新增列 -->
                <th>操作</th>             <!-- 新增列 -->
            </tr>
            </thead>
            <tbody>
            <c:forEach var="file" items="${files}">
                <tr>
                    <td>${file.name}</td>
                    <td>${file.userName}</td>
                    <td>
                        <fmt:formatNumber
                                value="${file.size}"
                                type="number"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                    </td>
                    <td>${file.type}</td>
                    <td>
                        <fmt:parseDate
                                value="${file.createdTime}"
                                pattern="yyyy-MM-dd'T'HH:mm:ss'Z'"
                                var="dt"/>
                        <fmt:formatDate
                                value="${dt}"
                                pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <!-- 假设后端文件对象有 count 字段 -->
                            ${file.count}
                    </td>
                    <td>
                        <!-- 下载按钮 -->
                        <c:url var="downloadUrl" value="/file/download">
                            <c:param name="fileId" value="${file.fileId}"/>
                        </c:url>

                        <a href="${downloadUrl}"
                           target="_blank"
                           onclick="setTimeout(function(){ location.reload() }, 1000)">
                            下载
                        </a>
                        <!-- 删除按钮 -->
                        <a href="${pageContext.request.contextPath}/file/delete?fileId=${file.fileId}"
                           onclick="return confirm('确定要删除此文件？');">
                            删除
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- 无文件提示 -->
    <c:if test="${empty files}">
        <div class="empty-message">暂无上传文件。</div>
    </c:if>

</div>
</body>
</html>
