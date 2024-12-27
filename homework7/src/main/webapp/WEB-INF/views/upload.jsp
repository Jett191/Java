<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>文件上传</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
      body {
        background-color: #ffffff;
      }
    </style>
</head>
<body class="min-h-screen flex flex-col items-center justify-start py-10">

<!-- Header部分 -->
<div class="w-full max-w-4xl px-4 mb-6">
    <h1 class="text-2xl font-bold text-center text-blue-500">
        文件上传
    </h1>
    <!-- 欢迎用户 -->
    <p class="text-center text-gray-700 mt-2">
        欢迎, <span class="font-semibold text-gray-800">${sessionScope.loginUser.userName}</span>
    </p>
</div>

<!-- 表单卡片 -->
<div class="w-full max-w-2xl bg-white shadow-lg rounded-xl p-6">
    <!-- 文件上传表单 -->
    <form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data" class="space-y-6">

        <!-- 文件类型选择 -->
        <div>
            <label for="cateId" class="block text-sm font-medium text-gray-700">文件类型</label>
            <select name="cateId" id="cateId"
                    class="w-full border border-gray-300 rounded-lg px-4 py-2 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-transparent"
                    required>
                <option value="">选择文件类型</option>
                <!-- 使用JSTL遍历文件类型 -->
                <c:forEach items="${categories}" var="category">
                    <option value="${category.cateId}">${category.cateName}</option>
                </c:forEach>
            </select>
        </div>

        <!-- 文件选择 -->
        <div>
            <label for="file" class="block text-sm font-medium text-gray-700">选择文件</label>
            <input type="file" id="file" name="file"
                   class="w-full border border-gray-300 rounded-lg px-4 py-2 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-transparent"
                   required>
        </div>

        <!-- 提交按钮 -->
        <div class="text-center">
            <button type="submit"
                    class="w-full py-3 rounded-lg bg-blue-500 text-white font-bold shadow-md hover:bg-blue-600 transform hover:scale-105 transition-all duration-300">
                上传
            </button>
        </div>
    </form>
</div>
</body>
</html>