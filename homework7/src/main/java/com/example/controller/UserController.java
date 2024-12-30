package com.example.controller;

import com.example.entity.User; // 导入用户实体类，表示用户的基本信息
import com.example.service.UserService; // 导入用户服务层，用于处理用户相关的业务逻辑
import org.springframework.beans.factory.annotation.Autowired; // 用于自动注入依赖
import org.springframework.stereotype.Controller; // 将该类标识为Spring MVC的控制器
import org.springframework.ui.Model; // 用于将数据传递给视图
import org.springframework.web.bind.annotation.GetMapping; // 处理GET请求的注解
import org.springframework.web.bind.annotation.PostMapping; // 处理POST请求的注解
import org.springframework.web.bind.annotation.RequestMapping; // 映射请求路径的注解
import org.springframework.web.bind.annotation.RequestParam; // 用于获取请求参数的注解
import javax.servlet.http.HttpSession; // 用于管理HTTP会话，处理用户登录状态

@Controller // 声明该类为控制器类
@RequestMapping("/user") // 映射 "/user" 路径下的请求
public class UserController {

    @Autowired
    private UserService userService; // 自动注入用户服务类，处理与用户相关的逻辑操作

    // 显示登录页面
    @GetMapping("/login") // 映射 "/user/login" 的GET请求，显示登录页面
    public String showLoginPage() {
        return "login"; // 返回登录页面的视图名称
    }

    // 显示注册页面
    @GetMapping("/register") // 映射 "/user/register" 的GET请求，显示注册页面
    public String showRegisterPage() {
        return "register"; // 返回注册页面的视图名称
    }

    // 处理用户注册操作
    @PostMapping("/register") // 映射 "/user/register" 的POST请求，处理注册表单提交
    public String register(@RequestParam String userName, @RequestParam String userPassword) {
        User user = new User(); // 创建新的用户对象
        user.setUserName(userName); // 设置用户的用户名
        user.setUserPassword(userPassword); // 设置用户的密码
        userService.register(user); // 调用用户服务注册用户
        return "login"; // 注册成功后，重定向到登录页面
    }

    // 处理用户登录操作
    @PostMapping("/login") // 映射 "/user/login" 的POST请求，处理登录表单提交
    public String login(HttpSession session, @RequestParam String userName,
        @RequestParam String userPassword, Model model) {
        // 调用用户服务进行登录验证
        User user = userService.login(userName, userPassword);
        if (user != null) {
            session.setAttribute("loginUser", user); // 登录成功，存储用户信息到会话中
            return "redirect:/file/list"; // 登录成功后，跳转到文件列表页面
        }
        model.addAttribute("error", "用户名或密码错误"); // 登录失败，传递错误信息到视图
        return "login"; // 登录失败后，返回登录页面
    }
}
