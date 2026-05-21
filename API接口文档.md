# 电商平台API接口文档

## 基础信息
- **Base URL**: `http://localhost:8081/api`
- **统一响应格式**: 
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {}
}
```

---

## 1. 用户模块 (/api/user)

### 1.1 用户登录
- **URL**: `/user/login`
- **Method**: `POST`
- **Request Body**:
```json
·
```
- **Response Data** (User对象):
```json
{
  "userId": 1,
  "username": "admin",
  "realName": "张三",
  "phone": "13800138000",
  "email": "admin@example.com",
  "gender": 1,
  "address": "北京市朝阳区",
  "status": 1,
  "createTime": "2024-01-01T00:00:00",
  "updateTime": "2024-01-01T00:00:00"
}
```

### 1.2 用户注册
- **URL**: `/user/register`
- **Method**: `POST`
- **Request Body** (User对象):
```json
{
  "username": "string",
  "password": "string",
  "realName": "string",
  "phone": "string",
  "email": "string",
  "gender": 1,
  "address": "string"
}
```

### 1.3 获取当前用户信息
- **URL**: `/user/current`
- **Method**: `GET`
- **Response Data**: User对象（同登录响应）

### 1.4 根据ID查询用户
- **URL**: `/user/{userId}`
- **Method**: `GET`
- **Response Data**: User对象

### 1.5 查询所有用户
- **URL**: `/user/list`
- **Method**: `GET`
- **Response Data**: User对象数组

### 1.6 更新用户信息
- **URL**: `/user/update`
- **Method**: `PUT`
- **Request Body**: User对象（需包含userId）

### 1.7 删除用户
- **URL**: `/user/{userId}`
- **Method**: `DELETE`

### 1.8 退出登录
- **URL**: `/user/logout`
- **Method**: `POST`

---

## 2. 商品模块 (/api/product)

### 2.1 根据ID查询商品
- **URL**: `/product/{productId}`
- **Method**: `GET`
- **Response Data** (Product对象):
```json
{
  "productId": 1,
  "productName": "华为Mate 60 Pro",
  "categoryId": 1,
  "merchantId": 1,
  "price": 6999.00,
  "originalPrice": 7999.00,
  "stock": 100,
  "sales": 500,
  "description": "商品描述",
  "imageUrl": "/images/huawei-mate60.jpg",
  "status": 1,
  "createTime": "2024-01-01T00:00:00",
  "updateTime": "2024-01-01T00:00:00",
  "categoryName": "手机数码",
  "merchantName": "华为官方旗舰店"
}
```

### 2.2 查询所有商品
- **URL**: `/product/list`
- **Method**: `GET`
- **Response Data**: Product对象数组

### 2.3 查询上架商品
- **URL**: `/product/onsale`
- **Method**: `GET`
- **Response Data**: Product对象数组

### 2.4 根据分类ID查询商品
- **URL**: `/product/category/{categoryId}`
- **Method**: `GET`
- **Response Data**: Product对象数组

### 2.5 搜索商品
- **URL**: `/product/search?keyword=xxx`
- **Method**: `GET`
- **Query Parameter**: `keyword` (搜索关键词)
- **Response Data**: Product对象数组

### 2.6 新增商品
- **URL**: `/product/add`
- **Method**: `POST`
- **Request Body** (Product对象):
```json
{
  "productName": "string",
  "categoryId": 1,
  "merchantId": 1,
  "price": 99.00,
  "originalPrice": 129.00,
  "stock": 100,
  "description": "string",
  "imageUrl": "string",
  "status": 1
}
```

### 2.7 更新商品信息
- **URL**: `/product/update`
- **Method**: `PUT`
- **Request Body**: Product对象（需包含productId）

### 2.8 删除商品
- **URL**: `/product/{productId}`
- **Method**: `DELETE`

### 2.9 更新库存
- **URL**: `/product/stock/{productId}?stock=100`
- **Method**: `PUT`
- **Path Parameter**: `productId`
- **Query Parameter**: `stock` (新库存数量)

---

## 3. 购物车模块 (/api/cart)

### 3.1 添加商品到购物车
- **URL**: `/cart/add`
- **Method**: `POST`
- **Request Body**:
```json
{
  "productId": 1,
  "quantity": 2
}
```
- **注意**: 需要登录状态

### 3.2 获取购物车列表
- **URL**: `/cart/list`
- **Method**: `GET`
- **Response Data** (CartItem对象数组):
```json
[
  {
    "itemId": 1,
    "cartId": 1,
    "productId": 1,
    "quantity": 2,
    "checked": 1,
    "createTime": "2024-01-01T00:00:00",
    "updateTime": "2024-01-01T00:00:00",
    "productName": "华为Mate 60 Pro",
    "productImage": "/images/huawei-mate60.jpg",
    "price": 6999.00,
    "stock": 100,
    "productStatus": 1
  }
]
```
- **注意**: 需要登录状态

### 3.3 更新购物车项数量
- **URL**: `/cart/updateQuantity`
- **Method**: `PUT`
- **Request Body**:
```json
{
  "itemId": 1,
  "quantity": 3
}
```

### 3.4 更新购物车项选中状态
- **URL**: `/cart/updateChecked`
- **Method**: `PUT`
- **Request Body**:
```json
{
  "itemId": 1,
  "checked": 1
}
```
- **checked**: 0-未选中, 1-选中

### 3.5 删除购物车项
- **URL**: `/cart/{itemId}`
- **Method**: `DELETE`

### 3.6 清空购物车
- **URL**: `/cart/clear`
- **Method**: `DELETE`

### 3.7 全选/取消全选
- **URL**: `/cart/checkAll`
- **Method**: `PUT`
- **Request Body**:
```json
{
  "checked": 1
}
```

### 3.8 获取购物车统计信息
- **URL**: `/cart/stats`
- **Method**: `GET`
- **Response Data**:
```json
{
  "totalCount": 5,
  "totalAmount": 9999.00,
  "checkedCount": 3,
  "checkedAmount": 6999.00
}
```

---

## 4. 订单模块 (/api/order)

### 4.1 根据ID查询订单
- **URL**: `/order/{orderId}`
- **Method**: `GET`
- **Response Data** (Orders对象):
```json
{
  "orderId": 1,
  "orderNo": "ORD202401010001",
  "userId": 1,
  "totalAmount": 6999.00,
  "receiverName": "张三",
  "receiverPhone": "13800138000",
  "receiverAddress": "北京市朝阳区xxx",
  "orderStatus": 1,
  "remark": "备注信息",
  "createTime": "2024-01-01T00:00:00",
  "updateTime": "2024-01-01T00:00:00",
  "username": "admin"
}
```

### 4.2 根据用户ID查询订单列表
- **URL**: `/order/user/{userId}`
- **Method**: `GET`
- **Response Data**: Orders对象数组

### 4.3 查询所有订单
- **URL**: `/order/list`
- **Method**: `GET`
- **Response Data**: Orders对象数组

### 4.4 创建订单
- **URL**: `/order/create`
- **Method**: `POST`
- **Request Body** (Orders对象):
```json
{
  "userId": 1,
  "totalAmount": 6999.00,
  "receiverName": "张三",
  "receiverPhone": "13800138000",
  "receiverAddress": "北京市朝阳区xxx",
  "orderStatus": 0,
  "remark": "备注"
}
```
- **Response Data**: 创建的Orders对象

### 4.5 更新订单信息
- **URL**: `/order/update`
- **Method**: `PUT`
- **Request Body**: Orders对象（需包含orderId）

### 4.6 取消订单
- **URL**: `/order/cancel/{orderId}`
- **Method**: `PUT`

### 4.7 删除订单
- **URL**: `/order/{orderId}`
- **Method**: `DELETE`

---

## 5. 分类模块 (/api/category)

### 5.1 查询所有分类
- **URL**: `/category/list`
- **Method**: `GET`
- **Response Data** (Category对象数组):
```json
[
  {
    "categoryId": 1,
    "categoryName": "手机数码",
    "parentId": 0,
    "sortOrder": 1,
    "status": 1,
    "createTime": "2024-01-01T00:00:00"
  }
]
```

### 5.2 查询启用的分类
- **URL**: `/category/enabled`
- **Method**: `GET`
- **Response Data**: Category对象数组

### 5.3 根据ID查询分类
- **URL**: `/category/{categoryId}`
- **Method**: `GET`
- **Response Data**: Category对象

### 5.4 新增分类
- **URL**: `/category/add`
- **Method**: `POST`
- **Request Body** (Category对象):
```json
{
  "categoryName": "string",
  "parentId": 0,
  "sortOrder": 1,
  "status": 1
}
```

### 5.5 更新分类
- **URL**: `/category/update`
- **Method**: `PUT`
- **Request Body**: Category对象（需包含categoryId）

### 5.6 删除分类
- **URL**: `/category/{categoryId}`
- **Method**: `DELETE`

---

## 6. 商家模块 (/api/merchant)

### 6.1 查询所有商家
- **URL**: `/merchant/list`
- **Method**: `GET`
- **Response Data** (Merchant对象数组):
```json
[
  {
    "merchantId": 1,
    "merchantName": "华为官方旗舰店",
    "contactPerson": "李四",
    "contactPhone": "13900139000",
    "address": "深圳市南山区",
    "status": 1,
    "createTime": "2024-01-01T00:00:00",
    "updateTime": "2024-01-01T00:00:00"
  }
]
```

### 6.2 查询活跃的商家
- **URL**: `/merchant/active`
- **Method**: `GET`
- **Response Data**: Merchant对象数组

### 6.3 根据ID查询商家
- **URL**: `/merchant/{merchantId}`
- **Method**: `GET`
- **Response Data**: Merchant对象

### 6.4 新增商家
- **URL**: `/merchant/add`
- **Method**: `POST`
- **Request Body** (Merchant对象):
```json
{
  "merchantName": "string",
  "contactPerson": "string",
  "contactPhone": "string",
  "address": "string",
  "status": 1
}
```

### 6.5 更新商家
- **URL**: `/merchant/update`
- **Method**: `PUT`
- **Request Body**: Merchant对象（需包含merchantId）

### 6.6 删除商家
- **URL**: `/merchant/{merchantId}`
- **Method**: `DELETE`

---

## 7. 支付模块 (/api/payment)

### 7.1 根据ID查询支付记录
- **URL**: `/payment/{paymentId}`
- **Method**: `GET`
- **Response Data** (Payment对象):
```json
{
  "paymentId": 1,
  "orderId": 1,
  "paymentNo": "PAY202401010001",
  "paymentMethod": 1,
  "paymentAmount": 6999.00,
  "paymentStatus": 1,
  "paymentTime": "2024-01-01T00:00:00",
  "createTime": "2024-01-01T00:00:00"
}
```
- **paymentMethod**: 1-支付宝, 2-微信, 3-银行卡
- **paymentStatus**: 0-待支付, 1-支付成功, 2-支付失败

### 7.2 根据订单ID查询支付记录
- **URL**: `/payment/order/{orderId}`
- **Method**: `GET`
- **Response Data**: Payment对象

### 7.3 查询所有支付记录
- **URL**: `/payment/list`
- **Method**: `GET`
- **Response Data**: Payment对象数组

### 7.4 创建支付记录
- **URL**: `/payment/create`
- **Method**: `POST`
- **Request Body** (Payment对象):
```json
{
  "orderId": 1,
  "paymentNo": "PAY202401010001",
  "paymentMethod": 1,
  "paymentAmount": 6999.00,
  "paymentStatus": 0
}
```

### 7.5 更新支付记录
- **URL**: `/payment/update`
- **Method**: `PUT`
- **Request Body**: Payment对象（需包含paymentId）

---

## 8. 物流模块 (/api/logistics)

### 8.1 根据ID查询物流记录
- **URL**: `/logistics/{logisticsId}`
- **Method**: `GET`
- **Response Data** (Logistics对象):
```json
{
  "logisticsId": 1,
  "orderId": 1,
  "logisticsNo": "SF1234567890",
  "logisticsCompany": "顺丰快递",
  "logisticsStatus": 1,
  "shipTime": "2024-01-01T00:00:00",
  "receiveTime": "2024-01-03T00:00:00",
  "createTime": "2024-01-01T00:00:00",
  "updateTime": "2024-01-03T00:00:00"
}
```
- **logisticsStatus**: 0-待发货, 1-已发货, 2-已收货

### 8.2 根据订单ID查询物流记录
- **URL**: `/logistics/order/{orderId}`
- **Method**: `GET`
- **Response Data**: Logistics对象

### 8.3 查询所有物流记录
- **URL**: `/logistics/list`
- **Method**: `GET`
- **Response Data**: Logistics对象数组

### 8.4 创建物流记录
- **URL**: `/logistics/create`
- **Method**: `POST`
- **Request Body** (Logistics对象):
```json
{
  "orderId": 1,
  "logisticsNo": "SF1234567890",
  "logisticsCompany": "顺丰快递",
  "logisticsStatus": 0
}
```

### 8.5 更新物流记录
- **URL**: `/logistics/update`
- **Method**: `PUT`
- **Request Body**: Logistics对象（需包含logisticsId）

---

## 字段说明

### User (用户)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| userId | Long | 用户ID |
| username | String | 用户名 |
| password | String | 密码（MD5加密） |
| realName | String | 真实姓名 |
| phone | String | 手机号 |
| email | String | 邮箱 |
| gender | Integer | 性别：0-女，1-男 |
| address | String | 地址 |
| status | Integer | 状态：0-禁用，1-启用 |
| createTime | LocalDateTime | 创建时间 |
| updateTime | LocalDateTime | 更新时间 |

### Product (商品)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| productId | Long | 商品ID |
| productName | String | 商品名称 |
| categoryId | Integer | 分类ID |
| merchantId | Long | 商家ID |
| price | BigDecimal | 价格 |
| originalPrice | BigDecimal | 原价 |
| stock | Integer | 库存 |
| sales | Integer | 销量 |
| description | String | 描述 |
| imageUrl | String | 图片URL |
| status | Integer | 状态：0-下架，1-上架 |
| categoryName | String | 分类名称（关联查询） |
| merchantName | String | 商家名称（关联查询） |

### CartItem (购物车项)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| itemId | Long | 购物车项ID |
| cartId | Long | 购物车ID |
| productId | Long | 商品ID |
| quantity | Integer | 数量 |
| checked | Integer | 选中状态：0-未选中，1-选中 |
| productName | String | 商品名称（关联查询） |
| productImage | String | 商品图片（关联查询） |
| price | BigDecimal | 商品价格（关联查询） |
| stock | Integer | 商品库存（关联查询） |

### Orders (订单)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| orderId | Long | 订单ID |
| orderNo | String | 订单编号 |
| userId | Long | 用户ID |
| totalAmount | BigDecimal | 总金额 |
| receiverName | String | 收货人姓名 |
| receiverPhone | String | 收货人电话 |
| receiverAddress | String | 收货地址 |
| orderStatus | Integer | 订单状态：0-待支付，1-已支付，2-已发货，3-已完成，4-已取消 |
| remark | String | 备注 |
| username | String | 用户名（关联查询） |

### Category (分类)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| categoryId | Integer | 分类ID |
| categoryName | String | 分类名称 |
| parentId | Integer | 父分类ID |
| sortOrder | Integer | 排序号 |
| status | Integer | 状态：0-禁用，1-启用 |

### Merchant (商家)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| merchantId | Long | 商家ID |
| merchantName | String | 商家名称 |
| contactPerson | String | 联系人 |
| contactPhone | String | 联系电话 |
| address | String | 地址 |
| status | Integer | 状态：0-禁用，1-启用 |

### Payment (支付)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| paymentId | Long | 支付ID |
| orderId | Long | 订单ID |
| paymentNo | String | 支付流水号 |
| paymentMethod | Integer | 支付方式：1-支付宝，2-微信，3-银行卡 |
| paymentAmount | BigDecimal | 支付金额 |
| paymentStatus | Integer | 支付状态：0-待支付，1-支付成功，2-支付失败 |
| paymentTime | LocalDateTime | 支付时间 |

### Logistics (物流)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| logisticsId | Long | 物流ID |
| orderId | Long | 订单ID |
| logisticsNo | String | 物流单号 |
| logisticsCompany | String | 物流公司 |
| logisticsStatus | Integer | 物流状态：0-待发货，1-已发货，2-已收货 |
| shipTime | LocalDateTime | 发货时间 |
| receiveTime | LocalDateTime | 收货时间 |

---

## 测试工具推荐

1. **Postman**: 图形化API测试工具
2. **curl**: 命令行HTTP客户端
3. **浏览器开发者工具**: Network面板可查看前端请求

## 注意事项

1. 所有接口都需要启动后端服务（默认端口8081）
2. 购物车相关接口需要先登录
3. 密码使用MD5加密存储
4. 时间字段格式：`yyyy-MM-ddTHH:mm:ss`
5. 金额字段使用BigDecimal类型，保留两位小数
