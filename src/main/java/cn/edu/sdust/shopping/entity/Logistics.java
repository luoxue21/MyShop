package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 物流记录实体类
 */
@Data
public class Logistics {
    private Long logisticsId;
    private Long orderId;
    private String logisticsNo;
    private String logisticsCompany;
    private Integer logisticsStatus;
    private LocalDateTime shipTime;
    private LocalDateTime receiveTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
