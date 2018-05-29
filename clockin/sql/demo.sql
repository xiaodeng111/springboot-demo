/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.120
Source Server Version : 50548
Source Host           : 192.168.2.120:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2018-05-29 10:59:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `school_code` varchar(10) DEFAULT NULL COMMENT '学校编号',
  `login_name` varchar(60) DEFAULT NULL COMMENT '登录账号',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(60) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `is_onjob` int(2) DEFAULT NULL COMMENT '是否在职（1-是；0-否）',
  `is_lock` int(2) DEFAULT NULL COMMENT '是否锁定（1-是；0-否）',
  `creator` varchar(60) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(200) DEFAULT NULL COMMENT '最后登录IP',
  `role_id` int(11) DEFAULT NULL,
  `modifier` varchar(60) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', '0', 'admin', '83aa400af464c76d', null, null, '1', '0', '超级管理员', '2017-03-13 10:05:26', '2018-05-29 10:53:49', '192.168.2.253', '1', '黎', null);

-- ----------------------------
-- Table structure for manager_info
-- ----------------------------
DROP TABLE IF EXISTS `manager_info`;
CREATE TABLE `manager_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `manager_id` int(11) DEFAULT NULL COMMENT '教师账号编号',
  `school_code` varchar(10) DEFAULT NULL COMMENT '学校编号',
  `name` varchar(60) DEFAULT NULL COMMENT '姓名',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `card_type` int(2) DEFAULT NULL COMMENT '证件类型',
  `card_code` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `wechat` varchar(100) DEFAULT NULL COMMENT '微信号',
  `qq` varchar(30) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(256) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `phone` varchar(30) DEFAULT NULL COMMENT '座机号码',
  `is_onjob` int(2) DEFAULT NULL COMMENT '是否在职（1-是；0-否）',
  `job_category` int(2) DEFAULT NULL COMMENT '工作性质（1-全职；0-兼职）',
  `academies` varchar(200) DEFAULT NULL COMMENT '毕业院校',
  `awards` varchar(200) DEFAULT NULL COMMENT '所获奖项',
  `start_work` varchar(10) DEFAULT NULL COMMENT '从业开始时间',
  `summary` varchar(256) DEFAULT NULL COMMENT '概要介绍',
  `details` text COMMENT '详细介绍',
  `creator` varchar(60) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(60) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `logo` varchar(256) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_manager_id` (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager_info
-- ----------------------------
INSERT INTO `manager_info` VALUES ('1', '1', '0', '超级管理员', null, null, null, 'Silence', '1234561112', 'admin@qq.com', '12345678901', null, '1', null, '18101182352', '和我一起无所谓\r\n哄你哦破网速', null, '测试', '测试', '1', null, 'admin@pxjy.com', null, 'images/20171014/2017101411561463382.jpg', null);

-- ----------------------------
-- Table structure for resource_action
-- ----------------------------
DROP TABLE IF EXISTS `resource_action`;
CREATE TABLE `resource_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name_zh` varchar(32) NOT NULL COMMENT '名称',
  `name_en` varchar(32) NOT NULL COMMENT '英文名称',
  `bit` int(11) DEFAULT NULL COMMENT '位值',
  `action_status` int(2) DEFAULT NULL COMMENT '状态',
  `creator_id` int(11) NOT NULL COMMENT '创建人id',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='资源动作';

-- ----------------------------
-- Records of resource_action
-- ----------------------------
INSERT INTO `resource_action` VALUES ('1', '查看', 'execute', '1', '1', '1', '超级管理员', '2014-08-06 19:43:35', '');
INSERT INTO `resource_action` VALUES ('2', '增加', 'add', '2', '1', '1', '超级管理员', '2014-07-31 11:11:36', '');
INSERT INTO `resource_action` VALUES ('3', '删除', 'delete', '4', '1', '1', '超级管理员', '2014-08-06 12:05:28', '');
INSERT INTO `resource_action` VALUES ('4', '修改', 'edit', '8', '1', '1', '超级管理员', '2014-07-31 17:35:42', '');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NOT NULL COMMENT '上级资源',
  `name_en` varchar(64) NOT NULL COMMENT '英文名称',
  `name_zh` varchar(64) NOT NULL COMMENT '中文名称',
  `resource_type` int(11) NOT NULL COMMENT '类型',
  `actions` int(11) NOT NULL COMMENT '动作',
  `default_uri` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `resource_status` int(11) DEFAULT NULL COMMENT '状态',
  `resource_order` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creator_id` int(11) NOT NULL COMMENT '创建人id',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '0', 'systemMenu', '系统菜单', '1', '0', '#', '1', '5', '', '1', '超级管理员', '2017-03-29 18:45:28');
INSERT INTO `resources` VALUES ('2', '0', 'buesinessMenu', '业务菜单', '1', '0', '#', '1', '1', '', '1', '超级管理员', '2017-10-08 17:16:14');
INSERT INTO `resources` VALUES ('3', '2', 'buesinessFunction', '业务功能', '2', '0', '#', '1', '1', '', '1', '超级管理员', '2014-08-06 11:46:48');
INSERT INTO `resources` VALUES ('4', '1', 'systemManage', '系统管理', '2', '0', '#', '1', '1', '', '1', '超级管理员', '2017-03-29 18:45:59');
INSERT INTO `resources` VALUES ('5', '1', 'resourcesManage', '资源管理', '2', '0', '#', '1', '2', '', '1', '超级管理员', '2014-08-06 12:01:17');
INSERT INTO `resources` VALUES ('6', '4', 'manager', '账号列表', '3', '11', '/admin/manager/managerAction', '1', '1', '', '1', '超级管理员', '2017-03-02 18:22:09');
INSERT INTO `resources` VALUES ('7', '4', 'roles', '角色', '3', '15', '/admin/roles/rolesAction', '1', '2', '', '1', '超级管理员', '2017-03-02 18:21:58');
INSERT INTO `resources` VALUES ('8', '5', 'resources', '资源', '3', '15', '/admin/resources/resourcesAction', '1', '1', '', '1', '超级管理员', '2014-08-06 12:02:10');
INSERT INTO `resources` VALUES ('9', '5', 'resourcesAction', '资源动作', '3', '15', '/admin/resourceAction/resourceActionAction', '1', '2', '', '1', '超级管理员', '2014-08-06 12:03:01');
INSERT INTO `resources` VALUES ('10', '0', 'selfmanage', '账户菜单', '1', '0', '#', '1', '6', '', '1', '超级管理员', '2017-10-08 17:12:56');
INSERT INTO `resources` VALUES ('11', '10', 'selfmanager', '账户管理', '2', '0', '#', '1', '1', '', '1', '超级管理员', '2017-10-08 17:14:08');
INSERT INTO `resources` VALUES ('12', '11', 'resetpw', '修改密码', '3', '8', '/account/onChange', '1', '1', '', '1', '超级管理员', '2017-10-08 17:15:30');
INSERT INTO `resources` VALUES ('13', '11', 'editmanagerinfo', '修改个人信息', '3', '8', '/account/onEdit', '1', '2', '', '1', '超级管理员', '2017-10-08 17:16:14');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `role_id` int(11) NOT NULL COMMENT 'role_id',
  `reource_id` int(11) NOT NULL COMMENT 'reource_id',
  `action_bit` int(11) NOT NULL COMMENT 'action_bit',
  PRIMARY KEY (`role_id`,`reource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源角色关联';

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '0');
INSERT INTO `role_resource` VALUES ('1', '2', '0');
INSERT INTO `role_resource` VALUES ('1', '3', '0');
INSERT INTO `role_resource` VALUES ('1', '4', '0');
INSERT INTO `role_resource` VALUES ('1', '5', '0');
INSERT INTO `role_resource` VALUES ('1', '6', '11');
INSERT INTO `role_resource` VALUES ('1', '7', '15');
INSERT INTO `role_resource` VALUES ('1', '8', '15');
INSERT INTO `role_resource` VALUES ('1', '9', '15');
INSERT INTO `role_resource` VALUES ('1', '10', '0');
INSERT INTO `role_resource` VALUES ('1', '11', '0');
INSERT INTO `role_resource` VALUES ('1', '12', '3');
INSERT INTO `role_resource` VALUES ('1', '13', '3');
INSERT INTO `role_resource` VALUES ('2', '1', '0');
INSERT INTO `role_resource` VALUES ('2', '4', '0');
INSERT INTO `role_resource` VALUES ('2', '6', '11');
INSERT INTO `role_resource` VALUES ('2', '7', '15');
INSERT INTO `role_resource` VALUES ('2', '10', '0');
INSERT INTO `role_resource` VALUES ('2', '11', '0');
INSERT INTO `role_resource` VALUES ('2', '12', '8');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL COMMENT '角色名称',
  `ROLE_STATUS` int(2) NOT NULL COMMENT '角色状态',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATOR_ID` int(11) DEFAULT NULL COMMENT '创建人ID',
  `CREATOR` varchar(64) DEFAULT NULL,
  `DESCRIPTION` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLENAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '超级管理员', '1', '2018-04-03 11:39:38', '1', '超级管理员', '超级管理员，拥有所有权限');
INSERT INTO `roles` VALUES ('2', '普通管理员', '1', '2018-05-29 10:40:22', '1', 'admin', '');
