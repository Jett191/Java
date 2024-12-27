package com.example.service;

import com.example.dao.FileRepository; // 文件信息数据访问层
import com.example.entity.FileInfo; // 文件信息实体
import com.example.entity.Space; // 空间实体
import org.apache.commons.io.FilenameUtils; // 用于处理文件名
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service // 标记该类为服务层
@Transactional // 事务管理
public class FileServiceImpl {

    @Autowired
    private FileRepository fileRepository; // 注入文件信息数据访问层
    @Autowired
    private SpaceServiceImpl spaceService; // 注入空间服务

    private static final String DEFAULT_UPLOAD_PATH = "uploads"; // 默认上传路径

    // 保存文件
    public FileInfo saveFile(MultipartFile file, Integer userId, Integer cateId, String uploadDir) throws IOException {
        Space space = spaceService.getSpaceByUserId(userId); // 获取用户空间信息
        if ((space.getSpaceSize() - space.getUsedSpace()) < file.getSize()) { // 检查是否有足够空间
            throw new RuntimeException("空间不足");
        }

        String saveName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename()); // 生成唯一文件名

        // 获取当前工作目录
        String basePath = System.getProperty("user.dir");
        // 使用指定的上传目录或默认目录
        String directoryPath = (uploadDir != null && !uploadDir.isEmpty()) ? uploadDir : DEFAULT_UPLOAD_PATH;
        File userDir = new File(basePath, directoryPath + "/" + userId);
        if (!userDir.exists()) {
            userDir.mkdirs(); // 创建用户文件夹
        }

        // 保存文件
        file.transferTo(new File(userDir, saveName));

        // 创建文件信息并保存
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUserId(userId);
        fileInfo.setCateId(cateId);
        fileInfo.setFileRealName(file.getOriginalFilename());
        fileInfo.setFileSaveName(saveName);
        fileInfo.setFilePath(userDir.getPath());
        fileInfo.setTimeUpload(new Date());
        fileInfo.setDownCount(0);
        fileInfo.setStatus(1);
        fileInfo.setFileSize(file.getSize());

        FileInfo savedFile = fileRepository.save(fileInfo);

        // 更新用户空间已用空间
        spaceService.updateUsedSpace(userId, file.getSize());

        return savedFile;
    }

    // 获取用户所有文件，按下载次数降序排列
    public List<FileInfo> getUserFiles(Integer userId) {
        return fileRepository.findByUserIdOrderByDownCountDesc(userId);
    }

    // 切换文件状态（启用/禁用）
    public void toggleFileStatus(Integer fileId) {
        FileInfo file = fileRepository.findById(fileId)
            .orElseThrow(() -> new RuntimeException("文件不存在"));
        file.setStatus(file.getStatus() == 1 ? 0 : 1); // 状态切换
        fileRepository.save(file); // 保存更新后的文件信息
    }
}