package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商家信息实体类
 */
@Data
public class Merchant {
    private Long merchantId;
    private String merchantName;
    private String contactPerson;
    private String contactPhone;
    private String address;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
