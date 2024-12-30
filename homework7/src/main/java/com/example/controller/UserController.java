package com.example.controller;

import com.example.entity.User; // 用户实体
import com.example.service.UserService; // 用户业务逻辑
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService; // 用户服务

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // 返回登录页面视图
    }

    // 显示注册页面
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // 返回注册页面视图
    }

    // 处理用户注册
    @PostMapping("/register")
    public String register(@RequestParam String userName, @RequestParam String userPassword) {
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        userService.register(user); // 注册用户
        return "login"; // 注册后返回登录页面
    }

    // 处理用户登录
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String userName,
        @RequestParam String userPassword, Model model) {
        User user = userService.login(userName, userPassword); // 登录验证
        if (user != null) {
            session.setAttribute("loginUser", user); // 登录成功，将用户存入会话
            return "redirect:/file/list"; // 登录成功，跳转到文件列表页面
        }
        model.addAttribute("error", "用户名或密码错误"); // 登录失败，添加错误信息
        return "login"; // 返回登录页面
    }
}