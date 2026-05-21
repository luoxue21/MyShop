package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车项实体类
 */
@Data
public class CartItem {
    private Long itemId;
    private Long cartId;
    private Long productId;
    private Integer quantity;
    private Integer checked;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 关联查询字段
    private String productName;
    private String productImage;
    private BigDecimal price;
    private Integer stock;
    private Integer productStatus;
}
