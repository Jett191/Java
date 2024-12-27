package com.example.devspringmvc.service.impl;

import com.example.devspringmvc.dao.ScoreDao;
import com.example.devspringmvc.dao.StudentDao;
import com.example.devspringmvc.model.Score;
import com.example.devspringmvc.model.Student;
import com.example.devspringmvc.service.ScoreService;
import com.example.devspringmvc.exception.ServiceException;
import com.example.devspringmvc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    
    @Autowired
    private ScoreDao scoreDao;
    
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Score> findByStudentId(Integer studentId) {
        Student student = studentDao.findById(studentId);
        if (student == null) {
            throw new ServiceException("学生不存在");
        }
        return scoreDao.findByStudentId(studentId);
    }

    @Override
    @Transactional
    public void save(Score score) {
        // 验证学生是否存在
        Student student = studentDao.findById(score.getStudentId());
        if (student == null) {
            throw new ServiceException("学生不存在");
        }
        
        // 验证成绩信息
        ValidationUtil.validateScore(score.getSubject(), score.getScore());
        ValidationUtil.validateExamTime(score.getExamTime());
        
        // 检查是否已存在相同科目的成绩
        List<Score> existingScores = scoreDao.findByStudentId(score.getStudentId());
        for (Score existingScore : existingScores) {
            if (existingScore.getSubject().equals(score.getSubject())) {
                throw new ServiceException("该学生已有" + score.getSubject() + "科目的成绩记录");
            }
        }
        
        scoreDao.insert(score);
    }

    @Override
    @Transactional
    public void deleteByStudentId(Integer studentId) {
        Student student = studentDao.findById(studentId);
        if (student == null) {
            throw new ServiceException("学生不存在");
        }
        scoreDao.deleteByStudentId(studentId);
    }
} 