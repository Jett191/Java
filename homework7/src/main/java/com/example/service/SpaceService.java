package com.example.service;

import com.example.dao.SpaceDao; // 导入空间数据访问层，处理与空间数据的持久化操作
import com.example.entity.Space; // 导入空间实体类，表示用户存储空间的模型
import org.springframework.beans.factory.annotation.Autowired; // 用于自动注入依赖
import org.springframework.stereotype.Service; // 标记该类为服务层组件
import javax.transaction.Transactional; // 用于确保事务管理

@Service // 标记该类为服务层，业务逻辑处理类
@Transactional // 事务管理，确保方法执行的原子性
public class SpaceService {

    @Autowired
    private SpaceDao spaceDao; // 自动注入空间数据访问层

    // 创建新的用户空间
    public Space createSpace(Space space) {
        return spaceDao.save(space); // 保存用户空间数据到数据库
    }

    // 根据用户ID获取用户的存储空间信息
    public Space getSpaceByUserId(Integer userId) {
        return spaceDao.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("用户空间不存在")); // 如果空间不存在，抛出异常
    }

    // 更新用户已使用的空间大小
    public void updateUsedSpace(Integer userId, Long additionalSize) {
        Space space = getSpaceByUserId(userId); // 获取用户的存储空间信息
        space.setUsedSpace(space.getUsedSpace() + additionalSize); // 更新已使用空间大小
        spaceDao.save(space); // 保存更新后的空间数据
    }
}
