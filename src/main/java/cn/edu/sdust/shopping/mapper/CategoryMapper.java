package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类Mapper接口
 */
@Mapper
public interface CategoryMapper {
    
    /**
     * 根据ID查询分类
     */
    Category findById(@Param("categoryId") Integer categoryId);
    
    /**
     * 查询所有分类
     */
    List<Category> findAll();
    
    /**
     * 查询启用的分类
     */
    List<Category> findEnabledCategories();
    
    /**
     * 新增分类
     */
    int insert(Category category);
    
    /**
     * 更新分类
     */
    int update(Category category);
    
    /**
     * 删除分类
     */
    int deleteById(@Param("categoryId") Integer categoryId);
}
