package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.Orders;
import cn.edu.sdust.shopping.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrdersController {
    
    @Autowired
    private OrdersService ordersService;
    
    /**
     * 根据ID查询订单
     */
    @GetMapping("/{orderId}")
    public Result findById(@PathVariable Long orderId) {
        Orders orders = ordersService.findById(orderId);
        if (orders != null) {
            return Result.success(orders);
        }
        return Result.error("订单不存在");
    }
    
    /**
     * 根据用户ID查询订单列表
     */
    @GetMapping("/user/{userId}")
    public Result findByUserId(@PathVariable Long userId) {
        List<Orders> orders = ordersService.findByUserId(userId);
        return Result.success(orders);
    }
    
    /**
     * 查询所有订单
     */
    @GetMapping("/list")
    public Result findAll() {
        List<Orders> orders = ordersService.findAll();
        return Result.success(orders);
    }
    
    /**
     * 创建订单
     */
    @PostMapping("/create")
    public Result createOrder(@RequestBody Orders orders) {
        Orders newOrder = ordersService.createOrder(orders);
        if (newOrder != null) {
            return Result.success("订单创建成功", newOrder);
        }
        return Result.error("订单创建失败");
    }
    
    /**
     * 更新订单信息
     */
    @PutMapping("/update")
    public Result updateOrder(@RequestBody Orders orders) {
        boolean success = ordersService.updateOrder(orders);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
    
    /**
     * 取消订单
     */
    @PutMapping("/cancel/{orderId}")
    public Result cancelOrder(@PathVariable Long orderId) {
        boolean success = ordersService.cancelOrder(orderId);
        if (success) {
            return Result.success("订单已取消");
        }
        return Result.error("取消失败");
    }
    
    /**
     * 删除订单
     */
    @DeleteMapping("/{orderId}")
    public Result deleteOrder(@PathVariable Long orderId) {
        boolean success = ordersService.deleteOrder(orderId);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
