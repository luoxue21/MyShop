-- 电商系统测试数据脚本
-- 用于验证系统功能完整性

USE shopping_db;

-- 1. 验证用户表数据
SELECT '=== 用户列表 ===' AS info;
SELECT user_id, username, real_name, phone, status FROM user;

-- 2. 验证分类表数据
SELECT '=== 商品分类 ===' AS info;
SELECT category_id, category_name, parent_id, status FROM category ORDER BY sort_order;

-- 3. 验证商家表数据
SELECT '=== 商家列表 ===' AS info;
SELECT merchant_id, merchant_name, contact_person, contact_phone, status FROM merchant;

-- 4. 验证商品表数据
SELECT '=== 商品列表 ===' AS info;
SELECT product_id, product_name, category_id, merchant_id, price, stock, sales, status 
FROM product;

-- 5. 查询上架商品（带分类和商家信息）
SELECT '=== 上架商品详情 ===' AS info;
SELECT 
    p.product_id,
    p.product_name,
    c.category_name,
    m.merchant_name,
    p.price,
    p.original_price,
    p.stock,
    p.sales,
    p.status
FROM product p
LEFT JOIN category c ON p.category_id = c.category_id
LEFT JOIN merchant m ON p.merchant_id = m.merchant_id
WHERE p.status = 1;

-- 6. 插入测试订单（可选）
INSERT INTO orders (order_no, user_id, total_amount, receiver_name, receiver_phone, receiver_address, order_status)
VALUES ('ORD20260415000001', 1, 6999.00, '管理员', '13800138000', '北京市朝阳区', 0);

-- 7. 插入测试订单明细
INSERT INTO order_item (order_id, product_id, product_name, price, quantity, total_price)
VALUES (1, 1, '华为Mate 60 Pro', 6999.00, 1, 6999.00);

-- 8. 查询订单列表
SELECT '=== 订单列表 ===' AS info;
SELECT 
    o.order_id,
    o.order_no,
    u.username,
    o.total_amount,
    o.receiver_name,
    o.receiver_phone,
    o.order_status,
    o.create_time
FROM orders o
LEFT JOIN user u ON o.user_id = u.user_id;

-- 9. 插入测试支付记录
INSERT INTO payment (order_id, payment_no, payment_method, payment_amount, payment_status)
VALUES (1, 'PAY20260415000001', 1, 6999.00, 0);

-- 10. 插入测试物流记录
INSERT INTO logistics (order_id, logistics_no, logistics_company, logistics_status)
VALUES (1, 'SF1234567890', '顺丰快递', 0);

-- 11. 完整查询示例 - 订单及其支付和物流信息
SELECT '=== 订单完整信息 ===' AS info;
SELECT 
    o.order_no,
    u.username,
    o.total_amount,
    o.receiver_name,
    o.receiver_phone,
    o.receiver_address,
    o.order_status,
    p.payment_no,
    p.payment_method,
    p.payment_status,
    l.logistics_no,
    l.logistics_company,
    l.logistics_status
FROM orders o
LEFT JOIN user u ON o.user_id = u.user_id
LEFT JOIN payment p ON o.order_id = p.order_id
LEFT JOIN logistics l ON o.order_id = l.order_id
WHERE o.order_id = 1;

-- 12. 统计查询
SELECT '=== 数据统计 ===' AS info;
SELECT 
    (SELECT COUNT(*) FROM user WHERE status = 1) AS active_users,
    (SELECT COUNT(*) FROM product WHERE status = 1) AS onsale_products,
    (SELECT COUNT(*) FROM orders) AS total_orders,
    (SELECT COUNT(*) FROM merchant WHERE status = 1) AS active_merchants;

-- 13. 热门商品TOP5
SELECT '=== 热门商品TOP5 ===' AS info;
SELECT 
    product_name,
    price,
    sales,
    stock
FROM product
ORDER BY sales DESC
LIMIT 5;

-- 14. 各分类商品数量
SELECT '=== 分类商品统计 ===' AS info;
SELECT 
    c.category_name,
    COUNT(p.product_id) AS product_count,
    SUM(p.stock) AS total_stock,
    SUM(p.sales) AS total_sales
FROM category c
LEFT JOIN product p ON c.category_id = p.category_id
GROUP BY c.category_id, c.category_name;
