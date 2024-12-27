package com.example.devspringmvc.service;

import com.example.devspringmvc.model.Photo;

public interface PhotoService {
    Photo findById(String id);
    void save(Photo photo);
    void deleteById(String id);
} 