package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.Orders;

import java.util.List;

/**
 * 订单Service接口
 */
public interface OrdersService {
    
    /**
     * 根据ID查询订单
     */
    Orders findById(Long orderId);
    
    /**
     * 根据订单号查询订单
     */
    Orders findByOrderNo(String orderNo);
    
    /**
     * 根据用户ID查询订单列表
     */
    List<Orders> findByUserId(Long userId);
    
    /**
     * 查询所有订单
     */
    List<Orders> findAll();
    
    /**
     * 创建订单
     */
    Orders createOrder(Orders orders);
    
    /**
     * 更新订单信息
     */
    boolean updateOrder(Orders orders);
    
    /**
     * 取消订单
     */
    boolean cancelOrder(Long orderId);
    
    /**
     * 删除订单
     */
    boolean deleteOrder(Long orderId);
}
