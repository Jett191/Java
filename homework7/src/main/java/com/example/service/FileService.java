package com.example.service;

import com.example.dao.FileDao; // 导入文件数据访问层，处理文件数据的持久化操作
import com.example.entity.File; // 导入文件实体类，表示文件的属性和信息
import com.example.entity.Space; // 导入空间实体类，表示用户存储空间的数据模型
import org.apache.commons.io.FilenameUtils; // 用于获取文件的扩展名
import org.springframework.beans.factory.annotation.Autowired; // 用于自动注入依赖
import org.springframework.stereotype.Service; // 标记该类为服务层组件
import org.springframework.transaction.annotation.Transactional; // 事务管理
import org.springframework.web.multipart.MultipartFile; // 用于处理上传的文件
import java.io.IOException; // 处理文件IO异常
import java.util.Date; // 用于日期处理
import java.util.List; // 用于返回多个文件数据
import java.util.UUID; // 用于生成唯一标识符

@Service // 声明该类为服务层组件
@Transactional // 确保方法内的数据库操作是事务性
public class FileService {

    @Autowired
    private FileDao fileDao; // 自动注入文件信息数据访问层
    @Autowired
    private SpaceService spaceService; // 自动注入空间服务，用于管理用户存储空间

    private static final String DEFAULT_UPLOAD_PATH = "uploads"; // 默认文件上传目录

    // 保存上传的文件并返回文件信息
    public File saveFile(MultipartFile file, Integer userId, Integer cateId, String uploadDir) throws IOException {
        Space space = spaceService.getSpaceByUserId(userId); // 获取用户的空间信息
        if ((space.getSpaceSize() - space.getUsedSpace()) < file.getSize()) { // 检查用户空间是否足够
            throw new RuntimeException("空间不足"); // 空间不足时抛出异常
        }

        // 生成唯一的文件名，避免重名覆盖
        String saveName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        // 获取系统的工作目录路径
        String basePath = System.getProperty("user.dir");
        // 如果提供了上传目录路径则使用该路径，否则使用默认路径
        String directoryPath = (uploadDir != null && !uploadDir.isEmpty()) ? uploadDir : DEFAULT_UPLOAD_PATH;
        java.io.File userDir = new java.io.File(basePath, directoryPath + "/" + userId);
        if (!userDir.exists()) {
            userDir.mkdirs(); // 如果目标目录不存在则创建
        }

        // 将文件保存到服务器指定目录
        file.transferTo(new java.io.File(userDir, saveName));

        // 创建文件信息对象并设置相关属性
        File fileInfo = new File();
        fileInfo.setUserId(userId); // 设置用户ID
        fileInfo.setCateId(cateId); // 设置文件分类ID
        fileInfo.setFileRealName(file.getOriginalFilename()); // 设置文件原始名称
        fileInfo.setFileSaveName(saveName); // 设置保存后的文件名
        fileInfo.setFilePath(userDir.getPath()); // 设置文件存储路径
        fileInfo.setTimeUpload(new Date()); // 设置上传时间
        fileInfo.setDownCount(0); // 初始下载次数为0
        fileInfo.setStatus(1); // 文件初始状态为启用
        fileInfo.setFileSize(file.getSize()); // 设置文件大小

        // 保存文件信息到数据库
        File savedFile = fileDao.save(fileInfo);

        // 更新用户空间已使用空间
        spaceService.updateUsedSpace(userId, file.getSize());

        return savedFile; // 返回保存后的文件信息
    }

    // 获取用户的所有文件，按下载次数降序排列
    public List<File> getUserFiles(Integer userId) {
        return fileDao.findByUserIdOrderByDownCountDesc(userId); // 根据用户ID获取文件，并按下载次数排序
    }

    // 切换文件的启用/禁用状态
    public void toggleFileStatus(Integer fileId) {
        File file = fileDao.findById(fileId)
            .orElseThrow(() -> new RuntimeException("文件不存在")); // 如果文件不存在，则抛出异常
        // 切换文件状态，1为启用，0为禁用
        file.setStatus(file.getStatus() == 1 ? 0 : 1);
        fileDao.save(file); // 保存更新后的文件状态
    }
}
