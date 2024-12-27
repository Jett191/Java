<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>个人空间管理</title>
    <!-- 引入Tailwind CSS框架 -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
      /* 设置页面背景为渐变动画 */
      body {
        background: linear-gradient(135deg, #7ab2da, #f0b7d9, #a182da);
        background-size: 400% 400%;
        animation: gradientAnimation 12s ease infinite;
      }

      /* 渐变背景动画效果 */
      @keyframes gradientAnimation {
        0% { background-position: 0 50%; }
        50% { background-position: 100% 50%; }
        100% { background-position: 0 50%; }
      }
    </style>
</head>
<body class="min-h-screen flex flex-col items-center justify-start py-8">

<!-- Header部分 -->
<div class="w-full max-w-4xl px-4 mb-6">
    <h1 class="text-2xl font-bold text-center bg-gradient-to-r from-blue-400 via-pink-400 to-purple-500 text-transparent bg-clip-text">
        个人空间管理
    </h1>
    <!-- 返回文件列表链接 -->
    <div class="mt-4 text-center">
        <a href="${pageContext.request.contextPath}/file/list"
           class="inline-block bg-gradient-to-r from-blue-500 via-pink-500 to-purple-500 text-white font-medium py-2 px-6 rounded-lg shadow-md hover:shadow-lg transform hover:scale-105 transition-all duration-300">
            返回文件列表
        </a>
    </div>
</div>

<!-- 表单卡片 -->
<div class="w-full max-w-2xl bg-white bg-opacity-90 backdrop-blur-md shadow-lg rounded-xl p-6">
    <form action="${pageContext.request.contextPath}/space/update" method="post" class="space-y-6">
        <!-- 空间名称输入框 -->
        <div>
            <label for="spaceName" class="block text-sm font-medium text-gray-700">空间名称</label>
            <div class="mt-1">
                <input type="text" id="spaceName" name="spaceName"
                       value="${space.spaceName}"
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 shadow-sm focus:outline-none focus:ring-2 focus:ring-pink-400 focus:border-transparent"
                       required>
            </div>
        </div>

        <!-- 空间大小输入框 -->
        <div>
            <label for="spaceSize" class="block text-sm font-medium text-gray-700">空间大小 (MB)</label>
            <div class="mt-1">
                <input type="number" id="spaceSize" name="spaceSize"
                       value="${space.spaceSize}"
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 shadow-sm focus:outline-none focus:ring-2 focus:ring-purple-400 focus:border-transparent"
                       required>
            </div>
        </div>

        <!-- 提交按钮 -->
        <div class="text-center">
            <button type="submit"
                    class="w-full py-3 rounded-lg bg-gradient-to-r from-blue-400 via-pink-500 to-purple-500 text-white font-bold shadow-md hover:shadow-lg transform hover:scale-105 transition-all duration-300">
                更新
            </button>
        </div>
    </form>
</div>

</body>
</html>