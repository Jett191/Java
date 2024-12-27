package com.example.devspringmvc.service.impl;

import com.example.devspringmvc.dao.PhotoDao;
import com.example.devspringmvc.model.Photo;
import com.example.devspringmvc.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoServiceImpl implements PhotoService {
    
    @Autowired
    private PhotoDao photoDao;

    @Override
    public Photo findById(String id) {
        return photoDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Photo photo) {
        photoDao.insert(photo);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        photoDao.deleteById(id);
    }
} 