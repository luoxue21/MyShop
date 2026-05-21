package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.User;
import cn.edu.sdust.shopping.mapper.UserMapper;
import cn.edu.sdust.shopping.service.UserService;
import cn.edu.sdust.shopping.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && MD5Util.verifyPassword(password, user.getPassword())) {
            if (user.getStatus() == 1) {
                user.setPassword(null); // 不返回密码
                return user;
            }
        }
        return null;
    }
    
    @Override
    public boolean register(User user) {
        // 检查用户名是否已存在
        User existUser = userMapper.findByUsername(user.getUsername());
        if (existUser != null) {
            return false;
        }
        // 密码MD5加密
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setStatus(1);
        return userMapper.insert(user) > 0;
    }
    
    @Override
    public User findById(Long userId) {
        User user = userMapper.findById(userId);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }
    
    @Override
    public List<User> findAll() {
        List<User> users = userMapper.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }
    
    @Override
    public boolean update(User user) {
        return userMapper.update(user) > 0;
    }
    
    @Override
    public boolean deleteById(Long userId) {
        return userMapper.deleteById(userId) > 0;
    }
}
