package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.Payment;
import cn.edu.sdust.shopping.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 支付Controller
 */
@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @GetMapping("/{paymentId}")
    public Result findById(@PathVariable Long paymentId) {
        Payment payment = paymentService.findById(paymentId);
        if (payment != null) {
            return Result.success(payment);
        }
        return Result.error("支付记录不存在");
    }
    
    @GetMapping("/order/{orderId}")
    public Result findByOrderId(@PathVariable Long orderId) {
        Payment payment = paymentService.findByOrderId(orderId);
        if (payment != null) {
            return Result.success(payment);
        }
        return Result.error("支付记录不存在");
    }
    
    @GetMapping("/list")
    public Result findAll() {
        List<Payment> payments = paymentService.findAll();
        return Result.success(payments);
    }
    
    @PostMapping("/create")
    public Result createPayment(@RequestBody Payment payment) {
        boolean success = paymentService.createPayment(payment);
        if (success) {
            return Result.success("支付记录创建成功");
        }
        return Result.error("创建失败");
    }
    
    @PutMapping("/update")
    public Result updatePayment(@RequestBody Payment payment) {
        boolean success = paymentService.updatePayment(payment);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
}
