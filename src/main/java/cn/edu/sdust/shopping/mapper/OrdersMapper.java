package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper接口
 */
@Mapper
public interface OrdersMapper {
    
    /**
     * 根据ID查询订单
     */
    Orders findById(@Param("orderId") Long orderId);
    
    /**
     * 根据订单号查询订单
     */
    Orders findByOrderNo(@Param("orderNo") String orderNo);
    
    /**
     * 根据用户ID查询订单列表
     */
    List<Orders> findByUserId(@Param("userId") Long userId);
    
    /**
     * 查询所有订单
     */
    List<Orders> findAll();
    
    /**
     * 新增订单
     */
    int insert(Orders orders);
    
    /**
     * 更新订单信息
     */
    int update(Orders orders);
    
    /**
     * 删除订单
     */
    int deleteById(@Param("orderId") Long orderId);
}
