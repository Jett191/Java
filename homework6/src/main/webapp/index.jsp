<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>文件管理系统</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- 引入样式 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar {
            padding: 1rem;
            background-color: #1976d2;
            color: white;
        }
        .navbar-brand {
            color: white;
            font-size: 1.5rem;
        }
        .nav-link {
            color: white !important;
        }
        .modal-header {
            background-color: #1976d2;
            color: white;
        }
        .btn-primary {
            background-color: #1976d2;
            border-color: #1976d2;
        }
    </style>
</head>
<body>
    <!-- 导航栏 -->
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <span class="navbar-brand">文件管理系统</span>
            <div class="d-flex">
                <button class="btn btn-outline-light me-2">登录</button>
                <button class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#registerModal">注册</button>
            </div>
        </div>
    </nav>

    <!-- 注册模态框 -->
    <div class="modal fade" id="registerModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">用户注册</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="registerForm">
                        <div class="mb-3">
                            <label for="username" class="form-label">用户名</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">密码</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">邮箱</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="registerButton">注册</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Toast 提示 -->
    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
        <div id="toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="me-auto">提示</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body" id="toastMessage"></div>
        </div>
    </div>

    <!-- 引入脚本 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const registerButton = document.getElementById('registerButton');
            const registerModal = new bootstrap.Modal(document.getElementById('registerModal'));
            const toast = new bootstrap.Toast(document.getElementById('toast'));
            const toastMessage = document.getElementById('toastMessage');

            registerButton.addEventListener('click', async function() {
                const username = document.getElementById('username').value;
                const password = document.getElementById('password').value;
                const email = document.getElementById('email').value;

                const formData = {
                    username: username,
                    password: password,
                    email: email
                };

                try {
                    console.log('Sending registration request:', formData);
                    const response = await axios.post('/homework6_war_exploded/user/register', formData, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    console.log('Registration response:', response);

                    if (response.data.success) {
                        toastMessage.textContent = '注册成功！';
                        registerModal.hide();
                        document.getElementById('registerForm').reset();
                    } else {
                        toastMessage.textContent = response.data.message || '注册失败';
                    }
                } catch (error) {
                    console.error('Registration error:', error);
                    toastMessage.textContent = error.response?.data?.message || '注册失败，请稍后重试';
                }
                toast.show();
            });
        });
    </script>
</body>
</html>
