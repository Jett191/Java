package com.example.controller;

import com.example.dao.FileKindDao; // 导入文件分类数据库操作类，用于执行增删改查
import com.example.entity.FileKind; // 导入文件分类实体类，定义文件分类的数据结构
import com.example.service.FileKindService; // 导入业务逻辑层服务，用于处理文件分类相关的操作
import org.springframework.beans.factory.annotation.Autowired; // 用于自动注入Spring容器中的依赖
import org.springframework.stereotype.Controller; // 标识该类为Spring MVC的控制器组件
import org.springframework.web.bind.annotation.PostMapping; // 处理POST请求的注解
import org.springframework.web.bind.annotation.RequestMapping; // 用于配置请求路径的注解
import org.springframework.web.bind.annotation.RequestParam; // 用于获取请求参数的注解
import org.springframework.web.bind.annotation.ResponseBody; // 指定方法返回值直接写入响应体中

@Controller // 声明这是一个控制器类
@RequestMapping("/category") // 该控制器负责处理以 "/category" 为前缀的请求路径
public class FileKindController {

    @Autowired
    private FileKindDao categoryRepository; // 自动注入文件分类的数据库操作类，用于数据持久化

    // 该方法是内部辅助方法，不会被请求直接访问
    public void addCategory(FileKind category) {
        categoryRepository.save(category); // 将文件分类信息保存到数据库中
    }

    @Autowired
    private FileKindService categoryService; // 自动注入文件分类的业务逻辑处理类

    @PostMapping("/add") // 映射 "/category/add" 路径的POST请求
    @ResponseBody // 将返回值直接写入HTTP响应体，返回的内容无需视图解析
    public String addCategory(@RequestParam String cateName) {
        // 获取客户端请求中传递的文件分类名称
        FileKind category = new FileKind(); // 创建新的文件分类对象
        category.setCateName(cateName); // 设置文件分类名称
        categoryService.addCategory(category); // 调用服务层方法，处理保存操作
        return "success"; // 返回操作成功的提示信息
    }
}
