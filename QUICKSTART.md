# 快速启动指南

## 一、前置条件检查

在启动项目前，请确保已安装以下软件：

- ✅ JDK 17 或更高版本
- ✅ Maven 3.6+ 
- ✅ MySQL 8.0+

## 二、快速启动步骤（5分钟完成）

### 步骤1: 初始化数据库

打开命令行，执行：

```bash
# 登录MySQL
mysql -u root -p

# 执行建表脚本
source E:/JAVA_Project/shopping/database/schema.sql

# 或者直接在MySQL客户端中运行 schema.sql 文件内容
```

### 步骤2: 确认数据库配置

编辑文件: `src/main/resources/application.properties`

确认以下配置正确：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopping_db?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=123456  # 修改为你的实际密码
```

### 步骤3: 编译项目

在项目根目录执行：

```bash
cd E:\JAVA_Project\shopping
mvn clean compile
```

### 步骤4: 启动应用

```bash
mvn spring-boot:run
```

看到以下输出表示启动成功：
```
Started ShoppingApplication in X.XXX seconds
```

### 步骤5: 访问系统

浏览器打开: **http://localhost:8080/login.html**

使用测试账号登录：
- 用户名: `admin`
- 密码: `123456`

## 三、功能测试清单

### ✅ 用户模块测试
1. [ ] 使用admin账号登录
2. [ ] 注册新用户
3. [ ] 修改个人信息
4. [ ] 退出登录

### ✅ 商品模块测试
1. [ ] 浏览首页商品列表
2. [ ] 搜索商品（输入关键词）
3. [ ] 按分类筛选商品
4. [ ] 查看商品详情

### ✅ 订单模块测试
1. [ ] 在商品列表点击"立即购买"
2. [ ] 查看"我的订单"
3. [ ] 取消待支付订单
4. [ ] 模拟支付订单

### ✅ 数据验证
在MySQL中执行以下查询验证数据：

```sql
-- 查看所有用户
SELECT * FROM user;

-- 查看所有商品
SELECT * FROM product;

-- 查看所有订单
SELECT * FROM orders;

-- 查看完整订单信息（含支付和物流）
SELECT o.*, p.*, l.* 
FROM orders o
LEFT JOIN payment p ON o.order_id = p.order_id
LEFT JOIN logistics l ON o.order_id = l.order_id;
```

## 四、常见问题解决

### 问题1: 端口8080被占用

**解决方案**: 修改 `application.properties` 中的端口
```properties
server.port=8081  # 改为其他可用端口
```

### 问题2: 数据库连接失败

**检查清单**:
- [ ] MySQL服务是否启动
- [ ] 数据库 `shopping_db` 是否创建
- [ ] 用户名密码是否正确
- [ ] 防火墙是否阻止连接

### 问题3: Maven依赖下载慢

**解决方案**: 配置国内镜像源

编辑 `~/.m2/settings.xml`，添加阿里云镜像：
```xml
<mirrors>
    <mirror>
        <id>aliyun</id>
        <mirrorOf>central</mirrorOf>
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
</mirrors>
```

### 问题4: 前端页面样式异常

**原因**: 浏览器缓存问题

**解决**: 
- 清除浏览器缓存（Ctrl + Shift + Delete）
- 或使用无痕模式访问

### 问题5: 登录后无法跳转

**检查**:
- 打开浏览器开发者工具（F12）
- 查看Console是否有错误
- 确认后端服务正常运行
- 检查Network请求是否返回200状态码

## 五、API测试（使用Postman或curl）

### 测试登录接口
```bash
curl -X POST http://localhost:8080/api/user/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"123456"}'
```

### 测试商品列表
```bash
curl http://localhost:8080/api/product/onsale
```

### 测试创建订单
```bash
curl -X POST http://localhost:8080/api/order/create \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "totalAmount": 6999.00,
    "receiverName": "张三",
    "receiverPhone": "13800138000",
    "receiverAddress": "北京市朝阳区",
    "remark": "测试订单"
  }'
```

## 六、项目结构速览

```
shopping/
├── database/              # 数据库脚本
│   ├── schema.sql        # 建表脚本
│   └── test-data.sql     # 测试数据
├── src/main/java/        # Java源代码
│   └── cn/edu/sdust/shopping/
│       ├── controller/   # 控制层（7个Controller）
│       ├── service/      # 业务层（7个Service）
│       ├── mapper/       # 数据层（8个Mapper）
│       └── entity/       # 实体类（8个Entity）
├── src/main/resources/
│   ├── mapper/           # MyBatis XML映射
│   └── static/           # 前端HTML页面
└── pom.xml               # Maven配置
```

## 七、下一步操作

系统启动成功后，你可以：

1. 📖 阅读 `README.md` 了解完整功能
2. 🔧 修改代码实现自定义需求
3. 🎨 美化前端页面样式
4. 📊 添加更多统计功能
5. 🚀 部署到服务器

## 八、技术支持

如遇到问题：
1. 查看控制台错误日志
2. 检查数据库连接
3. 确认端口未被占用
4. 参考 README.md 常见问题部分

---

**祝开发顺利！** 🎉
