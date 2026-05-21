package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车Mapper接口
 */
@Mapper
public interface CartMapper {
    
    /**
     * 根据用户ID查询购物车
     */
    Cart findByUserId(@Param("userId") Long userId);
    
    /**
     * 创建购物车
     */
    int insert(Cart cart);
    
    /**
     * 删除购物车
     */
    int deleteById(@Param("cartId") Long cartId);
}
