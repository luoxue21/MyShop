package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付记录实体类
 */
@Data
public class Payment {
    private Long paymentId;
    private Long orderId;
    private String paymentNo;
    private Integer paymentMethod;
    private BigDecimal paymentAmount;
    private Integer paymentStatus;
    private LocalDateTime paymentTime;
    private LocalDateTime createTime;
}
