package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.User;

import java.util.List;

/**
 * 用户Service接口
 */
public interface UserService {
    
    /**
     * 用户登录
     */
    User login(String username, String password);
    
    /**
     * 用户注册
     */
    boolean register(User user);
    
    /**
     * 根据ID查询用户
     */
    User findById(Long userId);
    
    /**
     * 查询所有用户
     */
    List<User> findAll();
    
    /**
     * 更新用户信息
     */
    boolean update(User user);
    
    /**
     * 删除用户
     */
    boolean deleteById(Long userId);
}
