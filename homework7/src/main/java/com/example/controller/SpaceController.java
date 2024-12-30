package com.example.controller;

import com.example.dao.SpaceDao; // 用于与空间数据交互
import com.example.entity.Space; // 空间实体
import com.example.entity.User; // 用户实体
import com.example.service.SpaceService; // 空间业务逻辑
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class SpaceController {

    @Autowired
    private SpaceService spaceService; // 空间服务
    @Autowired
    private SpaceDao spaceDao; // 空间数据库操作

    // 显示空间管理页面
    @GetMapping("/space/manage")
    public String manageSpace(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login"; // 未登录重定向到登录页面
        }
        Space space = spaceService.getSpaceByUserId(user.getUserId()); // 获取用户空间信息
        model.addAttribute("space", space); // 传递空间信息到视图
        return "spaceManage"; // 返回空间管理页面
    }

    // 更新空间信息
    @PostMapping("/space/update")
    public String updateSpace(@RequestParam String spaceName, @RequestParam Long spaceSize, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login"; // 未登录重定向到登录页面
        }
        Space space = spaceService.getSpaceByUserId(user.getUserId());
        space.setSpaceName(spaceName); // 更新空间名称
        space.setSpaceSize(spaceSize); // 更新空间大小
        spaceDao.save(space); // 保存更新后的空间信息
        return "redirect:/space/manage"; // 重定向回空间管理页面
    }
}