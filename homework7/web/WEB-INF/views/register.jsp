<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>注册</title>
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
<body class="flex items-center justify-center min-h-screen font-sans">
<div class="bg-white bg-opacity-90 backdrop-blur-md shadow-lg rounded-xl p-8 w-full max-w-md">
    <!-- Header部分 -->
    <div class="text-center mb-6">
        <!-- 显示图标 -->
        <svg class="w-16 h-16 mx-auto text-gradient" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4H9l4-8v6h3l-4 8z"></path>
        </svg>
        <!-- 显示注册标题 -->
        <h2 class="text-2xl font-bold bg-gradient-to-r from-blue-400 via-pink-400 to-purple-500 text-transparent bg-clip-text">
            注册
        </h2>
    </div>
    <!-- 注册表单 -->
    <form action="${pageContext.request.contextPath}/user/register" method="post" class="space-y-4">
        <!-- 用户名输入框 -->
        <div class="relative">
            <!-- 用户名图标 -->
            <span class="absolute inset-y-0 left-0 flex items-center pl-4">
                    <svg class="w-5 h-5 text-blue-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A13.937 13.937 0 0112 15c2.486 0 4.764.586 6.879 1.804M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                    </svg>
                </span>
            <label>
                <!-- 用户名输入框 -->
                <input type="text" name="userName" class="pl-12 pr-4 py-3 rounded-lg border border-blue-300 w-full focus:outline-none focus:ring-2 focus:ring-pink-400 focus:border-transparent" placeholder="用户名" required>
            </label>
        </div>
        <!-- 密码输入框 -->
        <div class="relative">
            <!-- 密码图标 -->
            <span class="absolute inset-y-0 left-0 flex items-center pl-4">
                    <svg class="w-5 h-5 text-pink-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c1.657 0 3-1.343 3-3V6a3 3 0 00-6 0v2c0 1.657 1.343 3 3 3z"></path>
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 11v6a8 8 0 0016 0v-6M6 11V9a6 6 0 1112 0v2"></path>
                    </svg>
                </span>
            <label>
                <!-- 密码输入框 -->
                <input type="password" name="userPassword" class="pl-12 pr-4 py-3 rounded-lg border border-pink-300 w-full focus:outline-none focus:ring-2 focus:ring-purple-400 focus:border-transparent" placeholder="密码" required>
            </label>
        </div>
        <!-- 注册按钮 -->
        <button type="submit" class="w-full py-3 rounded-lg bg-gradient-to-r from-blue-400 via-pink-500 to-purple-500 text-white font-bold shadow-md hover:shadow-lg transform hover:scale-105 transition-all duration-300">
            注册
        </button>
    </form>
    <!-- 链接部分 -->
    <div class="text-center mt-4">
        <!-- 提供登录链接 -->
        <a href="${pageContext.request.contextPath}/user/login" class="text-sm text-blue-500 hover:underline">
            已有账号？立即登录
        </a>
    </div>
</div>
</body>
</html>