package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.OrderItem;
import cn.edu.sdust.shopping.entity.Orders;
import cn.edu.sdust.shopping.mapper.OrderItemMapper;
import cn.edu.sdust.shopping.mapper.OrdersMapper;
import cn.edu.sdust.shopping.mapper.ProductMapper;
import cn.edu.sdust.shopping.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * 订单Service实现类
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    
    @Autowired
    private OrdersMapper ordersMapper;
    
    @Autowired
    private OrderItemMapper orderItemMapper;
    
    @Autowired
    private ProductMapper productMapper;
    
    @Override
    public Orders findById(Long orderId) {
        return ordersMapper.findById(orderId);
    }
    
    @Override
    public Orders findByOrderNo(String orderNo) {
        return ordersMapper.findByOrderNo(orderNo);
    }
    
    @Override
    public List<Orders> findByUserId(Long userId) {
        return ordersMapper.findByUserId(userId);
    }
    
    @Override
    public List<Orders> findAll() {
        return ordersMapper.findAll();
    }
    
    @Override
    @Transactional
    public Orders createOrder(Orders orders) {
        // 生成订单号
        String orderNo = generateOrderNo();
        orders.setOrderNo(orderNo);
        orders.setOrderStatus(0); // 待支付
        orders.setCreateTime(LocalDateTime.now());
        
        // 插入订单
        ordersMapper.insert(orders);
        
        return orders;
    }
    
    @Override
    public boolean updateOrder(Orders orders) {
        return ordersMapper.update(orders) > 0;
    }
    
    @Override
    @Transactional
    public boolean cancelOrder(Long orderId) {
        Orders orders = ordersMapper.findById(orderId);
        if (orders != null && orders.getOrderStatus() == 0) {
            orders.setOrderStatus(4); // 已取消
            return ordersMapper.update(orders) > 0;
        }
        return false;
    }
    
    @Override
    @Transactional
    public boolean deleteOrder(Long orderId) {
        // 先删除订单明细
        List<OrderItem> items = orderItemMapper.findByOrderId(orderId);
        if (items != null && !items.isEmpty()) {
            // 恢复库存
            for (OrderItem item : items) {
                cn.edu.sdust.shopping.entity.Product product = productMapper.findById(item.getProductId());
                if (product != null) {
                    productMapper.updateStock(item.getProductId(), product.getStock() + item.getQuantity());
                }
            }
        }
        return ordersMapper.deleteById(orderId) > 0;
    }
    
    /**
     * 生成订单号
     */
    private String generateOrderNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        Random random = new Random();
        int randomNum = random.nextInt(9000) + 1000;
        return "ORD" + timestamp + randomNum;
    }
}
