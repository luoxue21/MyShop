package cn.edu.sdust.shopping.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品分类实体类
 */
@Data
public class Category {
    private Integer categoryId;
    private String categoryName;
    private Integer parentId;
    private Integer sortOrder;
    private Integer status;
    private LocalDateTime createTime;
}
