package com.example.devspringmvc.service.impl;

import com.example.devspringmvc.dao.StudentDao;
import com.example.devspringmvc.dao.ScoreDao;
import com.example.devspringmvc.model.Photo;
import com.example.devspringmvc.model.Student;
import com.example.devspringmvc.service.PhotoService;
import com.example.devspringmvc.service.StudentService;
import com.example.devspringmvc.exception.ServiceException;
import com.example.devspringmvc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentDao studentDao;
    
    @Autowired
    private PhotoService photoService;
    
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public Student findById(Integer id) {
        return studentDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Student student, MultipartFile photoFile) {
        ValidationUtil.validateStudent(student.getStudentNo(), student.getName());
        ValidationUtil.validatePhoto(photoFile);
        
        if (studentDao.findByStudentNo(student.getStudentNo()) != null) {
            throw new ServiceException("学号已存在：" + student.getStudentNo());
        }
        
        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                String photoId = UUID.randomUUID().toString();
                Photo photo = new Photo();
                photo.setId(photoId);
                photo.setFileName(photoFile.getOriginalFilename());
                photo.setContentType(photoFile.getContentType());
                photo.setData(photoFile.getBytes());
                
                photoService.save(photo);
                student.setPhotoId(photoId);
            } catch (Exception e) {
                throw new ServiceException("保存照片失败：" + e.getMessage(), e);
            }
        }
        studentDao.insert(student);
    }

    @Override
    @Transactional
    public void update(Student student, MultipartFile photoFile) {
        ValidationUtil.validateStudent(student.getStudentNo(), student.getName());
        ValidationUtil.validatePhoto(photoFile);
        
        Student existingStudent = studentDao.findByStudentNo(student.getStudentNo());
        if (existingStudent != null && !existingStudent.getId().equals(student.getId())) {
            throw new ServiceException("学号已被其他学生使用：" + student.getStudentNo());
        }
        
        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                // 如果学生已有照片，先删除旧照片
                Student oldStudent = studentDao.findById(student.getId());
                if (oldStudent != null && oldStudent.getPhotoId() != null) {
                    photoService.deleteById(oldStudent.getPhotoId());
                }
                
                String photoId = UUID.randomUUID().toString();
                Photo photo = new Photo();
                photo.setId(photoId);
                photo.setFileName(photoFile.getOriginalFilename());
                photo.setContentType(photoFile.getContentType());
                photo.setData(photoFile.getBytes());
                
                photoService.save(photo);
                student.setPhotoId(photoId);
            } catch (Exception e) {
                throw new ServiceException("更新照片失败：" + e.getMessage(), e);
            }
        }
        studentDao.update(student);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Student student = studentDao.findById(id);
        if (student != null && student.getPhotoId() != null) {
            photoService.deleteById(student.getPhotoId());
        }
        scoreDao.deleteByStudentId(id);
        studentDao.deleteById(id);
    }
} 