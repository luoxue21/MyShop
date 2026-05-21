package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.Payment;

import java.util.List;

/**
 * 支付Service接口
 */
public interface PaymentService {
    
    Payment findById(Long paymentId);
    
    Payment findByOrderId(Long orderId);
    
    List<Payment> findAll();
    
    boolean createPayment(Payment payment);
    
    boolean updatePayment(Payment payment);
}
