package com.example.devspringmvc.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

public class Score {
    private Integer id;
    private Integer studentId;
    private String subject;
    private BigDecimal score;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date examTime;
    
    // Getter和Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }
} 