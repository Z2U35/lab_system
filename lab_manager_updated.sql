/*
 Navicat Premium Data Transfer

 Source Server         : User
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : rm-cn-x0r3sr2gp000ez8o.rwlb.rds.aliyuncs.com:3306
 Source Schema         : lab_manager

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 30/06/2024 22:58:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '管理员', 'images/avatar1.jpg', 'ADMIN', '13677889923', 'admin@xm.com');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `lab_id` int NULL DEFAULT NULL COMMENT '实验室id',
  `semester_id` int NULL DEFAULT NULL COMMENT '学期id',
  `week` int NULL DEFAULT NULL COMMENT '周',
  `day` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '星期',
  `session_id` int NULL DEFAULT NULL COMMENT '节次id',
  `student_id` int NULL DEFAULT NULL COMMENT '学生id',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申请原因',
  `apply_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申请日期',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '未审核' COMMENT '申请状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `borrow_lab`(`lab_id` ASC) USING BTREE,
  INDEX `borrow_semester`(`semester_id` ASC) USING BTREE,
  INDEX `borrow_student`(`student_id` ASC) USING BTREE,
  INDEX `borrow_session`(`session_id` ASC) USING BTREE,
  CONSTRAINT `borrow_lab` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_session` FOREIGN KEY (`session_id`) REFERENCES `session` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '实验室借用表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (3, 2, 2, 1, '周一', 2, 4, '做实验1', NULL, '未审核');
INSERT INTO `borrow` VALUES (4, 1, 2, 8, '周一', 1, 4, '做实验3', NULL, '未审核');
INSERT INTO `borrow` VALUES (5, 2, 2, 6, '周二', 2, 4, '做实验', '2024-12-26', '使用完毕');
INSERT INTO `borrow` VALUES (6, 2, 2, 4, '周四', 3, 5, '做实验', '2024-12-26', '通过');
INSERT INTO `borrow` VALUES (7, 2, 2, 4, '周四', 4, 4, 'test', '2024-12-27', '未审核');
INSERT INTO `borrow` VALUES (8, 2, 2, 6, '周二', 6, 4, 'test', '2024-12-28', '未审核');
INSERT INTO `borrow` VALUES (9, 1, 3, 1, '周一', 1, 1, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (10, 2, 4, 2, '周二', 2, 2, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (11, 3, 5, 3, '周三', 3, 3, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (12, 4, 6, 4, '周四', 4, 4, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (13, 5, 7, 5, '周五', 5, 5, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (14, 6, 8, 6, '周一', 6, 6, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (15, 7, 9, 7, '周二', 1, 7, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (16, 8, 10, 8, '周三', 2, 8, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (17, 9, 11, 9, '周四', 3, 9, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (18, 10, 12, 10, '周五', 4, 10, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (19, 11, 13, 11, '周一', 5, 11, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (20, 12, 14, 12, '周二', 6, 12, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (21, 13, 15, 13, '周三', 1, 13, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (22, 14, 16, 14, '周四', 2, 14, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (23, 15, 17, 15, '周五', 3, 15, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (24, 16, 18, 16, '周一', 4, 16, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (25, 17, 19, 1, '周二', 5, 17, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (26, 18, 20, 2, '周三', 6, 18, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (27, 19, 21, 3, '周四', 1, 19, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (28, 20, 22, 4, '周五', 2, 20, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (29, 21, 23, 5, '周一', 3, 21, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (30, 22, 24, 6, '周二', 4, 22, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (31, 23, 25, 7, '周三', 5, 23, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (32, 24, 26, 8, '周一', 6, 24, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (33, 25, 27, 9, '周二', 1, 25, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (34, 26, 28, 10, '周三', 2, 26, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (35, 27, 29, 11, '周四', 3, 27, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (36, 28, 30, 12, '周五', 4, 28, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (37, 29, 31, 13, '周一', 5, 29, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (38, 30, 32, 14, '周二', 6, 30, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (39, 31, 33, 15, '周三', 1, 31, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (40, 32, 34, 16, '周四', 2, 32, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (41, 33, 35, 1, '周五', 3, 33, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (42, 1, 36, 2, '周一', 4, 34, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (43, 2, 37, 3, '周一', 5, 35, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (44, 3, 38, 4, '周二', 6, 36, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (45, 4, 39, 5, '周三', 1, 37, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (46, 5, 40, 6, '周四', 2, 38, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (47, 6, 41, 7, '周五', 3, 39, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (48, 7, 3, 8, '周一', 4, 40, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (49, 8, 4, 9, '周二', 5, 41, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (50, 9, 5, 10, '周三', 6, 42, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (51, 10, 6, 11, '周四', 1, 43, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (52, 11, 7, 12, '周五', 2, 44, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (53, 12, 8, 13, '周一', 3, 45, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (54, 13, 9, 14, '周二', 4, 46, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (55, 14, 10, 15, '周三', 5, 47, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (56, 15, 11, 16, '周二', 6, 48, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (57, 16, 12, 1, '周三', 1, 49, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (58, 17, 13, 2, '周四', 2, 50, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (59, 18, 14, 3, '周五', 3, 51, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (60, 19, 15, 4, '周一', 4, 52, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (61, 20, 16, 5, '周二', 5, 53, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (62, 21, 17, 6, '周三', 6, 54, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (63, 22, 18, 7, '周二', 1, 55, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (64, 23, 19, 8, '周三', 2, 56, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (65, 24, 20, 9, '周四', 3, 57, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (66, 25, 21, 10, '周五', 4, 58, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (67, 26, 22, 11, '周一', 5, 59, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (68, 27, 23, 12, '周二', 6, 60, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (69, 28, 24, 13, '周三', 1, 61, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (70, 29, 25, 14, '周二', 2, 62, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (71, 30, 26, 15, '周三', 3, 63, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (72, 31, 27, 16, '周二', 4, 64, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (73, 32, 28, 1, '周三', 5, 65, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (74, 33, 29, 2, '周四', 6, 66, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (75, 1, 30, 3, '周五', 1, 67, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (76, 2, 31, 4, '周一', 2, 68, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (77, 3, 32, 5, '周二', 3, 69, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (78, 4, 33, 6, '周三', 4, 70, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (79, 5, 34, 7, '周二', 5, 71, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (80, 6, 35, 8, '周三', 6, 72, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (81, 7, 36, 9, '周四', 1, 73, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (82, 8, 37, 10, '周五', 2, 74, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (83, 9, 38, 11, '周一', 3, 75, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (84, 10, 39, 12, '周二', 4, 76, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (85, 11, 40, 13, '周三', 5, 77, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (86, 12, 41, 14, '周二', 6, 78, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (87, 13, 3, 15, '周三', 1, 79, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (88, 14, 4, 16, '周四', 2, 80, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (89, 15, 5, 1, '周五', 3, 81, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (90, 16, 6, 2, '周一', 4, 82, 'test', NULL, '通过');
INSERT INTO `borrow` VALUES (91, 17, 7, 3, '周二', 5, 83, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (92, 18, 8, 4, '周三', 6, 84, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (93, 19, 9, 5, '周二', 1, 85, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (94, 20, 10, 6, '周三', 2, 86, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (95, 21, 11, 7, '周二', 3, 87, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (96, 22, 12, 8, '周三', 4, 88, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (97, 23, 13, 9, '周四', 5, 89, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (98, 24, 14, 10, '周五', 6, 90, 'test', NULL, '未审核');
INSERT INTO `borrow` VALUES (99, 25, 15, 11, '周一', 1, 91, 'test', NULL, '使用完毕');
INSERT INTO `borrow` VALUES (100, 26, 16, 12, '周二', 2, 92, 'test', NULL, '未审核');

-- ----------------------------
-- Table structure for lab
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` int NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  `equipment_count` int NULL DEFAULT NULL COMMENT '设备数',
  `labadmin_id` int NULL DEFAULT NULL COMMENT '实验员id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `lab_manager`(`labadmin_id` ASC) USING BTREE,
  CONSTRAINT `lab_manager` FOREIGN KEY (`labadmin_id`) REFERENCES `lab_admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '实验室表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab
-- ----------------------------
INSERT INTO `lab` VALUES (1, 532, '程序设计实验室', '软件', 70, 2);
INSERT INTO `lab` VALUES (2, 736, '计算机实验室', '硬件', 30, 3);
INSERT INTO `lab` VALUES (3, 732, '程序设计实验室12', '软件', 33, 2);
INSERT INTO `lab` VALUES (4, 502, '程序设计实验室2', '软件', 40, 4);
INSERT INTO `lab` VALUES (5, 504, '程序设计实验室3', '软件', 50, 5);
INSERT INTO `lab` VALUES (6, 506, '程序设计实验室4', '软件', 70, 6);
INSERT INTO `lab` VALUES (7, 508, '程序设计实验室5', '软件', 80, 2);
INSERT INTO `lab` VALUES (8, 510, '程序设计实验室6', '软件', 30, 3);
INSERT INTO `lab` VALUES (9, 512, '程序设计实验室7', '软件', 40, 4);
INSERT INTO `lab` VALUES (10, 514, '程序设计实验室8', '软件', 50, 5);
INSERT INTO `lab` VALUES (11, 516, '程序设计实验室9', '软件', 70, 6);
INSERT INTO `lab` VALUES (12, 518, '程序设计实验室10', '软件', 80, 2);
INSERT INTO `lab` VALUES (13, 520, '程序设计实验室11', '软件', 30, 3);
INSERT INTO `lab` VALUES (14, 702, '计算机实验室2', '硬件', 40, 4);
INSERT INTO `lab` VALUES (15, 704, '计算机实验室3', '硬件', 50, 2);
INSERT INTO `lab` VALUES (16, 706, '计算机实验室4', '硬件', 60, 5);
INSERT INTO `lab` VALUES (17, 708, '计算机实验室5', '硬件', 70, 6);
INSERT INTO `lab` VALUES (18, 710, '计算机实验室6', '硬件', 80, 2);
INSERT INTO `lab` VALUES (19, 712, '计算机实验室7', '硬件', 30, 3);
INSERT INTO `lab` VALUES (20, 714, '计算机实验室8', '硬件', 40, 4);
INSERT INTO `lab` VALUES (21, 716, '计算机实验室9', '硬件', 50, 5);
INSERT INTO `lab` VALUES (22, 718, '计算机实验室10', '硬件', 60, 6);
INSERT INTO `lab` VALUES (23, 720, '计算机实验室11', '硬件', 70, 2);
INSERT INTO `lab` VALUES (24, 802, '计算机网络实验室1', '硬件', 30, 3);
INSERT INTO `lab` VALUES (25, 804, '计算机网络实验室2', '硬件', 40, 4);
INSERT INTO `lab` VALUES (26, 806, '计算机网络实验室3', '硬件', 50, 5);
INSERT INTO `lab` VALUES (27, 808, '计算机网络实验室4', '硬件', 60, 6);
INSERT INTO `lab` VALUES (28, 810, '计算机网络实验室5', '硬件', 70, 2);
INSERT INTO `lab` VALUES (29, 812, '计算机网络实验室6', '硬件', 80, 3);
INSERT INTO `lab` VALUES (30, 814, '计算机网络实验室7', '硬件', 30, 4);
INSERT INTO `lab` VALUES (31, 816, '计算机网络实验室8', '硬件', 40, 5);
INSERT INTO `lab` VALUES (32, 818, '计算机网络实验室9', '硬件', 50, 6);
INSERT INTO `lab` VALUES (33, 820, '计算机网络实验室10', '硬件', 60, 2);

-- ----------------------------
-- Table structure for lab_admin
-- ----------------------------
DROP TABLE IF EXISTS `lab_admin`;
CREATE TABLE `lab_admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '实验员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_admin
-- ----------------------------

INSERT INTO `lab_admin` VALUES (3, '333', '123456', '卢致远', 'images/avatar18.jpg', 'LABADMIN', '184-7713-9481', '1415703033@qq.com', '实验员');
INSERT INTO `lab_admin` VALUES (4, '444', '123456', '龙震南', 'images/avatar12.jpg', 'LABADMIN', '187-2484-6611', '1415703033@qq.com', '实验员');
INSERT INTO `lab_admin` VALUES (5, '555', '123456', '朱致远', 'images/avatar1.jpg', 'LABADMIN', '195-9602-9704', '1415703033@qq.com', '实验员');
INSERT INTO `lab_admin` VALUES (6, '666', '123456', '姜子韬', 'images/avatar4.jpg', 'LABADMIN', '172-2975-1035', '1415703033@qq.com', '实验员');
INSERT INTO `lab_admin` VALUES (7, '777', '123456', '李杰宏', 'images/avatar9.jpg', 'LABADMIN', '760-3059-9855', '1415703033@qq.com', '实验员');
INSERT INTO `lab_admin` VALUES (8, '888', '123456', '林一', 'images/avatar10.jpg', 'LABADMIN', '123', '123@qq.com', '实验员');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `lab_id` int NULL DEFAULT NULL COMMENT '实验室id',
  `equipment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '故障说明',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师id',
  `apply_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '报修日期',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '未维修' COMMENT '报修状态',
  `end_repair` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '报修完情况说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `repair_lab`(`lab_id` ASC) USING BTREE,
  INDEX `repair_teacher`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `repair_lab` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `repair_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '设备报修表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1, 1, '12', '死机', 4, NULL, '已维修', '已处理，设备可以正常使用');
INSERT INTO `repair` VALUES (2, 2, '1', '蓝屏', 4, NULL, '维修中', NULL);
INSERT INTO `repair` VALUES (3, 2, '8', '键盘坏了', 4, NULL, '已维修', '已解决');
INSERT INTO `repair` VALUES (4, 1, '21', '鼠标失灵', 4, '2024-12-27', '维修中', NULL);
INSERT INTO `repair` VALUES (5, 3, '1', '显示屏无显示', 1, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (6, 4, '2', '死机', 2, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (7, 5, '3', '蓝屏', 3, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (8, 6, '4', '白屏', 4, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (9, 7, '5', '鼠标实例', 5, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (10, 8, '6', '键盘坏了', 6, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (11, 9, '7', 'test', 7, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (12, 10, '8', 'test', 8, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (13, 11, '9', 'test', 9, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (14, 12, '10', 'test', 10, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (15, 13, '11', 'test', 11, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (16, 14, '12', 'test', 12, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (17, 15, '13', 'test', 13, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (18, 16, '14', 'test', 14, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (19, 17, '15', 'test', 15, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (20, 18, '16', 'test', 16, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (21, 19, '17', 'test', 17, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (22, 20, '18', 'test', 18, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (23, 21, '19', 'test', 19, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (24, 22, '20', 'test', 20, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (25, 23, '21', 'test', 1, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (26, 24, '1', 'test', 2, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (27, 25, '2', 'test', 3, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (28, 26, '3', 'test', 4, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (29, 27, '4', 'test', 5, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (30, 28, '5', 'test', 6, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (31, 29, '6', 'test', 7, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (32, 30, '7', 'test', 8, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (33, 31, '8', 'test', 9, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (34, 32, '9', 'test', 10, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (35, 33, '10', 'test', 11, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (36, 3, '11', 'test', 12, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (37, 4, '12', 'test', 13, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (38, 5, '13', 'test', 14, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (39, 6, '14', 'test', 15, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (40, 7, '15', 'test', 16, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (41, 8, '16', 'test', 17, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (42, 9, '17', 'test', 18, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (43, 10, '18', 'test', 19, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (44, 11, '19', 'test', 20, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (45, 12, '20', 'test', 1, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (46, 13, '21', 'test', 2, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (47, 14, '1', 'test', 3, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (48, 15, '2', 'test', 4, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (49, 16, '3', 'test', 5, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (50, 17, '4', 'test', 6, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (51, 18, '5', 'test', 7, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (52, 19, '6', 'test', 8, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (53, 20, '7', 'test', 9, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (54, 21, '8', 'test', 10, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (55, 22, '9', 'test', 11, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (56, 23, '10', 'test', 12, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (57, 24, '11', 'test', 13, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (58, 25, '12', 'test', 14, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (59, 26, '13', 'test', 15, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (60, 27, '14', 'test', 16, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (61, 28, '15', 'test', 17, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (62, 29, '16', 'test', 18, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (63, 30, '17', 'test', 19, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (64, 31, '18', 'test', 20, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (65, 32, '19', 'test', 1, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (66, 33, '20', 'test', 2, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (67, 3, '21', 'test', 3, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (68, 4, '1', 'test', 4, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (69, 5, '2', 'test', 5, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (70, 6, '3', 'test', 6, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (71, 7, '4', 'test', 7, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (72, 8, '5', 'test', 8, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (73, 9, '6', 'test', 9, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (74, 10, '7', 'test', 10, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (75, 11, '8', 'test', 11, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (76, 12, '9', 'test', 12, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (77, 13, '10', 'test', 13, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (78, 14, '11', 'test', 14, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (79, 15, '12', 'test', 15, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (80, 16, '13', 'test', 16, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (81, 17, '14', 'test', 17, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (82, 18, '15', 'test', 18, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (83, 19, '16', 'test', 19, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (84, 20, '17', 'test', 20, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (85, 21, '18', 'test', 1, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (86, 22, '19', 'test', 2, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (87, 23, '20', 'test', 3, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (88, 24, '21', 'test', 4, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (89, 25, '1', 'test', 5, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (90, 26, '2', 'test', 6, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (91, 27, '3', 'test', 7, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (92, 28, '4', 'test', 8, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (93, 29, '5', 'test', 9, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (94, 30, '6', 'test', 10, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (95, 31, '7', 'test', 11, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (96, 32, '8', 'test', 12, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (97, 33, '9', 'test', 13, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (98, 1, '10', 'test', 14, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (99, 2, '11', 'test', 15, '2024-12-26', '维修中', NULL);
INSERT INTO `repair` VALUES (100, 3, '12', 'test', 16, '2024-12-26', '维修中', NULL);

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `semester_id` int NULL DEFAULT NULL COMMENT '学期id',
  `lab_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '实验室类型',
  `lab_id` int NULL DEFAULT NULL COMMENT '实验室id',
  `start_week` int NULL DEFAULT NULL COMMENT '开始周',
  `end_week` int NULL DEFAULT NULL COMMENT '结束周',
  `week` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '星期',
  `session_id` int NULL DEFAULT NULL COMMENT '节次id',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师id',
  `clazz` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课班级',
  `number` int NULL DEFAULT NULL COMMENT '上课人数',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '未排课' COMMENT '排课状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `schedule_semester`(`semester_id` ASC) USING BTREE,
  INDEX `schedule_lab`(`lab_id` ASC) USING BTREE,
  INDEX `schedule_teacher`(`teacher_id` ASC) USING BTREE,
  INDEX `schedule_session`(`session_id` ASC) USING BTREE,
  CONSTRAINT `schedule_lab` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `schedule_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `schedule_session` FOREIGN KEY (`session_id`) REFERENCES `session` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `schedule_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '排课表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (1, 2, '软件', 1, 1, 12, '周二', 2, '现代软件开发技术', 4, '21软工R1', 31, '已排课');
INSERT INTO `schedule` VALUES (2, 2, '软件', 1, 1, 12, '周一', 4, 'Linux程序设计', 4, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (5, 2, '网络', 1, 11, 11, '周三', 1, '数据库设计', 4, '21软工R1', 1, '未排课');
INSERT INTO `schedule` VALUES (6, 2, '硬件', 2, 1, 1, '周四', 2, 'python爬虫', 5, '21软工R1', 2, '已排课');
INSERT INTO `schedule` VALUES (7, 3, '硬件', 14, 4, 16, '周一', 1, '计算机系统基础', 1, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (8, 4, '硬件', 23, 4, 16, '周二', 2, '计算机网络', 2, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (9, 5, '硬件', 14, 4, 16, '周三', 3, '数字电路', 3, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (10, 6, '硬件', 15, 4, 16, '周四', 4, '图形化界面学习', 4, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (11, 7, '硬件', 16, 4, 16, '周五', 5, '计算机系统基础', 5, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (12, 8, '硬件', 17, 4, 16, '周一', 6, '计算机网络', 6, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (13, 9, '硬件', 18, 4, 16, '周二', 1, '数字电路', 7, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (14, 10, '硬件', 19, 4, 16, '周三', 2, '图形化界面学习', 8, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (15, 11, '硬件', 20, 4, 16, '周四', 3, '计算机系统基础', 9, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (16, 12, '硬件', 21, 4, 16, '周五', 4, '计算机网络', 10, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (17, 13, '硬件', 22, 4, 16, '周一', 5, '数字电路', 11, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (18, 14, '硬件', 23, 4, 16, '周二', 6, '图形化界面学习', 12, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (19, 15, '硬件', 14, 4, 16, '周三', 1, '计算机系统基础', 13, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (20, 16, '硬件', 15, 4, 16, '周四', 2, '计算机网络', 14, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (21, 17, '硬件', 16, 4, 16, '周五', 3, '数字电路', 15, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (22, 18, '硬件', 17, 4, 16, '周一', 4, '图形化界面学习', 16, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (23, 19, '硬件', 18, 4, 16, '周二', 5, '计算机系统基础', 17, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (24, 20, '硬件', 19, 4, 16, '周三', 6, '计算机网络', 18, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (25, 21, '硬件', 20, 4, 16, '周四', 1, '数字电路', 19, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (26, 22, '硬件', 21, 4, 16, '周五', 2, '图形化界面学习', 20, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (27, 23, '硬件', 22, 4, 16, '周一', 3, '计算机系统基础', 21, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (28, 24, '硬件', 23, 4, 16, '周二', 4, '计算机系统基础', 22, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (29, 25, '硬件', 14, 4, 16, '周三', 5, '计算机网络', 23, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (30, 26, '硬件', 15, 4, 16, '周一', 6, '数字电路', 24, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (31, 27, '硬件', 16, 4, 16, '周二', 1, '图形化界面学习', 25, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (32, 28, '硬件', 17, 4, 16, '周三', 2, '计算机系统基础', 26, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (33, 29, '硬件', 18, 4, 16, '周四', 3, '计算机网络', 27, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (34, 30, '硬件', 19, 4, 16, '周五', 4, '数字电路', 28, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (35, 31, '硬件', 20, 4, 16, '周一', 5, '图形化界面学习', 29, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (36, 32, '硬件', 21, 4, 16, '周二', 6, '计算机系统基础', 30, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (37, 33, '硬件', 22, 4, 16, '周三', 1, '计算机网络', 1, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (38, 34, '硬件', 23, 4, 16, '周四', 2, '数字电路', 2, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (39, 35, '硬件', 14, 4, 16, '周五', 3, '图形化界面学习', 3, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (40, 36, '硬件', 15, 4, 16, '周一', 4, '计算机系统基础', 4, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (41, 37, '硬件', 16, 4, 16, '周二', 5, '计算机网络', 5, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (42, 38, '硬件', 17, 4, 16, '周三', 6, '数字电路', 6, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (43, 39, '硬件', 18, 4, 16, '周四', 1, '计算机系统基础', 7, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (44, 40, '硬件', 19, 4, 16, '周五', 2, '计算机网络', 8, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (45, 41, '硬件', 20, 4, 16, '周一', 3, '数字电路', 9, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (46, 1, '硬件', 21, 4, 16, '周二', 4, '图形化界面学习', 10, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (47, 2, '硬件', 22, 4, 16, '周三', 5, '计算机系统基础', 11, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (48, 3, '硬件', 23, 4, 16, '周四', 6, '计算机网络', 12, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (49, 4, '硬件', 14, 4, 16, '周五', 1, '数字电路', 13, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (50, 5, '硬件', 15, 4, 16, '周一', 2, '图形化界面学习', 14, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (51, 6, '硬件', 16, 4, 16, '周二', 3, '计算机系统基础', 15, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (52, 7, '硬件', 17, 4, 16, '周三', 4, '计算机网络', 16, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (53, 8, '硬件', 18, 4, 16, '周一', 5, '数字电路', 17, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (54, 9, '硬件', 19, 4, 16, '周二', 6, '图形化界面学习', 18, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (55, 10, '硬件', 20, 4, 16, '周三', 1, '计算机系统基础', 19, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (56, 11, '硬件', 21, 4, 16, '周四', 2, '计算机网络', 20, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (57, 12, '硬件', 22, 4, 16, '周五', 3, '数字电路', 21, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (58, 13, '硬件', 23, 4, 16, '周一', 4, '计算机系统基础', 22, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (59, 14, '硬件', 14, 4, 16, '周二', 5, '计算机网络', 23, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (60, 15, '硬件', 15, 4, 16, '周三', 6, '数字电路', 24, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (61, 16, '硬件', 16, 4, 16, '周四', 1, '图形化界面学习', 25, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (62, 17, '硬件', 17, 4, 16, '周五', 2, '计算机系统基础', 26, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (63, 18, '硬件', 18, 4, 16, '周一', 3, '计算机网络', 27, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (64, 19, '硬件', 19, 4, 16, '周二', 4, '数字电路', 28, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (65, 20, '硬件', 20, 4, 16, '周三', 5, '图形化界面学习', 29, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (66, 21, '硬件', 21, 4, 16, '周四', 6, '计算机系统基础', 30, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (67, 22, '硬件', 22, 4, 16, '周五', 1, '计算机网络', 1, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (68, 23, '硬件', 23, 4, 16, '周一', 2, '数字电路', 2, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (69, 24, '硬件', 14, 4, 16, '周二', 3, '图形化界面学习', 3, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (70, 25, '硬件', 15, 4, 16, '周三', 4, '计算机系统基础', 4, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (71, 26, '硬件', 16, 4, 16, '周四', 5, '计算机网络', 5, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (72, 27, '硬件', 17, 4, 16, '周五', 6, '数字电路', 6, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (73, 28, '硬件', 18, 4, 16, '周一', 1, '图形化界面学习', 7, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (74, 29, '硬件', 19, 4, 16, '周二', 2, '计算机系统基础', 8, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (75, 30, '硬件', 20, 4, 16, '周三', 3, '计算机网络', 9, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (76, 31, '硬件', 21, 4, 16, '周一', 4, '数字电路', 10, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (77, 32, '硬件', 22, 4, 16, '周二', 5, '图形化界面学习', 11, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (78, 33, '硬件', 23, 4, 16, '周三', 6, '计算机系统基础', 12, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (79, 34, '硬件', 14, 4, 16, '周四', 1, '计算机系统基础', 13, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (80, 35, '硬件', 15, 4, 16, '周五', 2, '计算机网络', 14, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (81, 36, '硬件', 16, 4, 16, '周一', 3, '数字电路', 15, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (82, 37, '硬件', 17, 4, 16, '周二', 4, '图形化界面学习', 16, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (83, 38, '硬件', 18, 4, 16, '周三', 5, '计算机系统基础', 17, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (84, 39, '硬件', 19, 4, 16, '周四', 6, '计算机网络', 18, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (85, 40, '硬件', 20, 4, 16, '周五', 1, '数字电路', 19, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (86, 41, '硬件', 21, 4, 16, '周一', 2, '计算机系统基础', 20, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (87, 1, '硬件', 22, 4, 16, '周二', 3, '计算机网络', 21, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (88, 2, '硬件', 23, 4, 16, '周三', 4, '数字电路', 22, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (89, 3, '硬件', 14, 4, 16, '周四', 5, '图形化界面学习', 23, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (90, 4, '硬件', 15, 4, 16, '周五', 6, '计算机系统基础', 24, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (91, 5, '硬件', 16, 4, 16, '周一', 1, '计算机网络', 25, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (92, 6, '硬件', 17, 4, 16, '周二', 2, '数字电路', 26, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (93, 7, '硬件', 18, 4, 16, '周三', 3, '图形化界面学习', 27, '21软工R7', 31, '未排课');
INSERT INTO `schedule` VALUES (94, 8, '硬件', 19, 4, 16, '周四', 4, '计算机系统基础', 28, '21软工R8', 31, '未排课');
INSERT INTO `schedule` VALUES (95, 9, '硬件', 20, 4, 16, '周五', 5, '计算机网络', 29, '21软工R1', 31, '未排课');
INSERT INTO `schedule` VALUES (96, 10, '硬件', 21, 4, 16, '周一', 6, '数字电路', 30, '21软工R2', 31, '未排课');
INSERT INTO `schedule` VALUES (97, 11, '硬件', 22, 4, 16, '周二', 1, '图形化界面学习', 1, '21软工R3', 31, '未排课');
INSERT INTO `schedule` VALUES (98, 12, '硬件', 23, 4, 16, '周三', 2, '计算机系统基础', 2, '21软工R4', 31, '未排课');
INSERT INTO `schedule` VALUES (99, 13, '硬件', 14, 4, 16, '周一', 3, '计算机网络', 3, '21软工R5', 31, '未排课');
INSERT INTO `schedule` VALUES (100, 14, '硬件', 15, 4, 16, '周二', 4, '数字电路', 4, '21软工R6', 31, '未排课');
INSERT INTO `schedule` VALUES (101, 15, '硬件', 31, 4, 16, '周三', 5, '图形化界面学习', 5, '21软工R7', 31, '已排课');
INSERT INTO `schedule` VALUES (102, 16, '硬件', 31, 4, 16, '周四', 6, '计算机系统基础', 6, '21软工R8', 31, '已排课');
INSERT INTO `schedule` VALUES (103, 17, '硬件', 32, 4, 16, '周五', 1, '计算机网络', 7, '21软工R1', 31, '已排课');
INSERT INTO `schedule` VALUES (104, 18, '硬件', 31, 4, 16, '周一', 2, '数字电路', 8, '21软工R2', 31, '已排课');
INSERT INTO `schedule` VALUES (105, 19, '硬件', 31, 4, 16, '周二', 3, '图形化界面学习', 9, '21软工R3', 31, '已排课');
INSERT INTO `schedule` VALUES (106, 20, '硬件', 31, 4, 16, '周三', 4, '计算机系统基础', 10, '21软工R4', 31, '已排课');
INSERT INTO `schedule` VALUES (107, 21, '硬件', 15, 4, 16, '周四', 5, '计算机系统基础', 11, '21软工R5', 31, '已排课');
INSERT INTO `schedule` VALUES (108, 22, '硬件', 17, 4, 16, '周五', 6, '计算机网络', 12, '21软工R6', 31, '已排课');
INSERT INTO `schedule` VALUES (109, 23, '硬件', 16, 4, 16, '周一', 1, '数字电路', 13, '21软工R7', 31, '已排课');
INSERT INTO `schedule` VALUES (110, 24, '硬件', 14, 4, 16, '周二', 2, '图形化界面学习', 14, '21软工R8', 31, '已排课');

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学期',
  `weeks` int NULL DEFAULT NULL COMMENT '周数',
  `is_cur_semester` int NULL DEFAULT 0 COMMENT '是否为当前学期 0否 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学期表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of semester
-- ----------------------------
INSERT INTO `semester` VALUES (1, '2023-2024-1', 18, 0);
INSERT INTO `semester` VALUES (2, '2023-2024-2', 12, 1);
INSERT INTO `semester` VALUES (4, '2024-2025-1', 8, 0);
INSERT INTO `semester` VALUES (5, '2024-2025-2', 20, 0);
INSERT INTO `semester` VALUES (6, '2010-2011-1', 16, 0);
INSERT INTO `semester` VALUES (7, '2010-2011-2', 20, 0);
INSERT INTO `semester` VALUES (8, '2011-2012-1', 16, 0);
INSERT INTO `semester` VALUES (9, '2011-2012-2', 20, 0);
INSERT INTO `semester` VALUES (10, '2012-2013-1', 16, 0);
INSERT INTO `semester` VALUES (11, '2012-2013-2', 20, 0);
INSERT INTO `semester` VALUES (12, '2013-2014-1', 16, 0);
INSERT INTO `semester` VALUES (13, '2013-2014-2', 20, 0);
INSERT INTO `semester` VALUES (14, '2014-2015-1', 16, 0);
INSERT INTO `semester` VALUES (15, '2014-2015-2', 20, 0);
INSERT INTO `semester` VALUES (16, '2015-2016-1', 16, 0);
INSERT INTO `semester` VALUES (17, '2015-2016-2', 20, 0);
INSERT INTO `semester` VALUES (18, '2016-2017-1', 16, 0);
INSERT INTO `semester` VALUES (19, '2016-2017-2', 20, 0);
INSERT INTO `semester` VALUES (20, '2017-2018-1', 16, 0);
INSERT INTO `semester` VALUES (21, '2017-2018-2', 20, 0);
INSERT INTO `semester` VALUES (22, '2018-2019-1', 16, 0);
INSERT INTO `semester` VALUES (23, '2018-2019-2', 20, 0);
INSERT INTO `semester` VALUES (24, '2019-2020-1', 16, 0);
INSERT INTO `semester` VALUES (25, '2019-2020-2', 20, 0);
INSERT INTO `semester` VALUES (26, '2020-2021-1', 16, 0);
INSERT INTO `semester` VALUES (27, '2020-2021-2', 20, 0);
INSERT INTO `semester` VALUES (28, '2021-2022-1', 16, 0);
INSERT INTO `semester` VALUES (29, '2021-2022-2', 20, 0);
INSERT INTO `semester` VALUES (30, '2022-2023-1', 16, 0);
INSERT INTO `semester` VALUES (31, '2022-2023-2', 20, 0);
INSERT INTO `semester` VALUES (32, '2025-2026-1', 16, 0);
INSERT INTO `semester` VALUES (33, '2025-2026-2', 20, 0);
INSERT INTO `semester` VALUES (34, '2026-2027-1', 16, 0);
INSERT INTO `semester` VALUES (35, '2026-2027-2', 20, 0);
INSERT INTO `semester` VALUES (36, '2027-2028-1', 16, 0);
INSERT INTO `semester` VALUES (37, '2027-2028-2', 20, 0);
INSERT INTO `semester` VALUES (38, '2028-2029-1', 16, 0);
INSERT INTO `semester` VALUES (39, '2028-2029-2', 20, 0);
INSERT INTO `semester` VALUES (40, '2029-2030-1', 16, 0);
INSERT INTO `semester` VALUES (41, '2029-2030-2', 20, 0);

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `id` int NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '节次',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '节次表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES (1, '1-2');
INSERT INTO `session` VALUES (2, '3-5');
INSERT INTO `session` VALUES (3, '6-7');
INSERT INTO `session` VALUES (4, '8-9');
INSERT INTO `session` VALUES (5, '10-12');
INSERT INTO `session` VALUES (6, '13-15');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `speciality` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专业',
  `clazz` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '123', '123', '莫嘉伦', 'images/avatar6.jpg', 'STUDENT', '20-650-5826', 'moj5@gmail.com', '计算机科学与技术', '2班');
INSERT INTO `student` VALUES (2, 'Han Lan', '1985723459', '韩岚', 'images/avatar13.jpg', 'STUDENT', '20-0982-0335', 'hanlan@qq.com', '数据科学与大数据技术', '1班');
INSERT INTO `student` VALUES (3, 'Qian Zhennan', '5188194190', '钱震南', 'images/avatar8.jpg', 'STUDENT', '164-7711-8125', 'zqian4@gmail.com', '计算机科学与技术', '7班');
INSERT INTO `student` VALUES (4, 'Jia Xiuying', '3758809120', '贾秀英', 'images/avatar11.jpg', 'STUDENT', '20-996-9486', 'jiax@gmail.com', '软件工程', '8班');
INSERT INTO `student` VALUES (5, 'Sun Lu', '9538212944', '孙璐', 'images/avatar16.jpg', 'STUDENT', '21-8624-3975', 'lusu@hotmail.com', '网络工程', '6班');
INSERT INTO `student` VALUES (6, 'He Shihan', '5496036849', '贺詩涵', 'images/avatar5.jpg', 'STUDENT', '173-6275-9510', 'heshihan@qq.com', '软件工程', '7班');
INSERT INTO `student` VALUES (7, 'Cui Yunxi', '2829031931', '崔云熙', 'images/avatar1.jpg', 'STUDENT', '133-4950-5538', 'cui3@hotmail.com', '软件工程', '7班');
INSERT INTO `student` VALUES (8, 'Qin Ziyi', '4633544748', '秦子异', 'images/avatar11.jpg', 'STUDENT', '184-7713-9481', 'qinz@qq.com', '数据科学与大数据技术', '7班');
INSERT INTO `student` VALUES (9, 'Peng Lu', '5489099926', '彭璐', 'images/avatar4.jpg', 'STUDENT', '187-2484-6611', 'pelu5@gmail.com', '数据科学与大数据技术', '8班');
INSERT INTO `student` VALUES (10, 'Tao Jialun', '5031666675', '陶嘉伦', 'images/avatar2.jpg', 'STUDENT', '195-9602-9704', 'jialun52@hotmail.com', '网络工程', '3班');
INSERT INTO `student` VALUES (11, 'Qiu Anqi', '3083486499', '邱安琪', NULL, 'STUDENT', '172-2975-1035', 'anqiqiu@gmail.com', '网络工程', '4班');
INSERT INTO `student` VALUES (12, 'Ma Zhennan', '2899814477', '马震南', NULL, 'STUDENT', '760-3059-9855', 'mazhen825@hotmail.com', '计算机科学与技术', '6班');
INSERT INTO `student` VALUES (13, 'Jiang Xiaoming', '6104252681', '蒋晓明', NULL, 'STUDENT', '144-7198-5109', 'xjian8@hotmail.com', '网络工程', '8班');
INSERT INTO `student` VALUES (14, 'Xue Lu', '5821816344', '薛璐', NULL, 'STUDENT', '177-6036-0089', 'lux823@qq.com', '网络工程', '1班');
INSERT INTO `student` VALUES (15, 'Lu Zhiyuan', '4377787746', '卢致远', NULL, 'STUDENT', '191-0773-6491', 'luzh1116@gmail.com', '数据科学与大数据技术', '2班');
INSERT INTO `student` VALUES (16, 'Long Zhennan', '2522359645', '龙震南', NULL, 'STUDENT', '136-8987-7684', 'longzhe@gmail.com', '软件工程', '7班');
INSERT INTO `student` VALUES (17, 'Zhu Zhiyuan', '1800670834', '朱致远', NULL, 'STUDENT', '21-0282-0814', 'zhu95@hotmail.com', '数据科学与大数据技术', '6班');
INSERT INTO `student` VALUES (18, 'Jiang Zitao', '2482008727', '姜子韬', NULL, 'STUDENT', '178-9319-5462', 'zj1989@gmail.com', '软件工程', '1班');
INSERT INTO `student` VALUES (19, 'Li Jiehong', '9242401340', '李杰宏', NULL, 'STUDENT', '28-204-4871', 'li801@gmail.com', '数据科学与大数据技术', '2班');
INSERT INTO `student` VALUES (20, 'Kong Jialun', '8101323423', '孔嘉伦', NULL, 'STUDENT', '10-9294-7857', 'jialunk@gmail.com', '计算机科学与技术', '1班');
INSERT INTO `student` VALUES (21, 'Hao Ziyi', '0470167822', '郝子异', NULL, 'STUDENT', '173-3815-0840', 'haziyi07@hotmail.com', '计算机科学与技术', '5班');
INSERT INTO `student` VALUES (22, 'Du Xiuying', '8678859021', '杜秀英', NULL, 'STUDENT', '198-5608-8977', 'duxiu1227@hotmail.com', '计算机科学与技术', '1班');
INSERT INTO `student` VALUES (23, 'Jiang Zitao', '7564537501', '姜子韬', NULL, 'STUDENT', '146-7229-4756', 'jizita@hotmail.com', '软件工程', '2班');
INSERT INTO `student` VALUES (24, 'Tan Yuning', '7834876783', '谭宇宁', NULL, 'STUDENT', '755-385-6614', 'ytan@hotmail.com', '网络工程', '3班');
INSERT INTO `student` VALUES (25, 'Yan Zitao', '6647491678', '阎子韬', NULL, 'STUDENT', '21-2446-5786', 'zitaoyan@gmail.com', '网络工程', '7班');
INSERT INTO `student` VALUES (26, 'Feng Anqi', '7554369856', '冯安琪', NULL, 'STUDENT', '133-3339-8084', 'fengan@gmail.com', '软件工程', '1班');
INSERT INTO `student` VALUES (27, 'Qin Jialun', '5062052981', '秦嘉伦', NULL, 'STUDENT', '21-5376-7144', 'qinji@gmail.com', '数据科学与大数据技术', '2班');
INSERT INTO `student` VALUES (28, 'Shao Anqi', '9893566848', '邵安琪', NULL, 'STUDENT', '760-803-4054', 'shao43@gmail.com', '计算机科学与技术', '6班');
INSERT INTO `student` VALUES (29, 'Ma Xiaoming', '9605909810', '马晓明', NULL, 'STUDENT', '760-0584-0602', 'ma96@gmail.com', '软件工程', '3班');
INSERT INTO `student` VALUES (30, 'Yan Xiuying', '0396844204', '严秀英', NULL, 'STUDENT', '21-4953-4337', 'yanxiu425@gmail.com', '数据科学与大数据技术', '1班');
INSERT INTO `student` VALUES (31, 'Ding Zitao', '6250001130', '丁子韬', NULL, 'STUDENT', '760-5020-9869', 'zitao58@gmail.com', '网络工程', '4班');
INSERT INTO `student` VALUES (32, 'Gao Zhennan', '2465133729', '高震南', NULL, 'STUDENT', '155-9511-0272', 'zhennangao1@gmail.com', '软件工程', '5班');
INSERT INTO `student` VALUES (33, 'Li Zitao', '8617779047', '黎子韬', NULL, 'STUDENT', '145-7435-5575', 'zl3@gmail.com', '软件工程', '5班');
INSERT INTO `student` VALUES (34, 'Yu Lu', '4099540689', '于璐', NULL, 'STUDENT', '20-995-3705', 'luyu06@gmail.com', '软件工程', '3班');
INSERT INTO `student` VALUES (35, 'Wu Ziyi', '0793894001', '武子异', NULL, 'STUDENT', '20-0009-6787', 'wuzi@qq.com', '数据科学与大数据技术', '4班');
INSERT INTO `student` VALUES (36, 'Tang Zhennan', '4087717412', '汤震南', NULL, 'STUDENT', '181-4626-0511', 'zhennantang@gmail.com', '数据科学与大数据技术', '2班');
INSERT INTO `student` VALUES (37, 'Shi Zhiyuan', '6574676241', '石致远', NULL, 'STUDENT', '186-9650-4865', 'shiz@gmail.com', '软件工程', '7班');
INSERT INTO `student` VALUES (38, 'Lu Zhiyuan', '2083021581', '吕致远', NULL, 'STUDENT', '28-7348-7346', 'zhiyuanlu@qq.com', '网络工程', '7班');
INSERT INTO `student` VALUES (39, 'Tian Ziyi', '8965889166', '田子异', NULL, 'STUDENT', '189-0704-9749', 'tiazi@gmail.com', '网络工程', '4班');
INSERT INTO `student` VALUES (40, 'Wu Jiehong', '6030258851', '吴杰宏', NULL, 'STUDENT', '176-7836-3216', 'wuj@qq.com', '软件工程', '6班');
INSERT INTO `student` VALUES (41, 'Li Yuning', '6378003812', '李宇宁', NULL, 'STUDENT', '21-0889-4816', 'yuningli@gmail.com', '计算机科学与技术', '8班');
INSERT INTO `student` VALUES (42, 'Cao Xiaoming', '7009899643', '曹晓明', NULL, 'STUDENT', '168-1616-2755', 'cxiaom@hotmail.com', '网络工程', '2班');
INSERT INTO `student` VALUES (43, 'Xie Jiehong', '3737847467', '谢杰宏', NULL, 'STUDENT', '155-4041-6396', 'xiejieh213@qq.com', '网络工程', '4班');
INSERT INTO `student` VALUES (44, 'Xiao Zhennan', '6279042844', '萧震南', NULL, 'STUDENT', '28-1403-9927', 'xizhen@gmail.com', '数据科学与大数据技术', '6班');
INSERT INTO `student` VALUES (45, 'Fang Shihan', '5980744334', '方詩涵', NULL, 'STUDENT', '168-7013-9723', 'fangsh@hotmail.com', '软件工程', '7班');
INSERT INTO `student` VALUES (46, 'Du Zhennan', '3467008983', '杜震南', NULL, 'STUDENT', '171-1967-2795', 'duzhennan@gmail.com', '软件工程', '3班');
INSERT INTO `student` VALUES (47, 'Tang Jiehong', '6680660585', '唐杰宏', NULL, 'STUDENT', '769-6763-4178', 'tangjiehong@gmail.com', '软件工程', '8班');
INSERT INTO `student` VALUES (48, 'Yuan Zhiyuan', '0736072912', '袁致远', NULL, 'STUDENT', '178-1680-9448', 'yuanz904@gmail.com', '软件工程', '3班');
INSERT INTO `student` VALUES (49, 'Yu Xiaoming', '3037022818', '余晓明', NULL, 'STUDENT', '10-185-7381', 'yxiaom@gmail.com', '计算机科学与技术', '3班');
INSERT INTO `student` VALUES (50, 'Xiong Ziyi', '6670827659', '熊子异', NULL, 'STUDENT', '196-9236-7079', 'ziyixion@gmail.com', '网络工程', '5班');
INSERT INTO `student` VALUES (51, 'Xia Xiuying', '5676570350', '夏秀英', NULL, 'STUDENT', '769-897-9923', 'xiuying1996@gmail.com', '网络工程', '1班');
INSERT INTO `student` VALUES (52, 'Jiang Jialun', '9982754777', '江嘉伦', NULL, 'STUDENT', '10-2382-7789', 'jialunjiang@gmail.com', '软件工程', '2班');
INSERT INTO `student` VALUES (53, 'Fan Lan', '6837353437', '范岚', NULL, 'STUDENT', '145-5231-4918', 'fan71@qq.com', '计算机科学与技术', '6班');
INSERT INTO `student` VALUES (54, 'Lu Shihan', '6533968369', '吕詩涵', NULL, 'STUDENT', '760-548-5273', 'lush@gmail.com', '计算机科学与技术', '4班');
INSERT INTO `student` VALUES (55, 'Shi Rui', '5677540860', '石睿', NULL, 'STUDENT', '176-8666-8568', 'shi201@gmail.com', '数据科学与大数据技术', '4班');
INSERT INTO `student` VALUES (56, 'Wang Yunxi', '7525954241', '王云熙', NULL, 'STUDENT', '755-475-8165', 'yunxi228@gmail.com', '计算机科学与技术', '6班');
INSERT INTO `student` VALUES (57, 'Yu Zhennan', '0689080993', '余震南', NULL, 'STUDENT', '755-0850-6831', 'zyu10@gmail.com', '网络工程', '8班');
INSERT INTO `student` VALUES (58, 'Mo Lan', '8371205901', '莫岚', NULL, 'STUDENT', '198-8971-4011', 'lanm1953@qq.com', '数据科学与大数据技术', '4班');
INSERT INTO `student` VALUES (59, 'Hou Zhiyuan', '1256818584', '侯致远', NULL, 'STUDENT', '187-7915-4895', 'zhhou@gmail.com', '计算机科学与技术', '2班');
INSERT INTO `student` VALUES (60, 'Xiang Yunxi', '3255699401', '向云熙', NULL, 'STUDENT', '193-3670-3280', 'yunxixia@gmail.com', '软件工程', '3班');
INSERT INTO `student` VALUES (61, 'Gong Yunxi', '2683087342', '龚云熙', NULL, 'STUDENT', '755-592-5896', 'ygo928@gmail.com', '网络工程', '6班');
INSERT INTO `student` VALUES (62, 'Wu Zhiyuan', '7000702443', '武致远', NULL, 'STUDENT', '20-072-9998', 'wu327@qq.com', '计算机科学与技术', '1班');
INSERT INTO `student` VALUES (63, 'Lu Rui', '5767758324', '陆睿', NULL, 'STUDENT', '28-9401-2223', 'ruilu52@gmail.com', '数据科学与大数据技术', '3班');
INSERT INTO `student` VALUES (64, 'Tao Zitao', '1901888126', '陶子韬', NULL, 'STUDENT', '151-8295-8352', 'zitao@gmail.com', '软件工程', '2班');
INSERT INTO `student` VALUES (65, 'Pan Zitao', '0861369577', '潘子韬', NULL, 'STUDENT', '146-0050-8557', 'pazita@gmail.com', '计算机科学与技术', '6班');
INSERT INTO `student` VALUES (66, 'Lei Xiaoming', '4330062517', '雷晓明', NULL, 'STUDENT', '138-7728-2422', 'xlei6@hotmail.com', '计算机科学与技术', '4班');
INSERT INTO `student` VALUES (67, 'Liao Xiaoming', '8737623272', '廖晓明', NULL, 'STUDENT', '141-3084-8851', 'xiaoming9@hotmail.com', '网络工程', '8班');
INSERT INTO `student` VALUES (68, 'Zhu Jialun', '9637914053', '朱嘉伦', NULL, 'STUDENT', '10-366-2153', 'jialunz@gmail.com', '软件工程', '1班');
INSERT INTO `student` VALUES (69, 'Mo Jiehong', '6350677293', '莫杰宏', NULL, 'STUDENT', '173-3701-2063', 'mojiehong@hotmail.com', '计算机科学与技术', '2班');
INSERT INTO `student` VALUES (70, 'Yuan Yuning', '2729382856', '袁宇宁', NULL, 'STUDENT', '151-8027-5433', 'yuanyu@gmail.com', '软件工程', '6班');
INSERT INTO `student` VALUES (71, 'Xie Ziyi', '0831044748', '谢子异', NULL, 'STUDENT', '170-9589-1821', 'zxie@gmail.com', '数据科学与大数据技术', '3班');
INSERT INTO `student` VALUES (72, 'Wang Anqi', '2226193055', '汪安琪', NULL, 'STUDENT', '20-7368-6324', 'anqiwang@qq.com', '数据科学与大数据技术', '1班');
INSERT INTO `student` VALUES (73, 'Liu Zhennan', '0772449569', '刘震南', NULL, 'STUDENT', '28-9801-6107', 'zhenl@gmail.com', '数据科学与大数据技术', '6班');
INSERT INTO `student` VALUES (74, 'Zhao Zitao', '8947637102', '赵子韬', NULL, 'STUDENT', '178-5775-4126', 'zhaozitao@gmail.com', '计算机科学与技术', '4班');
INSERT INTO `student` VALUES (75, 'Zhao Jialun', '6714482962', '赵嘉伦', NULL, 'STUDENT', '183-0234-0577', 'zhao45@gmail.com', '数据科学与大数据技术', '2班');
INSERT INTO `student` VALUES (76, 'Duan Anqi', '4015388968', '段安琪', NULL, 'STUDENT', '180-3890-6710', 'and@hotmail.com', '网络工程', '6班');
INSERT INTO `student` VALUES (77, 'Jiang Anqi', '1929799758', '蒋安琪', NULL, 'STUDENT', '196-2848-6089', 'jianganqi@gmail.com', '数据科学与大数据技术', '4班');
INSERT INTO `student` VALUES (78, 'Zeng Jialun', '6188811153', '曾嘉伦', NULL, 'STUDENT', '148-0745-0619', 'jialun1@gmail.com', '网络工程', '2班');
INSERT INTO `student` VALUES (79, 'Xiong Jiehong', '2872045637', '熊杰宏', NULL, 'STUDENT', '10-2056-5814', 'xjiehong@gmail.com', '计算机科学与技术', '7班');
INSERT INTO `student` VALUES (80, 'Mao Yunxi', '6845268598', '毛云熙', NULL, 'STUDENT', '146-0421-1251', 'maoyun1@gmail.com', '数据科学与大数据技术', '3班');
INSERT INTO `student` VALUES (81, 'Shao Jialun', '7944506634', '邵嘉伦', NULL, 'STUDENT', '20-5722-7679', 'jialshao@hotmail.com', '数据科学与大数据技术', '2班');
INSERT INTO `student` VALUES (82, 'Cai Rui', '9149089234', '蔡睿', NULL, 'STUDENT', '147-5310-4521', 'cai7@hotmail.com', '计算机科学与技术', '7班');
INSERT INTO `student` VALUES (83, 'Fu Shihan', '6420686211', '傅詩涵', NULL, 'STUDENT', '760-3183-4403', 'shihanfu@hotmail.com', '网络工程', '3班');
INSERT INTO `student` VALUES (84, 'Xu Yunxi', '0198428466', '徐云熙', NULL, 'STUDENT', '20-7796-2500', 'xyun@gmail.com', '计算机科学与技术', '3班');
INSERT INTO `student` VALUES (85, 'Li Lu', '6068043350', '李璐', NULL, 'STUDENT', '10-1509-6417', 'luli@hotmail.com', '软件工程', '1班');
INSERT INTO `student` VALUES (86, 'Jiang Yuning', '1124874095', '江宇宁', NULL, 'STUDENT', '133-9036-4219', 'jiangy@gmail.com', '软件工程', '3班');
INSERT INTO `student` VALUES (87, 'Yu Ziyi', '7359020393', '于子异', NULL, 'STUDENT', '145-1935-7735', 'yuziy3@gmail.com', '计算机科学与技术', '6班');
INSERT INTO `student` VALUES (88, 'Xiang Yuning', '4152446256', '向宇宁', NULL, 'STUDENT', '193-3251-4388', 'yxian4@gmail.com', '软件工程', '8班');
INSERT INTO `student` VALUES (89, 'Cao Ziyi', '8192472908', '曹子异', NULL, 'STUDENT', '192-0120-5860', 'ziyi4@hotmail.com', '网络工程', '4班');
INSERT INTO `student` VALUES (90, 'Lu Zhennan', '3125770049', '吕震南', NULL, 'STUDENT', '10-8872-1442', 'zhennanlu1203@gmail.com', '网络工程', '2班');
INSERT INTO `student` VALUES (91, 'Qian Anqi', '3851745472', '钱安琪', NULL, 'STUDENT', '153-8357-6884', 'qiaa@hotmail.com', '网络工程', '2班');
INSERT INTO `student` VALUES (92, 'Jiang Rui', '5461996769', '蒋睿', NULL, 'STUDENT', '755-128-0263', 'ruj06@gmail.com', '软件工程', '5班');
INSERT INTO `student` VALUES (93, 'Zou Ziyi', '5389446423', '邹子异', NULL, 'STUDENT', '28-1735-4293', 'zouz14@gmail.com', '网络工程', '8班');
INSERT INTO `student` VALUES (94, 'Lin Zitao', '7733778357', '林子韬', NULL, 'STUDENT', '28-313-3675', 'zitao304@gmail.com', '数据科学与大数据技术', '1班');
INSERT INTO `student` VALUES (95, 'Song Rui', '4535091946', '宋睿', NULL, 'STUDENT', '150-0166-1887', 'rsong@hotmail.com', '软件工程', '8班');
INSERT INTO `student` VALUES (96, 'Duan Zhiyuan', '0680595989', '段致远', NULL, 'STUDENT', '181-5847-9244', 'zhiyduan3@qq.com', '软件工程', '3班');
INSERT INTO `student` VALUES (97, 'Gao Zhiyuan', '9145450830', '高致远', NULL, 'STUDENT', '755-4379-6410', 'gaozh@gmail.com', '网络工程', '7班');
INSERT INTO `student` VALUES (98, 'Kong Yunxi', '3876903745', '孔云熙', NULL, 'STUDENT', '755-149-0160', 'kongyunxi@gmail.com', '软件工程', '4班');
INSERT INTO `student` VALUES (99, 'Sun Jiehong', '0955151849', '孙杰宏', NULL, 'STUDENT', '28-3458-6896', 'jiehongsun@gmail.com', '软件工程', '4班');
INSERT INTO `student` VALUES (100, 'Wu Zhiyuan', '6078093964', '武致远', NULL, 'STUDENT', '769-8493-8701', 'zhiyuw@hotmail.com', '数据科学与大数据技术', '2班');
INSERT INTO `student` VALUES (101, 'test', '123456', '111', NULL, 'STUDENT', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '教师表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (4, '123', '123456', '丁詩涵', 'images/avatar15.jpg', 'TEACHER', '183-8425-9552', 'ding1999@gmail.com', '副教授');
INSERT INTO `teacher` VALUES (5, 'Tao Zhennan', '123456', '陶震南', 'images/avatar6.jpg', 'TEACHER', '755-382-9912', 'zhennant@qq.com', '教授');
INSERT INTO `teacher` VALUES (6, 'Yan Ziyi', '123456', '严子异', 'images/avatar9.jpg', 'TEACHER', '160-3901-2258', 'yan612@mail.com', '讲师');
INSERT INTO `teacher` VALUES (7, 'Huang Jialun', '123456', '黄嘉伦', 'images/avatar17.jpg', 'TEACHER', '155-5116-2606', 'jialunhua4@gmail.com', '教授');
INSERT INTO `teacher` VALUES (8, 'Fang Rui', '123456', '方睿', 'images/avatar9.jpg', 'TEACHER', '28-5639-0720', 'fangrui@mail.com', '副教授');
INSERT INTO `teacher` VALUES (9, 'Xiang Shihan', '123456', '向詩涵', 'images/avatar4.jpg', 'TEACHER', '182-7207-3219', 'xshi@gmail.com', '讲师');
INSERT INTO `teacher` VALUES (10, 'Gong Anqi', '123456', '龚安琪', 'images/avatar12.jpg', 'TEACHER', '153-1496-4953', 'anqigong10@mail.com', '教授');
INSERT INTO `teacher` VALUES (11, 'Fu Rui', '123456', '傅睿', 'images/avatar6.jpg', 'TEACHER', '178-3193-9982', 'frui@gmail.com', '讲师');
INSERT INTO `teacher` VALUES (12, 'Feng Yunxi', '123456', '冯云熙', 'images/avatar6.jpg', 'TEACHER', '755-857-7240', 'yunxi3@mail.com', '教授');
INSERT INTO `teacher` VALUES (13, 'Peng Shihan', '123456', '彭詩涵', 'images/avatar3.jpg', 'TEACHER', '164-1668-1405', 'shihapeng@gmail.com', '副教授');
INSERT INTO `teacher` VALUES (14, 'Duan Ziyi', '123456', '段子异', 'images/avatar9.jpg', 'TEACHER', '755-8295-6779', 'zduan5@qq.com', '讲师');
INSERT INTO `teacher` VALUES (15, 'Hao Yunxi', '123456', '郝云熙', 'images/avatar13.jpg', 'TEACHER', '20-1173-6654', 'haoyunxi10@qq.com', '教授');
INSERT INTO `teacher` VALUES (16, 'Shi Yuning', '123456', '石宇宁', 'images/avatar2.jpg', 'TEACHER', '146-1256-7026', 'yuningsh@qq.com', '讲师');
INSERT INTO `teacher` VALUES (17, 'Tang Lu', '123456', '唐璐', 'images/avatar12.jpg', 'TEACHER', '175-9474-8195', 'lu129@gmail.com', '教授');
INSERT INTO `teacher` VALUES (18, 'Zhang Zitao', '123456', '张子韬', 'images/avatar18.jpg', 'TEACHER', '173-1965-1779', 'zhz@gmail.com', '副教授');
INSERT INTO `teacher` VALUES (19, 'Jiang Zhiyuan', '123456', '江致远', 'images/avatar5.jpg', 'TEACHER', '178-9884-5412', 'jiangzhi1@gmail.com', '讲师');
INSERT INTO `teacher` VALUES (20, 'Hao Yunxi', '123456', '郝云熙', 'images/avatar8.jpg', 'TEACHER', '21-285-2123', 'haoyun99@gmail.com', '教授');
INSERT INTO `teacher` VALUES (21, 'Chen Lu', '123456', '陈璐', 'images/avatar17.jpg', 'TEACHER', '769-0474-8793', 'chelu@gmail.com', '讲师');
INSERT INTO `teacher` VALUES (22, 'Zhu Lan', '123456', '朱岚', 'images/avatar14.jpg', 'TEACHER', '769-761-1580', 'lazh@mail.com', '教授');
INSERT INTO `teacher` VALUES (23, 'Fu Rui', '123456', '傅睿', 'images/avatar3.jpg', 'TEACHER', '21-636-6606', 'rui50@gmail.com', '副教授');
INSERT INTO `teacher` VALUES (24, 'Liao Zhiyuan', '123456', '廖致远', 'images/avatar16.jpg', 'TEACHER', '175-7920-9799', 'zhiyl1124@gmail.com', '讲师');
INSERT INTO `teacher` VALUES (25, 'Yan Shihan', '123456', '严詩涵', 'images/avatar16.jpg', 'TEACHER', '145-8757-2657', 'sya6@gmail.com', '讲师');
INSERT INTO `teacher` VALUES (26, 'Gong Rui', '123456', '龚睿', 'images/avatar6.jpg', 'TEACHER', '196-9594-9099', 'ruigong8@gmail.com', '教授');
INSERT INTO `teacher` VALUES (27, 'Wei Xiaoming', '123456', '魏晓明', 'images/avatar5.jpg', 'TEACHER', '28-125-1423', 'xiaomwei2012@qq.com', '副教授');
INSERT INTO `teacher` VALUES (28, 'Liang Yunxi', '123456', '梁云熙', 'images/avatar18.jpg', 'TEACHER', '769-539-3694', 'liangyunxi5@gmail.com', '讲师');
INSERT INTO `teacher` VALUES (29, 'Li Anqi', '123456', '黎安琪', 'images/avatar4.jpg', 'TEACHER', '188-5641-7738', 'lianqi@gmail.com', '教授');
INSERT INTO `teacher` VALUES (30, 'Ren Yunxi', '123456', '任云熙', 'images/avatar7.jpg', 'TEACHER', '20-3421-9451', 'yuren9@gmail.com', '讲师');



CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `number` int DEFAULT NULL COMMENT '实验室编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实验室名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实验室类型',
  `week` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '周次',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教师姓名',
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '年级',
  `t1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `t2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `t3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `t4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `t5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `t6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='课程表';
INSERT INTO `course` (`id`, `number`, `name`, `type`, `week`, `teacher_name`, `grade`, `t1`, `t2`, `t3`, `t4`, `t5`, `t6`) VALUES (1, 532, '程序设计实验室', '软件', '周一,周三,周五', '丁詩涵', '大二', '上午8:00-10:00', '下午2:00-4:00', '上午8:00-10:00', '下午2:00-4:00', '上午8:00-10:00', '下午2:00-4:00');

-- ----------------------------
-- Triggers structure for table admin
-- ----------------------------
DROP TRIGGER IF EXISTS `prevent_admin_changes`;
delimiter ;;
CREATE TRIGGER `prevent_admin_changes` BEFORE INSERT ON `admin` FOR EACH ROW BEGIN
  SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'Error: 不允许更改管理表';
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table lab_admin
-- ----------------------------
DROP TRIGGER IF EXISTS `lab_admin_password_length`;
delimiter ;;
CREATE TRIGGER `lab_admin_password_length` BEFORE INSERT ON `lab_admin` FOR EACH ROW BEGIN
    IF LENGTH(NEW.password) < 6 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = '密码长度必须至少为6个字符';
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `student_password_length`;
delimiter ;;
CREATE TRIGGER `student_password_length` BEFORE INSERT ON `student` FOR EACH ROW BEGIN
    IF LENGTH(NEW.password) < 6 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = '密码长度必须至少为6个字符';
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `check_password_length`;
delimiter ;;
CREATE TRIGGER `check_password_length` BEFORE INSERT ON `teacher` FOR EACH ROW BEGIN
    IF LENGTH(NEW.password) < 6 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = '密码长度必须至少为6个字符';
    END IF;
END
;;
delimiter ;57

SET FOREIGN_KEY_CHECKS = 1;
