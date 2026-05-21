-- 购物车功能数据库更新脚本
-- 执行此脚本前请确保已执行过 schema.sql

USE shopping_db;

-- 创建购物车表
DROP TABLE IF EXISTS `cart_item`;
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
