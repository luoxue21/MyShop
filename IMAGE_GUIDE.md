# 商品图片使用说明

## 概述
系统已为商品添加了图片功能支持。本文档说明如何添加和管理商品图片。

## 图片目录结构
图片应放置在以下目录：
```
src/main/resources/static/images/
```

## 当前商品图片配置

数据库中已为以下商品配置了图片路径：

1. **华为Mate 60 Pro** - `/images/huawei-mate60.jpg`
2. **小米14 Ultra** - `/images/xiaomi-14-ultra.jpg`
3. **MacBook Pro 14** - `/images/macbook-pro.jpg`
4. **男士休闲衬衫** - `/images/mens-shirt.jpg`
5. **女士连衣裙** - `/images/womens-dress.jpg`

## 添加实际图片的步骤

### 方法一：使用在线图片服务（推荐用于测试）

如果您想快速测试，可以使用免费的在线图片占位符服务：

1. 访问 https://picsum.photos/ 或 https://placeholder.com/
2. 获取图片URL
3. 更新数据库中的 `image_url` 字段

例如：
```sql
UPDATE product SET image_url = 'https://picsum.photos/400/400?random=1' WHERE product_id = 1;
```

### 方法二：使用本地图片文件

1. **准备图片文件**
   - 格式支持：JPG, PNG, GIF, WebP
   - 建议尺寸：800x800 像素或更高
   - 文件大小：建议小于 500KB

2. **将图片复制到 images 目录**
   ```
   src/main/resources/static/images/
   ├── huawei-mate60.jpg
   ├── xiaomi-14-ultra.jpg
   ├── macbook-pro.jpg
   ├── mens-shirt.jpg
   └── womens-dress.jpg
   ```

3. **重启应用**
   Spring Boot 会自动提供静态资源访问

4. **访问图片**
   图片URL格式：`http://localhost:8081/images/文件名.jpg`

### 方法三：使用外部图片CDN

您可以使用阿里云OSS、七牛云等对象存储服务：

1. 上传图片到CDN
2. 获取图片URL
3. 更新数据库

例如：
```sql
UPDATE product SET image_url = 'https://your-cdn.com/products/huawei-mate60.jpg' WHERE product_id = 1;
```

## 更新数据库图片URL

如果数据库已经创建，运行以下SQL脚本更新图片URL：

```sql
USE shopping_db;

-- 更新现有商品的图片URL
UPDATE `product` SET `image_url` = '/images/huawei-mate60.jpg' WHERE `product_id` = 1;
UPDATE `product` SET `image_url` = '/images/xiaomi-14-ultra.jpg' WHERE `product_id` = 2;
UPDATE `product` SET `image_url` = '/images/macbook-pro.jpg' WHERE `product_id` = 3;
UPDATE `product` SET `image_url` = '/images/mens-shirt.jpg' WHERE `product_id` = 4;
UPDATE `product` SET `image_url` = '/images/womens-dress.jpg' WHERE `product_id` = 5;
```

或者执行提供的脚本文件：
```bash
mysql -u root -p shopping_db < database/update-product-images.sql
```

## 前端显示优化

系统已在以下页面优化了商品图片显示：

1. **首页 (index.html)** - 热门商品展示
2. **商品列表页 (products.html)** - 商品网格展示

### 图片显示特性

- ✅ 响应式设计，自适应不同屏幕
- ✅ 鼠标悬停放大效果
- ✅ 图片加载失败时显示"暂无图片"提示
- ✅ 保持图片比例，自动裁剪填充
- ✅ 平滑过渡动画

## 注意事项

1. **图片路径**
   - 本地图片使用相对路径：`/images/xxx.jpg`
   - 外部图片使用完整URL：`https://...`

2. **图片命名**
   - 建议使用英文和连字符
   - 避免中文和特殊字符
   - 示例：`huawei-mate60-pro.jpg`

3. **性能优化**
   - 压缩图片大小
   - 使用 WebP 格式可获得更好的压缩比
   - 考虑使用懒加载（lazy loading）

4. **跨域问题**
   - 如果使用外部图片，确保允许跨域访问
   - 或使用后端代理

## 故障排查

### 图片不显示

1. 检查图片文件是否存在于 `static/images/` 目录
2. 检查数据库中的 `image_url` 是否正确
3. 打开浏览器开发者工具，查看网络请求是否成功
4. 检查控制台是否有错误信息

### 图片路径404

确保：
- 图片文件确实存在于正确位置
- 文件扩展名正确（.jpg, .png等）
- Spring Boot应用正在运行
- 访问路径正确：`http://localhost:8081/images/xxx.jpg`

## 未来改进建议

1. 实现图片上传功能
2. 支持多张图片（商品相册）
3. 添加图片缩略图生成
4. 实现图片懒加载
5. 集成图片CDN加速
