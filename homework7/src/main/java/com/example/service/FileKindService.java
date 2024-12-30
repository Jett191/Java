package com.example.service;

import com.example.dao.FileKindDao; // 导入文件分类数据访问层，处理文件分类的持久化操作
import com.example.entity.FileKind; // 导入文件分类实体类，表示文件分类的数据模型
import org.springframework.beans.factory.annotation.Autowired; // 用于自动注入依赖
import org.springframework.stereotype.Service; // 标识该类为服务层组件
import javax.transaction.Transactional; // 用于事务管理，确保方法的原子性

@Service // 标记该类为Spring的服务层组件
@Transactional // 自动管理该类中所有方法的事务，确保数据一致性
public class FileKindService {

    @Autowired
    private FileKindDao categoryRepository; // 自动注入文件分类数据访问层

    // 保存新的文件分类
    public void addCategory(FileKind category) {
        categoryRepository.save(category); // 调用数据访问层保存文件分类
    }

    // 获取所有文件分类
    public Iterable<FileKind> getAllCategories() {
        return categoryRepository.findAll(); // 从数据访问层获取所有文件分类数据
    }
}
