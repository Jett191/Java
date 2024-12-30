<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
      body {
        background-color: #ffffff;
      }
    </style>
    <script>
      // 切换密码字段的可见性
      function togglePassword() {
        const passwordInput = document.getElementById('userPassword');
        const eyeIcon = document.getElementById('eyeIcon');
        if (passwordInput.type === 'password') {
          passwordInput.type = 'text';
          eyeIcon.classList.replace('fa-eye', 'fa-eye-slash');
        } else {
          passwordInput.type = 'password';
          eyeIcon.classList.replace('fa-eye-slash', 'fa-eye');
        }
      }
    </script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center min-h-screen">
<div class="bg-white shadow-xl p-8 w-full max-w-md transform transition-transform hover:scale-105">
    <div class="text-center mb-6">
        <i class="fas fa-user-circle text-6xl text-blue-500 mb-4"></i>
        <h3 class="text-3xl font-bold text-gray-800">登录</h3>
    </div>

    <c:if test="${not empty error}">
        <div class="bg-red-100 border-l-4 border-red-400 text-red-700 px-4 py-3 rounded mb-4 flex items-center">
            <i class="fas fa-exclamation-circle text-red-400 mr-2"></i>
                ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/user/login" method="post" class="space-y-6">
        <div class="relative">
            <i class="fas fa-user absolute top-1/2 transform -translate-y-1/2 left-3 text-gray-400"></i>
            <label>
                <input type="text" name="userName" class="pl-10 pr-4 py-3 border border-gray-300 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-blue-400 transition" placeholder="用户名" required>
            </label>
        </div>

        <div class="relative">
            <i class="fas fa-lock absolute top-1/2 transform -translate-y-1/2 left-3 text-gray-400"></i>
            <label for="userPassword"></label><input type="password" id="userPassword" name="userPassword" class="pl-10 pr-12 py-3 border border-gray-300 rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-blue-400 transition" placeholder="密码" required>
            <i id="eyeIcon" class="fas fa-eye absolute top-1/2 transform -translate-y-1/2 right-3 text-gray-400 cursor-pointer" onclick="togglePassword()"></i>
        </div>

        <button type="submit" class="w-full bg-blue-500 text-white font-bold py-3 rounded-lg hover:bg-blue-600 transition shadow-lg">
            登录
        </button>
    </form>

    <div class="text-center mt-6">
        <a href="${pageContext.request.contextPath}/user/register" class="text-blue-500 hover:underline">
            没有账号？立即注册
        </a>
    </div>
</div>
</body>
</html>
