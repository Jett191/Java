<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>文件列表</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
      body {
        background-color: #ffffff;
      }
    </style>
</head>
<body class="container mx-auto mt-8 px-4">
<!-- 页面顶部：标题和用户欢迎信息 -->
<div class="flex justify-between items-center mb-6">
    <div>
        <h3 class="text-2xl font-bold text-blue-500">文件列表</h3>
        <span class="text-gray-600">欢迎, ${sessionScope.loginUser.userName}</span>
    </div>
    <div class="flex space-x-2">
        <!-- 提供文件上传和空间管理的快捷链接 -->
        <a href="${pageContext.request.contextPath}/file/upload" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded">上传文件</a>
        <a href="${pageContext.request.contextPath}/space/manage" class="bg-gray-500 hover:bg-gray-600 text-white font-semibold py-2 px-4 rounded">个人空间管理</a>
    </div>
</div>

<!-- 展示文件信息的表格 -->
<div class="overflow-x-auto">
    <table class="min-w-full bg-white rounded-lg shadow-lg">
        <thead>
        <tr>
            <th class="py-2 px-4 border-b">文件名</th>
            <th class="py-2 px-4 border-b">类型</th>
            <th class="py-2 px-4 border-b">大小</th>
            <th class="py-2 px-4 border-b">上传时间</th>
            <th class="py-2 px-4 border-b">下载次数</th>
            <th class="py-2 px-4 border-b">状态</th>
            <th class="py-2 px-4 border-b">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${files}" var="file">
            <tr class="text-center">
                <td class="py-2 px-4 border-b">${file.fileRealName}</td>
                <td class="py-2 px-4 border-b">${file.cateName}</td>
                <td class="py-2 px-4 border-b">${file.fileSize}</td>
                <td class="py-2 px-4 border-b">
                    <fmt:formatDate value="${file.timeUpload}" pattern="yyyy-MM-dd"/>
                </td>
                <td class="py-2 px-4 border-b">${file.downCount}</td>
                <td class="py-2 px-4 border-b">
                    <!-- 根据文件状态显示不同的标签 -->
                    <c:choose>
                        <c:when test="${file.status == 1}">
                            <span class="text-green-500">正常</span>
                        </c:when>
                        <c:otherwise>
                            <span class="text-red-500">已锁定</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="py-2 px-4 border-b space-x-2">
                    <!-- 提供下载文件的链接 -->
                    <a href="${pageContext.request.contextPath}/file/download/${file.fileId}"
                       class="bg-blue-500 hover:bg-blue-600 text-white py-1 px-3 rounded"
                       onclick="return checkStatus(${file.status})">下载</a>
                    <!-- 仅允许文件所有者修改文件状态 -->
                    <c:if test="${sessionScope.loginUser.userId == file.userId}">
                        <button onclick="toggleStatus(${file.fileId})" class="bg-yellow-500 hover:bg-yellow-600 text-white py-1 px-3 rounded">
                            <c:choose>
                                <c:when test="${file.status == 1}">锁定</c:when>
                                <c:otherwise>解锁</c:otherwise>
                            </c:choose>
                        </button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- 页面底部的JavaScript脚本 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
  // 验证文件状态，决定是否允许下载
  function checkStatus(status) {
    if (status !== 1) {
      alert("文件已冻结，无法下载。");
      return false; // 阻止下载操作
    }
    return true; // 允许下载
  }

  // 切换文件的启用或禁用状态
  function toggleStatus(fileId) {
    $.post('${pageContext.request.contextPath}/file/toggle-status/' + fileId, function() {
      location.reload(); // 操作完成后刷新页面
    });
  }

  // 点击窗口时关闭模态弹窗
  window.onclick = function(event) {
    if (event.target.classList.contains('modal')) {
      event.target.style.display = "none";
    }
  }
</script>
</body>
</html>
