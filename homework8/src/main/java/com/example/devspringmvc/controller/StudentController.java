package com.example.devspringmvc.controller;

import com.example.devspringmvc.model.Photo;
import com.example.devspringmvc.model.Student;
import com.example.devspringmvc.service.PhotoService;
import com.example.devspringmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "student/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name, Model model) {
        List<Student> students;
        if (name != null && !name.trim().isEmpty()) {
            students = studentService.findByName(name.trim());
        } else {
            students = studentService.findAll();
        }
        model.addAttribute("students", students);
        model.addAttribute("searchName", name);
        return "student/list";
    }

    @GetMapping("/add")
    public String addForm() {
        return "student/add";
    }

    @PostMapping("/add")
    public String add(Student student, @RequestParam("photoFile") MultipartFile photoFile) {
        studentService.save(student, photoFile);
        return "redirect:/student/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/edit";
    }

    @PostMapping("/edit")
    public String edit(Student student, @RequestParam(value = "photoFile", required = false) MultipartFile photoFile) {
        studentService.update(student, photoFile);
        return "redirect:/student/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return "redirect:/student/list";
    }

    @GetMapping("/photo/{photoId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String photoId) {
        Photo photo = photoService.findById(photoId);
        if (photo != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(photo.getContentType()))
                    .body(photo.getData());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/photo/download/{photoId}")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable String photoId) {
        Photo photo = photoService.findById(photoId);
        if (photo != null) {
            String filename = photo.getFileName();
            // 处理中文文件名
            try {
                filename = URLEncoder.encode(filename, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                filename = "photo.jpg";
            }
            
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(photo.getContentType()))  // 使用原始的Content-Type
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .header(HttpHeaders.PRAGMA, "no-cache")
                    .header(HttpHeaders.CACHE_CONTROL, "no-cache")
                    .body(photo.getData());
        }
        return ResponseEntity.notFound().build();
    }
} 