package com.example.controller;

import com.example.dao.SpaceDao; // 导入空间数据库操作类，用于执行空间数据的持久化操作
import com.example.entity.Space; // 导入空间实体类，定义空间数据模型
import com.example.entity.User; // 导入用户实体类，用于获取当前登录用户信息
import com.example.service.SpaceService; // 导入空间业务逻辑服务，用于处理与空间相关的操作
import org.springframework.beans.factory.annotation.Autowired; // 用于自动注入依赖
import org.springframework.stereotype.Controller; // 标识该类为Spring MVC的控制器组件
import org.springframework.ui.Model; // 用于向视图传递数据
import org.springframework.web.bind.annotation.GetMapping; // 处理GET请求的注解
import org.springframework.web.bind.annotation.PostMapping; // 处理POST请求的注解
import org.springframework.web.bind.annotation.RequestParam; // 用于获取请求参数的注解
import javax.servlet.http.HttpSession; // 用于管理HTTP会话，获取当前用户的会话信息

@Controller // 声明这是一个Spring MVC控制器类
public class SpaceController {

    @Autowired
    private SpaceService spaceService; // 自动注入空间业务逻辑服务，处理空间数据相关操作

    @Autowired
    private SpaceDao spaceDao; // 自动注入空间数据访问层，进行空间信息的持久化操作

    // 显示空间管理界面
    @GetMapping("/space/manage") // 映射到 "/space/manage" 路径的GET请求
    public String manageSpace(HttpSession session, Model model) {
        // 获取当前会话中的用户信息
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login"; // 用户未登录，重定向到登录页面
        }
        Space space = spaceService.getSpaceByUserId(user.getUserId()); // 获取当前登录用户的空间信息
        model.addAttribute("space", space); // 将空间信息传递给视图层
        return "spaceManage"; // 返回空间管理视图
    }

    // 更新空间的相关信息
    @PostMapping("/space/update") // 映射到 "/space/update" 路径的POST请求
    public String updateSpace(@RequestParam String spaceName, @RequestParam Long spaceSize, HttpSession session) {
        // 获取当前会话中的用户信息
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login"; // 如果用户未登录，则重定向到登录页面
        }
        // 获取当前用户的空间信息
        Space space = spaceService.getSpaceByUserId(user.getUserId());
        space.setSpaceName(spaceName); // 更新空间名称
        space.setSpaceSize(spaceSize); // 更新空间大小
        spaceDao.save(space); // 将更新后的空间信息保存到数据库
        return "redirect:/space/manage"; // 更新后重定向回空间管理页面
    }
}
