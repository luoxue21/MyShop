package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.Product;
import cn.edu.sdust.shopping.mapper.ProductMapper;
import cn.edu.sdust.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品Service实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductMapper productMapper;
    
    @Override
    public Product findById(Long productId) {
        return productMapper.findById(productId);
    }
    
    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }
    
    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        return productMapper.findByCategoryId(categoryId);
    }
    
    @Override
    public List<Product> searchByName(String keyword) {
        return productMapper.searchByName(keyword);
    }
    
    @Override
    public List<Product> findOnSaleProducts() {
        return productMapper.findOnSaleProducts();
    }
    
    @Override
    public boolean addProduct(Product product) {
        if (product.getStatus() == null) {
            product.setStatus(1);
        }
        if (product.getSales() == null) {
            product.setSales(0);
        }
        return productMapper.insert(product) > 0;
    }
    
    @Override
    public boolean updateProduct(Product product) {
        return productMapper.update(product) > 0;
    }
    
    @Override
    public boolean deleteProduct(Long productId) {
        return productMapper.deleteById(productId) > 0;
    }
    
    @Override
    public boolean updateStock(Long productId, Integer stock) {
        return productMapper.updateStock(productId, stock) > 0;
    }
}
