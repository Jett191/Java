<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>个人空间管理</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
      body {
        background-color: #ffffff;
      }
    </style>
</head>
<body class="min-h-screen flex flex-col items-center justify-start py-8">

<!-- 页面顶部：标题和导航链接 -->
<div class="w-full max-w-4xl px-4 mb-6">
    <h1 class="text-2xl font-bold text-center text-blue-500">
        个人空间管理
    </h1>
    <!-- 返回文件列表的按钮 -->
    <div class="mt-4 text-center">
        <a href="${pageContext.request.contextPath}/file/list"
           class="inline-block bg-blue-500 text-white font-medium py-2 px-6 rounded-lg shadow-md hover:bg-blue-600 transform hover:scale-105 transition-all duration-300">
            返回文件列表
        </a>
    </div>
</div>

<!-- 空间管理表单容器 -->
<div class="w-full max-w-2xl bg-white shadow-lg rounded-xl p-6">
    <form action="${pageContext.request.contextPath}/space/update" method="post" class="space-y-6">
        <!-- 输入新的空间名称 -->
        <div>
            <label for="spaceName" class="block text-sm font-medium text-gray-700">空间名称</label>
            <div class="mt-1">
                <input type="text" id="spaceName" name="spaceName"
                       value="${space.spaceName}"
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-transparent"
                       required>
            </div>
        </div>

        <!-- 输入新的空间容量（以MB为单位） -->
        <div>
            <label for="spaceSize" class="block text-sm font-medium text-gray-700">空间大小 (MB)</label>
            <div class="mt-1">
                <input type="number" id="spaceSize" name="spaceSize"
                       value="${space.spaceSize}"
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-transparent"
                       required>
            </div>
        </div>

        <!-- 提交更新空间信息的按钮 -->
        <div class="text-center">
            <button type="submit"
                    class="w-full py-3 rounded-lg bg-blue-500 text-white font-bold shadow-md hover:bg-blue-600 transform hover:scale-105 transition-all duration-300">
                更新
            </button>
        </div>
    </form>
</div>

</body>
</html>
