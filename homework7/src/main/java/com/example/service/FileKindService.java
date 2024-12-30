package com.example.service;

import com.example.dao.FileKindDao; // 文件分类数据访问层
import com.example.entity.FileKind; // 文件分类实体
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service // 标记该类为服务层
@Transactional // 表示该类的方法会进行事务管理
public class FileKindService {

    @Autowired
    private FileKindDao categoryRepository; // 注入文件分类数据访问层

    // 添加文件分类
    public void addCategory(FileKind category) {
        categoryRepository.save(category); // 保存文件分类
    }

    // 获取所有文件分类
    public Iterable<FileKind> getAllCategories() {
        return categoryRepository.findAll(); // 返回所有文件分类
    }
}