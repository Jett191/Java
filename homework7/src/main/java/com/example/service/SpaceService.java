package com.example.service;

import com.example.dao.SpaceDao; // 空间数据访问层
import com.example.entity.Space; // 空间实体
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service // 标记该类为服务层
@Transactional // 事务管理
public class SpaceService {

    @Autowired
    private SpaceDao spaceDao; // 注入空间数据访问层

    // 创建新的空间
    public Space createSpace(Space space) {
        return spaceDao.save(space); // 保存空间信息
    }

    // 根据用户ID获取空间信息
    public Space getSpaceByUserId(Integer userId) {
        return spaceDao.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("用户空间不存在")); // 若未找到，则抛出异常
    }

    // 更新用户空间已使用的空间大小
    public void updateUsedSpace(Integer userId, Long additionalSize) {
        Space space = getSpaceByUserId(userId); // 获取用户空间
        space.setUsedSpace(space.getUsedSpace() + additionalSize); // 更新已用空间
        spaceDao.save(space); // 保存更新后的空间信息
    }
}