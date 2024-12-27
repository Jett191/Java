<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <!-- 引入Tailwind CSS框架用于快速样式布局 -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
      /* 设置背景为渐变动画 */
      body {
        background: linear-gradient(135deg, #7ab2da, #f0b7d9, #a182da);
        background-size: 400% 400%;
        animation: gradientAnimation 10s ease infinite;
      }

      /* 渐变动画效果 */
      @keyframes gradientAnimation {
        0%, 100% { background-position: 0 50%; }
        50% { background-position: 100% 50%; }
      }
    </style>
    <script>
      // 切换密码显示与隐藏
      function togglePassword() {
        const passwordInput = document.getElementById('userPassword');
        const eyeIcon = document.getElementById('eyeIcon');
        if (passwordInput.type === 'password') {
          passwordInput.type = 'text';  // 显示密码
          eyeIcon.classList.replace('fa-eye', 'fa-eye-slash');  // 更改图标
        } else {
          passwordInput.type = 'password';  // 隐藏密码
          eyeIcon.classList.replace('fa-eye-slash', 'fa-eye');  // 更改图标
        }
      }
    </script>
    <!-- 引入FontAwesome图标库，用于密码显示/隐藏图标 -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center min-h-screen">
<div class="bg-white bg-opacity-85 rounded-3xl shadow-xl p-8 w-full max-w-md transform transition-transform hover:scale-105">
    <div class="text-center mb-6">
        <!-- 用户头像图标 -->
        <i class="fas fa-user-circle text-6xl text-gradient-to-r from-purple-400 via-pink-400 to-blue-400 mb-4"></i>
        <!-- 登录标题 -->
        <h3 class="text-3xl font-bold text-gray-800">登录</h3>
    </div>

    <!-- 如果存在错误信息，则显示 -->
    <c:if test="${not empty error}">
        <div class="bg-pink-100 border-l-4 border-pink-400 text-pink-700 px-4 py-3 rounded mb-4 flex items-center">
            <i class="fas fa-exclamation-circle text-pink-400 mr-2"></i>
                ${error}  <!-- 显示错误信息 -->
        </div>
    </c:if>

    <!-- 登录表单 -->
    <form action="${pageContext.request.contextPath}/user/login" method="post" class="space-y-6">
        <!-- 用户名输入框 -->
        <div class="relative">
            <i class="fas fa-user absolute top-1/2 transform -translate-y-1/2 left-3 text-gray-400"></i>
            <label>
                <input type="text" name="userName" class="pl-10 pr-4 py-3 border border-gray-300 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-pink-400 transition" placeholder="用户名" required>
            </label>
        </div>

        <!-- 密码输入框 -->
        <div class="relative">
            <i class="fas fa-lock absolute top-1/2 transform -translate-y-1/2 left-3 text-gray-400"></i>
            <label for="userPassword"></label><input type="password" id="userPassword" name="userPassword" class="pl-10 pr-12 py-3 border border-gray-300 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-purple-400 transition" placeholder="密码" required>
            <!-- 密码显示/隐藏图标 -->
            <i id="eyeIcon" class="fas fa-eye absolute top-1/2 transform -translate-y-1/2 right-3 text-gray-400 cursor-pointer" onclick="togglePassword()"></i>
        </div>

        <!-- 登录按钮 -->
        <button type="submit" class="w-full bg-gradient-to-r from-purple-400 via-pink-400 to-blue-400 text-white font-bold py-3 rounded-lg hover:from-purple-500 hover:via-pink-500 hover:to-blue-500 transition shadow-lg">
            登录
        </button>
    </form>

    <!-- 注册链接 -->
    <div class="text-center mt-6">
        <a href="${pageContext.request.contextPath}/user/register" class="text-gradient-to-r from-purple-400 via-pink-400 to-blue-400 hover:underline">
            没有账号？立即注册
        </a>
    </div>
</div>
</body>
</html>