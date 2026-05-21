package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单明细Mapper接口
 */
@Mapper
public interface OrderItemMapper {
    
    /**
     * 根据订单ID查询明细
     */
    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 新增订单明细
     */
    int insert(OrderItem orderItem);
    
    /**
     * 批量新增订单明细
     */
    int batchInsert(List<OrderItem> orderItems);
}
