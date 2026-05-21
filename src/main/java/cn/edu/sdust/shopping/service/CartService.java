package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.CartItem;

import java.util.List;
import java.util.Map;

/**
 * 购物车Service接口
 */
public interface CartService {
    
    /**
     * 添加商品到购物车
     */
    boolean addToCart(Long userId, Long productId, Integer quantity);
    
    /**
     * 获取用户购物车列表
     */
    List<CartItem> getCartItems(Long userId);
    
    /**
     * 更新购物车项数量
     */
    boolean updateQuantity(Long userId, Long itemId, Integer quantity);
    
    /**
     * 更新购物车项选中状态
     */
    boolean updateChecked(Long userId, Long itemId, Integer checked);
    
    /**
     * 删除购物车项
     */
    boolean removeCartItem(Long userId, Long itemId);
    
    /**
     * 清空购物车
     */
    boolean clearCart(Long userId);
    
    /**
     * 全选/取消全选
     */
    boolean checkAll(Long userId, Integer checked);
    
    /**
     * 获取购物车统计信息
     */
    Map<String, Object> getCartStats(Long userId);
}
