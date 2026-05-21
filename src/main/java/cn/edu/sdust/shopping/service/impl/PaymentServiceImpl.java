package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.Payment;
import cn.edu.sdust.shopping.mapper.PaymentMapper;
import cn.edu.sdust.shopping.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * 支付Service实现类
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    
    @Autowired
    private PaymentMapper paymentMapper;
    
    @Override
    public Payment findById(Long paymentId) {
        return paymentMapper.findById(paymentId);
    }
    
    @Override
    public Payment findByOrderId(Long orderId) {
        return paymentMapper.findByOrderId(orderId);
    }
    
    @Override
    public List<Payment> findAll() {
        return paymentMapper.findAll();
    }
    
    @Override
    public boolean createPayment(Payment payment) {
        // 生成支付流水号
        String paymentNo = generatePaymentNo();
        payment.setPaymentNo(paymentNo);
        payment.setCreateTime(LocalDateTime.now());
        return paymentMapper.insert(payment) > 0;
    }
    
    @Override
    public boolean updatePayment(Payment payment) {
        return paymentMapper.update(payment) > 0;
    }
    
    private String generatePaymentNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        Random random = new Random();
        int randomNum = random.nextInt(9000) + 1000;
        return "PAY" + timestamp + randomNum;
    }
}
