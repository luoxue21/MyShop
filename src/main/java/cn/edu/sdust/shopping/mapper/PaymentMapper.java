package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 支付Mapper接口
 */
@Mapper
public interface PaymentMapper {
    
    /**
     * 根据ID查询支付记录
     */
    Payment findById(@Param("paymentId") Long paymentId);
    
    /**
     * 根据订单ID查询支付记录
     */
    Payment findByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 查询所有支付记录
     */
    List<Payment> findAll();
    
    /**
     * 新增支付记录
     */
    int insert(Payment payment);
    
    /**
     * 更新支付记录
     */
    int update(Payment payment);
}
