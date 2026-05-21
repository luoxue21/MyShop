package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.Category;
import cn.edu.sdust.shopping.mapper.CategoryMapper;
import cn.edu.sdust.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类Service实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
    
    @Override
    public List<Category> findEnabledCategories() {
        return categoryMapper.findEnabledCategories();
    }
    
    @Override
    public Category findById(Integer categoryId) {
        return categoryMapper.findById(categoryId);
    }
    
    @Override
    public boolean addCategory(Category category) {
        if (category.getStatus() == null) {
            category.setStatus(1);
        }
        return categoryMapper.insert(category) > 0;
    }
    
    @Override
    public boolean updateCategory(Category category) {
        return categoryMapper.update(category) > 0;
    }
    
    @Override
    public boolean deleteCategory(Integer categoryId) {
        return categoryMapper.deleteById(categoryId) > 0;
    }
}
