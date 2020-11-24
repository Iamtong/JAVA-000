
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '商家id',
  `goods_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品编码',
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `goods_pics` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头图',
  `goods_ad` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品广告语15个字',
  `goods_stock` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品库存',
  `goods_price` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `goods_cost_price` decimal(12, 2) NOT NULL COMMENT '成本价',
  `sale_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '累计销售量',
  `goods_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '商品状态 0下架 1上架',
  `goods_status_time` timestamp(0) NOT NULL COMMENT '上下架时间',
  `created_time` timestamp(0) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_name`(`goods_name`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(10) UNSIGNED NOT NULL COMMENT '订单ID',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `goods_id` int(10) UNSIGNED NOT NULL COMMENT '商品id',
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '冗余商品名称',
  `goods_pic` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '冗余商品头图',
  `goods_price` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `buy_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '购买件数',
  `pay_price` decimal(12, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '实付价格',
  `is_comment` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否评价过（暂不使用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单商品详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------

-- ----------------------------
-- Table structure for order_refunds
-- ----------------------------
DROP TABLE IF EXISTS `order_refunds`;
CREATE TABLE `order_refunds`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(10) UNSIGNED NOT NULL COMMENT '订单ID',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `refund_money` decimal(12, 2) UNSIGNED NOT NULL COMMENT '总退款金额',
  `refund_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 1申请退款 2退款成功 3退款失败 ',
  `refunded_time` timestamp(0) NULL DEFAULT NULL COMMENT '退款时间',
  `created_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '退款单表（简单设计，整单退，不包含物流）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_refunds
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单号 不单独设计订单ID了',
  `order_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单简介',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `goods_total_price` decimal(12, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '商品总价',
  `freight_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '运费',
  `order_pay_price` decimal(12, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '需要支付',
  `order_total_price` decimal(12, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '原总价',
  `created_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `paid_time` timestamp(0) NULL DEFAULT NULL COMMENT '支付时间',
  `sended_time` timestamp(0) NULL DEFAULT NULL COMMENT '发货时间',
  `taked_time` timestamp(0) NULL DEFAULT NULL COMMENT '收货时间',
  `closed_time` timestamp(0) NULL DEFAULT NULL COMMENT '关闭时间',
  `buy_note` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '买家附言',
  `order_state` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态 0:新订单 1已支付,2已发货,3:已完成 5:关闭',
  `close_state` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '关闭原因 0未关闭 1未支付 2退款关闭（退款不涉及复杂单个商品退款）',
  `refund_status` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '冗余退款状态 状态 0未申请 1申请退款 2退款成功 3退款失败 ',
  `refund_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '退款申请单号 当有退款时',
  `post_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人 物流这一部分简单设计了',
  `post_phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货电话',
  `post_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货地址',
  `express_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '快递号',
  `express_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '快递公司',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `created_time`(`created_time`) USING BTREE,
  INDEX `paid_time`(`paid_time`) USING BTREE,
  INDEX `taked_time`(`taked_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_pass` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号码（按监管要求这里要加密）',
  `balance` decimal(12, 2) NULL DEFAULT NULL COMMENT '账户余额（本来分表，这里简单设计到一起）',
  `real_name` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址（本来应该设计一个地址表，省区县详细地址，多地址选择，这里简单处理）',
  `created_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '注册IP',
  `created_time` timestamp(0) NULL DEFAULT NULL COMMENT '注册时间',
  `updated_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最近登录IP',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最近登录时间',
  `is_lock` tinyint(1) NULL DEFAULT NULL COMMENT '是否锁定 0为正常 1为锁定',
  `lock_mark` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '锁定原因',
  `lock_time` timestamp(0) NULL DEFAULT NULL COMMENT '账号锁定时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
