package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.CartItem;
import cn.edu.sdust.shopping.entity.User;
import cn.edu.sdust.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 购物车Controller
 */
@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    /**
     * 获取当前登录用户ID
     */
    private Long getCurrentUserId(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }
    
    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public Result addToCart(@RequestBody Map<String, Object> params, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        Long productId = Long.valueOf(params.get("productId").toString());
        Integer quantity = params.get("quantity") != null ? 
            Integer.valueOf(params.get("quantity").toString()) : 1;
        
        boolean success = cartService.addToCart(userId, productId, quantity);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }
    
    /**
     * 获取购物车列表
     */
    @GetMapping("/list")
    public Result getCartList(HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        List<CartItem> items = cartService.getCartItems(userId);
        return Result.success(items);
    }
    
    /**
     * 更新购物车项数量
     */
    @PutMapping("/updateQuantity")
    public Result updateQuantity(@RequestBody Map<String, Object> params, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        Long itemId = Long.valueOf(params.get("itemId").toString());
        Integer quantity = Integer.valueOf(params.get("quantity").toString());
        
        boolean success = cartService.updateQuantity(userId, itemId, quantity);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
    
    /**
     * 更新购物车项选中状态
     */
    @PutMapping("/updateChecked")
    public Result updateChecked(@RequestBody Map<String, Object> params, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        Long itemId = Long.valueOf(params.get("itemId").toString());
        Integer checked = Integer.valueOf(params.get("checked").toString());
        
        boolean success = cartService.updateChecked(userId, itemId, checked);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
    
    /**
     * 删除购物车项
     */
    @DeleteMapping("/{itemId}")
    public Result removeCartItem(@PathVariable Long itemId, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        boolean success = cartService.removeCartItem(userId, itemId);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
    
    /**
     * 清空购物车
     */
    @DeleteMapping("/clear")
    public Result clearCart(HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        boolean success = cartService.clearCart(userId);
        if (success) {
            return Result.success("清空成功");
        }
        return Result.error("清空失败");
    }
    
    /**
     * 全选/取消全选
     */
    @PutMapping("/checkAll")
    public Result checkAll(@RequestBody Map<String, Object> params, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        Integer checked = Integer.valueOf(params.get("checked").toString());
        
        boolean success = cartService.checkAll(userId, checked);
        if (success) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
    
    /**
     * 获取购物车统计信息
     */
    @GetMapping("/stats")
    public Result getCartStats(HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return Result.error(401, "请先登录");
        }
        
        Map<String, Object> stats = cartService.getCartStats(userId);
        return Result.success(stats);
    }
}
