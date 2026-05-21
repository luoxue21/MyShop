package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 购物车实体类
 */
@Data
public class Cart {
    private Long cartId;
    private Long userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
