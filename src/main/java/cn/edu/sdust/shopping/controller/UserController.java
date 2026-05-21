package cn.edu.sdust.shopping.controller;

import cn.edu.sdust.shopping.common.Result;
import cn.edu.sdust.shopping.entity.User;
import cn.edu.sdust.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            session.setAttribute("currentUser", loginUser);
            return Result.success("登录成功", loginUser);
        }
        return Result.error("用户名或密码错误");
    }
    
    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        boolean success = userService.register(user);
        if (success) {
            return Result.success("注册成功");
        }
        return Result.error("用户名已存在");
    }
    
    /**
     * 获取当前用户信息
     */
    @GetMapping("/current")
    public Result getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            return Result.success(user);
        }
        return Result.error(401, "未登录");
    }
    
    /**
     * 根据ID查询用户
     */
    @GetMapping("/{userId}")
    public Result findById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }
    
    /**
     * 查询所有用户
     */
    @GetMapping("/list")
    public Result findAll() {
        List<User> users = userService.findAll();
        return Result.success(users);
    }
    
    /**
     * 更新用户信息
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        boolean success = userService.update(user);
        if (success) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }
    
    /**
     * 删除用户
     */
    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable Long userId) {
        boolean success = userService.deleteById(userId);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
    
    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("currentUser");
        return Result.success("退出成功");
    }
}
