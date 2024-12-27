package com.example.devspringmvc.util;

import com.example.devspringmvc.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ValidationUtil {
    
    private static final Set<String> ALLOWED_CONTENT_TYPES = new HashSet<>(Arrays.asList(
        "image/jpeg",
        "image/png",
        "image/gif"
    ));
    
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    
    public static void validatePhoto(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return;
        }
        
        // 检查文件类型
        String contentType = file.getContentType();
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new ServiceException("不支持的文件类型，仅支持JPG、PNG和GIF格式");
        }
        
        // 检查文件大小
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new ServiceException("文件大小超过限制，最大支持5MB");
        }
    }
    
    public static void validateStudent(String studentNo, String name) {
        if (studentNo == null || studentNo.trim().isEmpty()) {
            throw new ServiceException("学号不能为空");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new ServiceException("姓名不能为空");
        }
        if (studentNo.length() > 20) {
            throw new ServiceException("学号长度不能超过20个字符");
        }
        if (name.length() > 50) {
            throw new ServiceException("姓名长度不能超过50个字符");
        }
    }
    
    public static void validateScore(String subject, BigDecimal score) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new ServiceException("科目不能为空");
        }
        if (subject.length() > 50) {
            throw new ServiceException("科目名称长度不能超过50个字符");
        }
        if (score == null) {
            throw new ServiceException("分数不能为空");
        }
        if (score.compareTo(BigDecimal.ZERO) < 0 || score.compareTo(new BigDecimal("100")) > 0) {
            throw new ServiceException("分数必须在0-100之间");
        }
    }
    
    public static void validateExamTime(Date examTime) {
        if (examTime == null) {
            throw new ServiceException("考试时间不能为空");
        }
        if (examTime.after(new Date())) {
            throw new ServiceException("考试时间不能晚于当前时间");
        }
    }
} 