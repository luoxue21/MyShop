package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车项Mapper接口
 */
@Mapper
public interface CartItemMapper {
    
    /**
     * 根据购物车ID查询所有购物车项（包含商品信息）
     */
    List<CartItem> findByCartId(@Param("cartId") Long cartId);
    
    /**
     * 根据购物车ID和商品ID查询购物车项
     */
    CartItem findByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);
    
    /**
     * 根据购物车项ID查询
     */
    CartItem findById(@Param("itemId") Long itemId);
    
    /**
     * 插入购物车项
     */
    int insert(CartItem cartItem);
    
    /**
     * 更新购物车项数量
     */
    int updateQuantity(CartItem cartItem);
    
    /**
     * 更新购物车项选中状态
     */
    int updateChecked(CartItem cartItem);
    
    /**
     * 删除购物车项
     */
    int deleteById(@Param("itemId") Long itemId);
    
    /**
     * 根据购物车ID删除所有购物车项
     */
    int deleteByCartId(@Param("cartId") Long cartId);
    
    /**
     * 统计购物车项数量
     */
    int countByCartId(@Param("cartId") Long cartId);
}
