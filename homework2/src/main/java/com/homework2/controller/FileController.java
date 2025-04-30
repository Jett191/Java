package com.homework2.controller;

import com.homework2.dto.FileInfoResponse;
import com.homework2.service.FileService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

  @Autowired
  private FileService fileService;

  @GetMapping("/home")
  public String showHome(Model model,HttpSession session) {
    Integer userId = (Integer) session.getAttribute("currentUserId");
    List<FileInfoResponse> files = fileService.listFilesByUserId(userId);
    model.addAttribute("files", files);
    return "home";
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file,
      Model model, HttpSession session) {
    if (file.isEmpty()) {
      model.addAttribute("message", "请先选择一个文件");
      return "home";
    }
    Integer userId = (Integer) session.getAttribute("currentUserId");
    try {
      fileService.uploadFile(file, userId);
      model.addAttribute("success", true);
      model.addAttribute("message", "上传成功！");
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("success", false);
      model.addAttribute("message", "上传失败：" + e.getMessage());
    }
    return showHome(model, session);
  }






}
