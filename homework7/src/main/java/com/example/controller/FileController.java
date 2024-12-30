package com.example.controller;

import com.example.dao.FileDao;
import com.example.entity.File;
import com.example.entity.User;
import com.example.service.FileKindService;
import com.example.service.FileService;
import com.example.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping("/file") // 设置该类处理请求的基本路径为 "/file"
public class FileController {

    @Autowired
    private FileService fileService; // 自动注入FileServiceImpl，处理文件的业务逻辑

    @Autowired
    private FileKindService categoryService; // 处理文件分类的业务逻辑

    @Autowired
    private SpaceService spaceService; // 处理空间管理的业务逻辑

    @Autowired
    private FileDao fileDao; // 用于文件数据的持久化操作

    // 显示上传页面，获取所有文件分类信息并传递到视图
    @GetMapping("/upload")
    public String showUploadPage(Model model, HttpSession session) {
        // 会话获取登录用户
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            // 未登录 重定向
            return "redirect:/login";
        }
        // 获取所有文件分类信息，并将其添加到模型中，以便在视图中使用
        model.addAttribute("categories", categoryService.getAllCategories());
        return "upload"; // 返回上传页面
    }

    // 显示文件列表页面，获取当前用户的文件列表并传递到视图
    @GetMapping("/list")
    public String showFileList(Model model, HttpSession session) {
        // 从会话中获取当前登录用户
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            // 如果用户未登录，重定向到登录页面
            return "redirect:/login";
        }
        // 获取当前用户的文件列表，并将其添加到模型中
        model.addAttribute("files", fileService.getUserFiles(user.getUserId()));
        return "fileList"; // 返回文件列表页面
    }

    // 处理文件上传请求
    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file, // 获取上传的文件
        @RequestParam Integer cateId, // 获取文件所属分类ID
        @RequestParam(required = false) String uploadDir,
        HttpSession session) {
        // 从会话中获取当前登录用户
        User user = (User) session.getAttribute("loginUser");

        try {
            // 调用文件服务层的saveFile方法保存文件
            fileService.saveFile(file, user.getUserId(), cateId, uploadDir);
            return "redirect:/file/list?success=true"; // 上传成功，重定向到文件列表页面
        } catch (Exception e) {
            // 如果发生错误，重定向到文件列表页面并附带错误信息
            return "redirect:/file/list?error=upload";
        }
    }

    // 处理文件下载请求
    @GetMapping("/download/{fileId}")
    public void downloadFile(@PathVariable Integer fileId, HttpServletResponse response) {
        // 从数据库中获取文件信息
        File fileInfo = fileDao.findById(fileId)
            .orElseThrow(() -> new RuntimeException("文件不存在"));

        // 根据文件路径和文件保存名构建文件对象
        java.io.File file = new java.io.File(fileInfo.getFilePath(), fileInfo.getFileSaveName());

        // 如果文件不存在，抛出异常
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }

        // 设置HTTP响应头，告知浏览器该响应是一个下载文件
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileRealName() + "\"");
        response.setContentLengthLong(fileInfo.getFileSize());

        // 更新文件的下载次数
        fileInfo.setDownCount(fileInfo.getDownCount() + 1);
        fileDao.save(fileInfo);

        // 读取文件并写入到HTTP响应流中，供用户下载
        try (InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead); // 将读取的数据写入响应
            }
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败", e); // 如果下载过程中出现异常，抛出运行时异常
        }
    }

    // 切换文件的冻结状态
    @PostMapping("/toggle-status/{fileId}")
    @ResponseBody // 返回响应体
    public ResponseEntity<?> toggleFileStatus(@PathVariable Integer fileId) {
        // 调用文件服务层的toggleFileStatus方法来切换文件状态
        fileService.toggleFileStatus(fileId);
        return ResponseEntity.ok().build(); // 返回HTTP 200响应，表示操作成功
    }
}