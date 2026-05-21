-- 为商品添加图片URL
USE shopping_db;

-- 更新现有商品的图片URL
UPDATE `product` SET `image_url` = '/images/huawei-mate60.jpg' WHERE `product_id` = 1;
UPDATE `product` SET `image_url` = '/images/xiaomi-14-ultra.jpg' WHERE `product_id` = 2;
UPDATE `product` SET `image_url` = '/images/macbook-pro.jpg' WHERE `product_id` = 3;
UPDATE `product` SET `image_url` = '/images/mens-shirt.jpg' WHERE `product_id` = 4;
UPDATE `product` SET `image_url` = '/images/womens-dress.jpg' WHERE `product_id` = 5;

-- 验证更新结果
SELECT product_id, product_name, image_url FROM product;
