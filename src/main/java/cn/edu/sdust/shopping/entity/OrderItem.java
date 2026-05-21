package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 订单明细实体类
 */
@Data
public class OrderItem {
    private Long itemId;
    private Long orderId;
    private Long productId;
    private String productName;
    private String productImage;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
}
