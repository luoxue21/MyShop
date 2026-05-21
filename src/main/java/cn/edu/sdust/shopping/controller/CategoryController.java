package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.Category;
import cn.edu.sdust.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类Controller
 */
@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/list")
    public Result findAll() {
        List<Category> categories = categoryService.findAll();
        return Result.success(categories);
    }
    
    @GetMapping("/enabled")
    public Result findEnabledCategories() {
        List<Category> categories = categoryService.findEnabledCategories();
        return Result.success(categories);
    }
    
    @GetMapping("/{categoryId}")
    public Result findById(@PathVariable Integer categoryId) {
        Category category = categoryService.findById(categoryId);
        if (category != null) {
            return Result.success(category);
        }
        return Result.error("分类不存在");
    }
    
    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category) {
        boolean success = categoryService.addCategory(category);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }
    
    @PutMapping("/update")
    public Result updateCategory(@RequestBody Category category) {
        boolean success = categoryService.updateCategory(category);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
    
    @DeleteMapping("/{categoryId}")
    public Result deleteCategory(@PathVariable Integer categoryId) {
        boolean success = categoryService.deleteCategory(categoryId);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
