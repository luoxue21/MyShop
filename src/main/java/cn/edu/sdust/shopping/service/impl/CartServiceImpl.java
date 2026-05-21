package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.Cart;
import cn.edu.sdust.shopping.entity.CartItem;
import cn.edu.sdust.shopping.mapper.CartItemMapper;
import cn.edu.sdust.shopping.mapper.CartMapper;
import cn.edu.sdust.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车Service实现类
 */
@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartMapper cartMapper;
    
    @Autowired
    private CartItemMapper cartItemMapper;
    
    /**
     * 获取或创建用户购物车
     */
    private Cart getOrCreateCart(Long userId) {
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartMapper.insert(cart);
        }
        return cart;
    }
    
    @Override
    @Transactional
    public boolean addToCart(Long userId, Long productId, Integer quantity) {
        if (quantity <= 0) {
            return false;
        }
        
        Cart cart = getOrCreateCart(userId);
        
        // 检查商品是否已在购物车中
        CartItem existItem = cartItemMapper.findByCartIdAndProductId(cart.getCartId(), productId);
        
        if (existItem != null) {
            // 如果已存在，增加数量
            existItem.setQuantity(existItem.getQuantity() + quantity);
            return cartItemMapper.updateQuantity(existItem) > 0;
        } else {
            // 如果不存在，新增购物车项
            CartItem newItem = new CartItem();
            newItem.setCartId(cart.getCartId());
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            newItem.setChecked(1);
            return cartItemMapper.insert(newItem) > 0;
        }
    }
    
    @Override
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null) {
            return List.of();
        }
        return cartItemMapper.findByCartId(cart.getCartId());
    }
    
    @Override
    @Transactional
    public boolean updateQuantity(Long userId, Long itemId, Integer quantity) {
        if (quantity <= 0) {
            return false;
        }
        
        CartItem item = cartItemMapper.findById(itemId);
        if (item == null) {
            return false;
        }
        
        // 验证购物车归属
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null || !cart.getCartId().equals(item.getCartId())) {
            return false;
        }
        
        item.setQuantity(quantity);
        return cartItemMapper.updateQuantity(item) > 0;
    }
    
    @Override
    @Transactional
    public boolean updateChecked(Long userId, Long itemId, Integer checked) {
        CartItem item = cartItemMapper.findById(itemId);
        if (item == null) {
            return false;
        }
        
        // 验证购物车归属
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null || !cart.getCartId().equals(item.getCartId())) {
            return false;
        }
        
        item.setChecked(checked);
        return cartItemMapper.updateChecked(item) > 0;
    }
    
    @Override
    @Transactional
    public boolean removeCartItem(Long userId, Long itemId) {
        CartItem item = cartItemMapper.findById(itemId);
        if (item == null) {
            return false;
        }
        
        // 验证购物车归属
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null || !cart.getCartId().equals(item.getCartId())) {
            return false;
        }
        
        return cartItemMapper.deleteById(itemId) > 0;
    }
    
    @Override
    @Transactional
    public boolean clearCart(Long userId) {
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null) {
            return false;
        }
        
        return cartItemMapper.deleteByCartId(cart.getCartId()) > 0;
    }
    
    @Override
    @Transactional
    public boolean checkAll(Long userId, Integer checked) {
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null) {
            return false;
        }
        
        List<CartItem> items = cartItemMapper.findByCartId(cart.getCartId());
        for (CartItem item : items) {
            item.setChecked(checked);
            cartItemMapper.updateChecked(item);
        }
        
        return true;
    }
    
    @Override
    public Map<String, Object> getCartStats(Long userId) {
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null) {
            Map<String, Object> stats = new HashMap<>();
            stats.put("totalCount", 0);
            stats.put("totalAmount", BigDecimal.ZERO);
            stats.put("checkedCount", 0);
            stats.put("checkedAmount", BigDecimal.ZERO);
            return stats;
        }
        
        List<CartItem> items = cartItemMapper.findByCartId(cart.getCartId());
        
        int totalCount = 0;
        BigDecimal totalAmount = BigDecimal.ZERO;
        int checkedCount = 0;
        BigDecimal checkedAmount = BigDecimal.ZERO;
        
        for (CartItem item : items) {
            if (item.getProductStatus() != null && item.getProductStatus() == 1) {
                totalCount += item.getQuantity();
                BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
                totalAmount = totalAmount.add(itemTotal);
                
                if (item.getChecked() != null && item.getChecked() == 1) {
                    checkedCount += item.getQuantity();
                    checkedAmount = checkedAmount.add(itemTotal);
                }
            }
        }
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCount", totalCount);
        stats.put("totalAmount", totalAmount);
        stats.put("checkedCount", checkedCount);
        stats.put("checkedAmount", checkedAmount);
        
        return stats;
    }
}
