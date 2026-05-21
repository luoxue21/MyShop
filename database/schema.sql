-- 电商系统数据库建表脚本
-- 数据库：shopping_db
-- 密码：123456

-- 创建数据库
CREATE DATABASE IF NOT EXISTS shopping_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shopping_db;

-- 1. 用户信息表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名，唯一',
    `password` VARCHAR(100) NOT NULL COMMENT '密码（加密存储）',
    `real_name` VARCHAR(50) DEFAULT NULL COMMENT '真实姓名',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `gender` TINYINT DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
    `address` VARCHAR(255) DEFAULT NULL COMMENT '收货地址',
    `status` TINYINT DEFAULT 1 COMMENT '账号状态：0-禁用，1-正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 2. 商品分类与商家信息表
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
    `category_id` INT NOT NULL AUTO_INCREMENT COMMENT '分类ID，主键',
    `category_name` VARCHAR(50) NOT NULL COMMENT '分类名称',
    `parent_id` INT DEFAULT 0 COMMENT '父分类ID，0表示一级分类',
    `sort_order` INT DEFAULT 0 COMMENT '排序序号',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
    `merchant_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商家ID，主键',
    `merchant_name` VARCHAR(100) NOT NULL COMMENT '商家名称',
    `contact_person` VARCHAR(50) DEFAULT NULL COMMENT '联系人',
    `contact_phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `address` VARCHAR(255) DEFAULT NULL COMMENT '商家地址',
    `status` TINYINT DEFAULT 1 COMMENT '商家状态：0-关停，1-正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '入驻时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家信息表';

-- 3. 商品信息表
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
    `product_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID，主键',
    `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
    `category_id` INT NOT NULL COMMENT '分类ID，外键关联category表',
    `merchant_id` BIGINT NOT NULL COMMENT '商家ID，外键关联merchant表',
    `price` DECIMAL(10,2) NOT NULL COMMENT '售价',
    `original_price` DECIMAL(10,2) DEFAULT NULL COMMENT '原价',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
    `sales` INT DEFAULT 0 COMMENT '销量',
    `description` TEXT COMMENT '商品详情描述',
    `image_url` VARCHAR(500) DEFAULT NULL COMMENT '商品图片URL',
    `status` TINYINT DEFAULT 1 COMMENT '上架状态：0-下架，1-上架',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`product_id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_merchant_id` (`merchant_id`),
    CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
    CONSTRAINT `fk_product_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- 4. 商品订单数据表
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
    `order_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID，主键',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单编号，唯一',
    `user_id` BIGINT NOT NULL COMMENT '用户ID，外键关联user表',
    `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    `receiver_name` VARCHAR(50) NOT NULL COMMENT '收货人姓名',
    `receiver_phone` VARCHAR(20) NOT NULL COMMENT '收货人电话',
    `receiver_address` VARCHAR(255) NOT NULL COMMENT '收货地址',
    `order_status` TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付，1-已支付，2-已发货，3-已完成，4-已取消',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '订单备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`order_id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    CONSTRAINT `fk_orders_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品订单表';

-- 订单明细表（关联订单和商品）
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
    `item_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '明细ID，主键',
    `order_id` BIGINT NOT NULL COMMENT '订单ID，外键关联orders表',
    `product_id` BIGINT NOT NULL COMMENT '商品ID，外键关联product表',
    `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称（快照）',
    `product_image` VARCHAR(500) DEFAULT NULL COMMENT '商品图片（快照）',
    `price` DECIMAL(10,2) NOT NULL COMMENT '购买时单价',
    `quantity` INT NOT NULL COMMENT '购买数量',
    `total_price` DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    PRIMARY KEY (`item_id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_product_id` (`product_id`),
    CONSTRAINT `fk_order_item_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `fk_order_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- 5. 订单支付与物流记录表
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
    `payment_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '支付ID，主键',
    `order_id` BIGINT NOT NULL COMMENT '订单ID，外键关联orders表',
    `payment_no` VARCHAR(50) NOT NULL COMMENT '支付流水号，唯一',
    `payment_method` TINYINT NOT NULL COMMENT '支付方式：1-支付宝，2-微信，3-银行卡',
    `payment_amount` DECIMAL(10,2) NOT NULL COMMENT '支付金额',
    `payment_status` TINYINT DEFAULT 0 COMMENT '支付状态：0-待支付，1-支付成功，2-支付失败',
    `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`payment_id`),
    UNIQUE KEY `uk_payment_no` (`payment_no`),
    KEY `idx_order_id` (`order_id`),
    CONSTRAINT `fk_payment_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单支付表';

DROP TABLE IF EXISTS `logistics`;
CREATE TABLE `logistics` (
    `logistics_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '物流ID，主键',
    `order_id` BIGINT NOT NULL COMMENT '订单ID，外键关联orders表',
    `logistics_no` VARCHAR(50) NOT NULL COMMENT '物流单号',
    `logistics_company` VARCHAR(50) DEFAULT NULL COMMENT '物流公司',
    `logistics_status` TINYINT DEFAULT 0 COMMENT '物流状态：0-未发货，1-已发货，2-运输中，3-已签收',
    `ship_time` DATETIME DEFAULT NULL COMMENT '发货时间',
    `receive_time` DATETIME DEFAULT NULL COMMENT '签收时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`logistics_id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_logistics_no` (`logistics_no`),
    CONSTRAINT `fk_logistics_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物流记录表';

-- 6. 购物车表
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
    `cart_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '购物车ID，主键',
    `user_id` BIGINT NOT NULL COMMENT '用户ID，外键关联user表',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`cart_id`),
    UNIQUE KEY `uk_user_id` (`user_id`),
    CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 购物车项表
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
    `item_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '购物车项ID，主键',
    `cart_id` BIGINT NOT NULL COMMENT '购物车ID，外键关联cart表',
    `product_id` BIGINT NOT NULL COMMENT '商品ID，外键关联product表',
    `quantity` INT NOT NULL DEFAULT 1 COMMENT '商品数量',
    `checked` TINYINT DEFAULT 1 COMMENT '是否选中：0-未选中，1-选中',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`item_id`),
    KEY `idx_cart_id` (`cart_id`),
    KEY `idx_product_id` (`product_id`),
    CONSTRAINT `fk_cart_item_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
    CONSTRAINT `fk_cart_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车项表';

-- 插入测试数据

-- 插入商品分类
INSERT INTO `category` (`category_name`, `parent_id`, `sort_order`) VALUES
('电子产品', 0, 1),
('服装鞋帽', 0, 2),
('家居用品', 0, 3),
('手机', 1, 1),
('电脑', 1, 2),
('男装', 2, 1),
('女装', 2, 2);

-- 插入商家
INSERT INTO `merchant` (`merchant_name`, `contact_person`, `contact_phone`, `address`) VALUES
('华为官方旗舰店', '张三', '13800138001', '深圳市南山区'),
('小米官方旗舰店', '李四', '13800138002', '北京市海淀区'),
('优衣库官方旗舰店', '王五', '13800138003', '上海市浦东新区');

-- 插入测试用户（密码为123456的MD5加密值：e10adc3949ba59abbe56e057f20f883e）
INSERT INTO `user` (`username`, `password`, `real_name`, `phone`, `email`, `gender`, `address`) VALUES
('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '13800138000', 'admin@example.com', 1, '北京市朝阳区'),
('testuser', 'e10adc3949ba59abbe56e057f20f883e', '测试用户', '13900139000', 'test@example.com', 2, '上海市黄浦区');

-- 插入测试商品
INSERT INTO `product` (`product_name`, `category_id`, `merchant_id`, `price`, `original_price`, `stock`, `sales`, `description`, `image_url`, `status`) VALUES
('华为Mate 60 Pro', 4, 1, 6999.00, 7999.00, 100, 50, '华为旗舰手机，搭载麒麟芯片', '/images/huawei-mate60.jpg', 1),
('小米14 Ultra', 4, 2, 5999.00, 6999.00, 150, 80, '小米高端旗舰，徕卡影像', '/images/xiaomi-14-ultra.jpg', 1),
('MacBook Pro 14', 5, 1, 14999.00, 16999.00, 50, 30, '苹果笔记本电脑，M3芯片', '/images/macbook-pro.jpg', 1),
('男士休闲衬衫', 6, 3, 199.00, 299.00, 500, 200, '纯棉材质，舒适透气', '/images/mens-shirt.jpg', 1),
('女士连衣裙', 7, 3, 399.00, 599.00, 300, 150, '优雅设计，适合多种场合', '/images/womens-dress.jpg', 1);
