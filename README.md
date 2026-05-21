# 电商系统 - 完整开发说明

## 一、项目简介

基于SpringBoot开发的简单电商网页系统，严格遵循三层架构（Controller + Service + Dao），使用MySQL数据库和MyBatis持久层框架。

## 二、技术栈

- **后端框架**: SpringBoot 4.0.5
- **持久层**: MyBatis 4.0.1
- **数据库**: MySQL 8.0+
- **构建工具**: Maven
- **Java版本**: JDK 17
- **前端**: 原生HTML + JavaScript（无框架、无CDN）

## 三、数据库设计

### 核心数据表（5大表）

1. **user** - 用户信息表
2. **category** - 商品分类表
3. **merchant** - 商家信息表
4. **product** - 商品信息表
5. **orders** - 订单表
6. **order_item** - 订单明细表
7. **payment** - 支付记录表
8. **logistics** - 物流记录表

### 数据库初始化

1. 执行 `database/schema.sql` 文件创建数据库和表结构
2. 默认数据库密码: `123456`
3. 测试账号:
   - 用户名: `admin` / 密码: `123456`
   - 用户名: `testuser` / 密码: `123456`

## 四、项目结构

```
shopping/
├── database/
│   └── schema.sql                    # 数据库建表脚本
├── src/main/java/cn/edu/sdust/shopping/
│   ├── ShoppingApplication.java      # 启动类
│   ├── common/
│   │   └── Result.java               # 统一返回结果
│   ├── controller/                   # 控制层
│   │   ├── UserController.java
│   │   ├── ProductController.java
│   │   ├── OrdersController.java
│   │   ├── CategoryController.java
│   │   ├── MerchantController.java
│   │   ├── PaymentController.java
│   │   └── LogisticsController.java
│   ├── service/                      # 业务层接口
│   │   ├── UserService.java
│   │   ├── ProductService.java
│   │   ├── OrdersService.java
│   │   ├── CategoryService.java
│   │   ├── MerchantService.java
│   │   ├── PaymentService.java
│   │   └── LogisticsService.java
│   ├── service/impl/                 # 业务层实现
│   │   ├── UserServiceImpl.java
│   │   ├── ProductServiceImpl.java
│   │   ├── OrdersServiceImpl.java
│   │   ├── CategoryServiceImpl.java
│   │   ├── MerchantServiceImpl.java
│   │   ├── PaymentServiceImpl.java
│   │   └── LogisticsServiceImpl.java
│   ├── mapper/                       # 数据访问层
│   │   ├── UserMapper.java
│   │   ├── ProductMapper.java
│   │   ├── OrdersMapper.java
│   │   ├── OrderItemMapper.java
│   │   ├── CategoryMapper.java
│   │   ├── MerchantMapper.java
│   │   ├── PaymentMapper.java
│   │   └── LogisticsMapper.java
│   ├── entity/                       # 实体类
│   │   ├── User.java
│   │   ├── Product.java
│   │   ├── Orders.java
│   │   ├── OrderItem.java
│   │   ├── Category.java
│   │   ├── Merchant.java
│   │   ├── Payment.java
│   │   └── Logistics.java
│   └── util/
│       └── MD5Util.java              # MD5加密工具
├── src/main/resources/
│   ├── application.properties        # 配置文件
│   ├── mapper/                       # MyBatis映射文件
│   │   ├── UserMapper.xml
│   │   ├── ProductMapper.xml
│   │   ├── OrdersMapper.xml
│   │   ├── OrderItemMapper.xml
│   │   ├── CategoryMapper.xml
│   │   ├── MerchantMapper.xml
│   │   ├── PaymentMapper.xml
│   │   └── LogisticsMapper.xml
│   └── static/                       # 前端静态页面
│       ├── login.html                # 登录页面
│       ├── register.html             # 注册页面
│       ├── index.html                # 首页
│       ├── products.html             # 商品列表
│       ├── orders.html               # 订单管理
│       └── profile.html              # 个人中心
└── pom.xml                           # Maven配置
```

## 五、核心功能

### 1. 用户模块
- ✅ 用户注册（含表单验证）
- ✅ 用户登录（MD5密码加密）
- ✅ 个人信息查询与修改
- ✅ 用户注销

### 2. 商品模块
- ✅ 商品列表展示
- ✅ 商品详情查看
- ✅ 商品搜索（模糊查询）
- ✅ 按分类筛选
- ✅ 商品上下架状态管理
- ✅ 库存管理

### 3. 订单模块
- ✅ 创建订单
- ✅ 订单列表查询
- ✅ 订单状态管理（待支付、已支付、已发货、已完成、已取消）
- ✅ 订单取消
- ✅ 订单删除

### 4. 支付模块
- ✅ 支付记录创建
- ✅ 支付状态更新
- ✅ 支付信息查询

### 5. 物流模块
- ✅ 物流记录创建
- ✅ 物流状态更新
- ✅ 物流信息查询

### 6. 分类与商家模块
- ✅ 分类增删改查
- ✅ 商家增删改查

## 六、API接口说明

### 用户接口 `/api/user`
- `POST /login` - 用户登录
- `POST /register` - 用户注册
- `GET /current` - 获取当前用户
- `GET /{userId}` - 查询用户
- `GET /list` - 查询所有用户
- `PUT /update` - 更新用户信息
- `DELETE /{userId}` - 删除用户
- `POST /logout` - 退出登录

### 商品接口 `/api/product`
- `GET /{productId}` - 查询商品详情
- `GET /list` - 查询所有商品
- `GET /onsale` - 查询上架商品
- `GET /category/{categoryId}` - 按分类查询
- `GET /search?keyword=xxx` - 搜索商品
- `POST /add` - 新增商品
- `PUT /update` - 更新商品
- `DELETE /{productId}` - 删除商品
- `PUT /stock/{productId}?stock=xxx` - 更新库存

### 订单接口 `/api/order`
- `GET /{orderId}` - 查询订单
- `GET /user/{userId}` - 查询用户订单
- `GET /list` - 查询所有订单
- `POST /create` - 创建订单
- `PUT /update` - 更新订单
- `PUT /cancel/{orderId}` - 取消订单
- `DELETE /{orderId}` - 删除订单

### 分类接口 `/api/category`
- `GET /list` - 查询所有分类
- `GET /enabled` - 查询启用分类
- `POST /add` - 新增分类
- `PUT /update` - 更新分类
- `DELETE /{categoryId}` - 删除分类

### 商家接口 `/api/merchant`
- `GET /list` - 查询所有商家
- `GET /active` - 查询活跃商家
- `POST /add` - 新增商家
- `PUT /update` - 更新商家
- `DELETE /{merchantId}` - 删除商家

### 支付接口 `/api/payment`
- `GET /{paymentId}` - 查询支付记录
- `GET /order/{orderId}` - 按订单查询支付
- `POST /create` - 创建支付记录
- `PUT /update` - 更新支付状态

### 物流接口 `/api/logistics`
- `GET /{logisticsId}` - 查询物流记录
- `GET /order/{orderId}` - 按订单查询物流
- `POST /create` - 创建物流记录
- `PUT /update` - 更新物流状态

## 七、运行步骤

### 1. 环境准备
- 安装JDK 17
- 安装Maven 3.6+
- 安装MySQL 8.0+

### 2. 数据库配置
```sql
-- 执行数据库脚本
mysql -u root -p < database/schema.sql
```
``
### 3. 修改配置
编辑 `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=123456  # 修改为你的数据库密码
```

### 4. 编译项目
```bash
mvn clean compile
```

### 5. 运行项目
```bash
mvn spring-boot:run
```

或者直接运行 `ShoppingApplication.java`

### 6. 访问系统
浏览器打开: `http://localhost:8081/login.html`

测试账号:
- 用户名: `admin`
- 密码: `123456`

## 八、前端页面说明

所有前端页面均为纯静态HTML + JavaScript，无需任何框架或CDN：

1. **login.html** - 登录页面，支持账号密码校验
2. **register.html** - 注册页面，完整的表单验证
3. **index.html** - 首页，展示分类和热门商品
4. **products.html** - 商品列表，支持购买操作
5. **orders.html** - 订单管理，支持支付和取消
6. **profile.html** - 个人中心，可修改个人信息

## 九、注意事项

1. **跨域问题**: 所有Controller已添加 `@CrossOrigin` 注解支持跨域
2. **会话管理**: 使用HttpSession存储登录状态
3. **密码加密**: 使用MD5加密存储密码
4. **事务管理**: 订单创建等关键操作使用 `@Transactional` 保证事务
5. **SQL注入防护**: 使用MyBatis参数化查询，杜绝SQL硬编码

## 十、常见问题

### Q1: 启动时报数据库连接错误
A: 检查MySQL是否启动，确认数据库密码是否正确

### Q2: 前端页面无法访问
A: 确保static目录下的HTML文件存在，检查端口8080是否被占用

### Q3: 登录后跳转到首页失败
A: 检查浏览器的localStorage是否可用，清除缓存后重试

### Q4: MyBatis映射文件找不到
A: 确认 `application.properties` 中 `mybatis.mapper-locations` 配置正确

## 十一、扩展建议

1. 添加图片上传功能
2. 实现购物车功能
3. 添加商品评价系统
4. 实现优惠券功能
5. 添加数据统计报表
6. 集成Redis缓存
7. 使用JWT替代Session

## 十二、联系方式

如有问题，请提交Issue或联系开发者。

---

**祝使用愉快！** 🎉
