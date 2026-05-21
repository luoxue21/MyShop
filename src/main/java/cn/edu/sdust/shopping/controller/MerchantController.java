package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.Merchant;
import cn.edu.sdust.shopping.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家Controller
 */
@RestController
@RequestMapping("/api/merchant")
@CrossOrigin
public class MerchantController {
    
    @Autowired
    private MerchantService merchantService;
    
    @GetMapping("/list")
    public Result findAll() {
        List<Merchant> merchants = merchantService.findAll();
        return Result.success(merchants);
    }
    
    @GetMapping("/active")
    public Result findActiveMerchants() {
        List<Merchant> merchants = merchantService.findActiveMerchants();
        return Result.success(merchants);
    }
    
    @GetMapping("/{merchantId}")
    public Result findById(@PathVariable Long merchantId) {
        Merchant merchant = merchantService.findById(merchantId);
        if (merchant != null) {
            return Result.success(merchant);
        }
        return Result.error("商家不存在");
    }
    
    @PostMapping("/add")
    public Result addMerchant(@RequestBody Merchant merchant) {
        boolean success = merchantService.addMerchant(merchant);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }
    
    @PutMapping("/update")
    public Result updateMerchant(@RequestBody Merchant merchant) {
        boolean success = merchantService.updateMerchant(merchant);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
    
    @DeleteMapping("/{merchantId}")
    public Result deleteMerchant(@PathVariable Long merchantId) {
        boolean success = merchantService.deleteMerchant(merchantId);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
