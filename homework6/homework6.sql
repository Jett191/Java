/*
 Navicat Premium Dump SQL

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1)
 Source Host           : localhost:3306
 Source Schema         : homework6

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1)
 File Encoding         : 65001

 Date: 24/12/2024 16:57:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论唯一标识',
  `file_id` int NOT NULL COMMENT '被评论的文件ID',
  `user_id` int NOT NULL COMMENT '评论者的用户ID',
  `comment` text NOT NULL COMMENT '评论内容',
  `comment_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件评论表';

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文件唯一标识',
  `user_id` int NOT NULL COMMENT '上传文件的用户ID',
  `filename` varchar(255) NOT NULL COMMENT '文件原始名称',
  `file_type` varchar(50) NOT NULL COMMENT '文件类型（扩展名）',
  `size` bigint NOT NULL COMMENT '文件大小（字节）',
  `upload_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '文件上传时间',
  `path` varchar(500) NOT NULL COMMENT '文件在本地的存储路径',
  `is_frozen` tinyint(1) DEFAULT '0' COMMENT '文件是否被冻结（不可下载）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户上传文件表';

-- ----------------------------
-- Table structure for space
-- ----------------------------
DROP TABLE IF EXISTS `space`;
CREATE TABLE `space` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '空间记录唯一标识',
  `user_id` int NOT NULL COMMENT '对应用户的ID',
  `total_space` bigint NOT NULL DEFAULT '52428800' COMMENT '用户总存储空间（字节），默认50M',
  `used_space` bigint NOT NULL DEFAULT '0' COMMENT '用户已使用的存储空间（字节）',
  `last_updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户存储空间表';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `username` varchar(50) NOT NULL COMMENT '唯一的用户名',
  `password` varchar(100) NOT NULL COMMENT '用户密码（应加密存储）',
  `email` varchar(100) NOT NULL COMMENT '用户唯一邮箱',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

SET FOREIGN_KEY_CHECKS = 1;
