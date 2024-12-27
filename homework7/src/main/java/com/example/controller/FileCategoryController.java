package com.example.controller;

import com.example.dao.FileCategoryRepository; // 引入文件分类的Repository，用于与数据库交互
import com.example.entity.FileCategory; // 引入FileCategory实体类，代表文件分类的数据模型
import com.example.service.FileCategoryServiceImpl; // 引入FileCategoryServiceImpl，处理业务逻辑
import org.springframework.beans.factory.annotation.Autowired; // 引入Autowired注解，用于自动注入依赖
import org.springframework.stereotype.Controller; // 引入Controller注解，标记为Spring MVC的控制器
import org.springframework.web.bind.annotation.PostMapping; // 引入PostMapping注解，处理HTTP POST请求
import org.springframework.web.bind.annotation.RequestMapping; // 引入RequestMapping注解，映射请求路径
import org.springframework.web.bind.annotation.RequestParam; // 引入RequestParam注解，用于获取请求参数
import org.springframework.web.bind.annotation.ResponseBody; // 引入ResponseBody注解，表示返回的结果直接作为响应体

@Controller // 标记该类为Spring MVC的控制器
@RequestMapping("/category") // 请求映射的基本路径，所有该类的方法将以 "/category" 为基础路径
public class FileCategoryController {

    @Autowired
    private FileCategoryRepository categoryRepository; // 自动注入FileCategoryRepository，管理数据库操作

    // 此方法为内部方法，未被请求映射
    public void addCategory(FileCategory category) {
        categoryRepository.save(category); // 保存文件分类到数据库
    }

    @Autowired
    private FileCategoryServiceImpl categoryService; // 自动注入FileCategoryServiceImpl，处理文件分类的业务逻辑

    @PostMapping("/add") // 处理 "/category/add" 路径的POST请求
    @ResponseBody // 表示返回的数据直接写入HTTP响应体中
    public String addCategory(@RequestParam String cateName) {
        // 接收前端请求中的参数 cateName，代表文件分类的名称
        FileCategory category = new FileCategory(); // 创建一个新的FileCategory对象
        category.setCateName(cateName); // 设置文件分类的名称为请求中的参数
        categoryService.addCategory(category); // 调用业务层的addCategory方法，执行保存操作
        return "success"; // 返回字符串"success"，表示操作成功
    }
}