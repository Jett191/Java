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
        #userInfo {
            display: none;
            color: white;
        }
    </style>
</head>
<body>
    <!-- 导航栏 -->
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <span class="navbar-brand">文件管理系统</span>
            <div class="d-flex align-items-center">
                <div id="userInfo" class="me-3">
                    欢迎，<span id="username"></span>
                </div>
                <div id="authButtons">
                    <button class="btn btn-outline-light me-2" data-bs-toggle="modal" data-bs-target="#loginModal">登录</button>
                    <button class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#registerModal">注册</button>
                </div>
                <button id="logoutBtn" class="btn btn-outline-light" style="display: none;">退出</button>
            </div>
        </div>
    </nav>

    <!-- 登录模态框 -->
    <div class="modal fade" id="loginModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">用户登录</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="loginForm">
                        <div class="mb-3">
                            <label for="loginUsername" class="form-label">用户名</label>
                            <input type="text" class="form-control" id="loginUsername" required>
                        </div>
                        <div class="mb-3">
                            <label for="loginPassword" class="form-label">密码</label>
                            <input type="password" class="form-control" id="loginPassword" required>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="loginButton">登录</button>
                </div>
            </div>
        </div>
    </div>

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
                            <label for="registerUsername" class="form-label">用户名</label>
                            <input type="text" class="form-control" id="registerUsername" required>
                        </div>
                        <div class="mb-3">
                            <label for="registerPassword" class="form-label">密码</label>
                            <input type="password" class="form-control" id="registerPassword" required>
                        </div>
                        <div class="mb-3">
                            <label for="registerEmail" class="form-label">邮箱</label>
                            <input type="email" class="form-control" id="registerEmail" required>
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
            const loginButton = document.getElementById('loginButton');
            const logoutBtn = document.getElementById('logoutBtn');
            const registerModal = new bootstrap.Modal(document.getElementById('registerModal'));
            const loginModal = new bootstrap.Modal(document.getElementById('loginModal'));
            const toast = new bootstrap.Toast(document.getElementById('toast'));
            const toastMessage = document.getElementById('toastMessage');
            const userInfo = document.getElementById('userInfo');
            const authButtons = document.getElementById('authButtons');
            const usernameSpan = document.getElementById('username');

            // 检查是否已登录
            const token = localStorage.getItem('token');
            if (token) {
                const username = localStorage.getItem('username');
                showLoggedInState(username);
            }

            // 配置axios拦截器
            axios.interceptors.request.use(
                config => {
                    const token = localStorage.getItem('token');
                    if (token) {
                        config.headers.Authorization = `Bearer ${token}`;
                    }
                    return config;
                },
                error => {
                    return Promise.reject(error);
                }
            );

            // 响应拦截器
            axios.interceptors.response.use(
                response => response,
                error => {
                    if (error.response && error.response.status === 401) {
                        // token过期或无效，清除本地存储并显示未登录状态
                        localStorage.removeItem('token');
                        localStorage.removeItem('userId');
                        localStorage.removeItem('username');
                        showLoggedOutState();
                        toastMessage.textContent = '登录已过期，请重新登录';
                        toast.show();
                    }
                    return Promise.reject(error);
                }
            );

            // 注册功能
            registerButton.addEventListener('click', async function() {
                const username = document.getElementById('registerUsername').value;
                const password = document.getElementById('registerPassword').value;
                const email = document.getElementById('registerEmail').value;

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

            // 登录功能
            loginButton.addEventListener('click', async function() {
                const username = document.getElementById('loginUsername').value;
                const password = document.getElementById('loginPassword').value;

                const loginData = {
                    username: username,
                    password: password
                };

                try {
                    console.log('Sending login request:', loginData);
                    const response = await axios.post('/homework6_war_exploded/user/login', loginData, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    console.log('Login response:', response);

                    if (response.data.success) {
                        // 保存token和用户信息
                        localStorage.setItem('token', response.data.token);
                        localStorage.setItem('userId', response.data.userId);
                        localStorage.setItem('username', response.data.username);

                        toastMessage.textContent = '登录成功！';
                        loginModal.hide();
                        document.getElementById('loginForm').reset();
                        
                        // 显示已登录状态
                        showLoggedInState(response.data.username);
                    } else {
                        toastMessage.textContent = response.data.message || '登录失败';
                    }
                } catch (error) {
                    console.error('Login error:', error);
                    toastMessage.textContent = error.response?.data?.message || '登录失败，请稍后重试';
                }
                toast.show();
            });

            // 退出功能
            logoutBtn.addEventListener('click', function() {
                localStorage.removeItem('token');
                localStorage.removeItem('userId');
                localStorage.removeItem('username');
                showLoggedOutState();
                toastMessage.textContent = '已退出登录';
                toast.show();
            });

            // 显示已登录状态
            function showLoggedInState(username) {
                userInfo.style.display = 'block';
                authButtons.style.display = 'none';
                logoutBtn.style.display = 'block';
                usernameSpan.textContent = username;
            }

            // 显示未登录状态
            function showLoggedOutState() {
                userInfo.style.display = 'none';
                authButtons.style.display = 'block';
                logoutBtn.style.display = 'none';
                usernameSpan.textContent = '';
            }
        });
    </script>
</body>
</html>
