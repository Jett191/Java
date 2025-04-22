package com.homework2.controller;


import com.homework2.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class FileController {

  @Autowired
  private FileService fileService;

  @GetMapping("/home")
  public String showRegister() {
    return "home";
  }




}
