package com.example.devspringmvc.controller;

import com.example.devspringmvc.model.Score;
import com.example.devspringmvc.model.Student;
import com.example.devspringmvc.service.ScoreService;
import com.example.devspringmvc.service.StudentService;
import com.example.devspringmvc.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/score")
public class ScoreController {
    
    @Autowired
    private ScoreService scoreService;
    
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{studentId}")
    public String listByStudent(@PathVariable Integer studentId, Model model) {
        try {
            Student student = studentService.findById(studentId);
            if (student == null) {
                throw new ServiceException("学生不存在");
            }
            model.addAttribute("student", student);
            return "score/list";
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "error/service_error";
        }
    }

    @GetMapping("/add/{studentId}")
    public String addForm(@PathVariable Integer studentId, Model model) {
        Student student = studentService.findById(studentId);
        model.addAttribute("student", student);
        return "score/add";
    }

    @PostMapping("/add")
    public String add(Score score, RedirectAttributes redirectAttributes) {
        try {
            scoreService.save(score);
            return "redirect:/score/student/" + score.getStudentId();
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/score/add/" + score.getStudentId();
        }
    }
} 