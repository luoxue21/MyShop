package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.Product;

import java.util.List;

/**
 * 商品Service接口
 */
public interface ProductService {
    
    /**
     * 根据ID查询商品
     */
    Product findById(Long productId);
    
    /**
     * 查询所有商品
     */
    List<Product> findAll();
    
    /**
     * 根据分类ID查询商品
     */
    List<Product> findByCategoryId(Integer categoryId);
    
    /**
     * 模糊查询商品
     */
    List<Product> searchByName(String keyword);
    
    /**
     * 查询上架商品
     */
    List<Product> findOnSaleProducts();
    
    /**
     * 新增商品
     */
    boolean addProduct(Product product);
    
    /**
     * 更新商品信息
     */
    boolean updateProduct(Product product);
    
    /**
     * 删除商品
     */
    boolean deleteProduct(Long productId);
    
    /**
     * 更新库存
     */
    boolean updateStock(Long productId, Integer stock);
}
