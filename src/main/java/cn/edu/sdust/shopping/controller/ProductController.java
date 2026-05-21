package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.Product;
import cn.edu.sdust.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    /**
     * 根据ID查询商品
     */
    @GetMapping("/{productId}")
    public Result findById(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        if (product != null) {
            return Result.success(product);
        }
        return Result.error("商品不存在");
    }
    
    /**
     * 查询所有商品
     */
    @GetMapping("/list")
    public Result findAll() {
        List<Product> products = productService.findAll();
        return Result.success(products);
    }
    
    /**
     * 查询上架商品
     */
    @GetMapping("/onsale")
    public Result findOnSaleProducts() {
        List<Product> products = productService.findOnSaleProducts();
        return Result.success(products);
    }
    
    /**
     * 根据分类ID查询商品
     */
    @GetMapping("/category/{categoryId}")
    public Result findByCategoryId(@PathVariable Integer categoryId) {
        List<Product> products = productService.findByCategoryId(categoryId);
        return Result.success(products);
    }
    
    /**
     * 搜索商品
     */
    @GetMapping("/search")
    public Result searchByName(@RequestParam String keyword) {
        List<Product> products = productService.searchByName(keyword);
        return Result.success(products);
    }
    
    /**
     * 新增商品
     */
    @PostMapping("/add")
    public Result addProduct(@RequestBody Product product) {
        boolean success = productService.addProduct(product);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }
    
    /**
     * 更新商品信息
     */
    @PutMapping("/update")
    public Result updateProduct(@RequestBody Product product) {
        boolean success = productService.updateProduct(product);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
    
    /**
     * 删除商品
     */
    @DeleteMapping("/{productId}")
    public Result deleteProduct(@PathVariable Long productId) {
        boolean success = productService.deleteProduct(productId);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
    
    /**
     * 更新库存
     */
    @PutMapping("/stock/{productId}")
    public Result updateStock(@PathVariable Long productId, @RequestParam Integer stock) {
        boolean success = productService.updateStock(productId, stock);
        if (success) {
            return Result.success("库存更新成功");
        }
        return Result.error("库存更新失败");
    }
}
