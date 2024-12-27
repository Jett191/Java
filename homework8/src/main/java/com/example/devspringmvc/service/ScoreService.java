package com.example.devspringmvc.service;

import com.example.devspringmvc.model.Score;
import java.util.List;

public interface ScoreService {
    List<Score> findByStudentId(Integer studentId);
    void save(Score score);
    void deleteByStudentId(Integer studentId);
} 