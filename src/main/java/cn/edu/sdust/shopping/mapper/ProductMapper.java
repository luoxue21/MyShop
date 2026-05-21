package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Mapper接口
 */
@Mapper
public interface ProductMapper {
    
    /**
     * 根据ID查询商品
     */
    Product findById(@Param("productId") Long productId);
    
    /**
     * 查询所有商品
     */
    List<Product> findAll();
    
    /**
     * 根据分类ID查询商品
     */
    List<Product> findByCategoryId(@Param("categoryId") Integer categoryId);
    
    /**
     * 模糊查询商品
     */
    List<Product> searchByName(@Param("keyword") String keyword);
    
    /**
     * 查询上架商品
     */
    List<Product> findOnSaleProducts();
    
    /**
     * 新增商品
     */
    int insert(Product product);
    
    /**
     * 更新商品信息
     */
    int update(Product product);
    
    /**
     * 删除商品
     */
    int deleteById(@Param("productId") Long productId);
    
    /**
     * 更新库存
     */
    int updateStock(@Param("productId") Long productId, @Param("stock") Integer stock);
}
