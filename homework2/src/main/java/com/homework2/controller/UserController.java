package com.homework2.controller;

import com.homework2.dto.UserLoginRequest;
import com.homework2.dto.UserRegisterRequest;
import com.homework2.dto.UserLoginInfoResponse;
import com.homework2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/register")
  public String showRegister() {
    return "register";
  }

  @PostMapping("/register")
  public String register(UserRegisterRequest userInfo, Model model) {
    boolean ok = userService.register(userInfo);
    if (!ok) {
      model.addAttribute("error", "用户名或邮箱已存在");
      return "register";
    }
    return "redirect:/user/login";
  }

  @GetMapping("/login")
  public String showLogin() {
    return "login";
  }

  @PostMapping("/login")
  public String login(UserLoginRequest userInfo, Model model) {
    UserLoginInfoResponse user = userService.login(userInfo);
    if (user == null) {
      model.addAttribute("error", "用户名或密码错误");
      return "login";
    }
    model.addAttribute("user", user);
    return "home";
  }


}
