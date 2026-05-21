package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户信息实体类
 */
@Data
public class User {
    private Long userId;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private Integer gender;
    private String address;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
