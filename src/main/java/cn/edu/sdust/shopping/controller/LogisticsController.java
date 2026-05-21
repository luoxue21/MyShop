package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.Logistics;
import cn.edu.sdust.shopping.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物流Controller
 */
@RestController
@RequestMapping("/api/logistics")
@CrossOrigin
public class LogisticsController {
    
    @Autowired
    private LogisticsService logisticsService;
    
    @GetMapping("/{logisticsId}")
    public Result findById(@PathVariable Long logisticsId) {
        Logistics logistics = logisticsService.findById(logisticsId);
        if (logistics != null) {
            return Result.success(logistics);
        }
        return Result.error("物流记录不存在");
    }
    
    @GetMapping("/order/{orderId}")
    public Result findByOrderId(@PathVariable Long orderId) {
        Logistics logistics = logisticsService.findByOrderId(orderId);
        if (logistics != null) {
            return Result.success(logistics);
        }
        return Result.error("物流记录不存在");
    }
    
    @GetMapping("/list")
    public Result findAll() {
        List<Logistics> logisticsList = logisticsService.findAll();
        return Result.success(logisticsList);
    }
    
    @PostMapping("/create")
    public Result createLogistics(@RequestBody Logistics logistics) {
        boolean success = logisticsService.createLogistics(logistics);
        if (success) {
            return Result.success("物流记录创建成功");
        }
        return Result.error("创建失败");
    }
    
    @PutMapping("/update")
    public Result updateLogistics(@RequestBody Logistics logistics) {
        boolean success = logisticsService.updateLogistics(logistics);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
}
