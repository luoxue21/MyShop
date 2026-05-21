package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.Category;

import java.util.List;

/**
 * 分类Service接口
 */
public interface CategoryService {
    
    List<Category> findAll();
    
    List<Category> findEnabledCategories();
    
    Category findById(Integer categoryId);
    
    boolean addCategory(Category category);
    
    boolean updateCategory(Category category);
    
    boolean deleteCategory(Integer categoryId);
}
