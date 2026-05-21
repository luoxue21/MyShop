package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.Logistics;

import java.util.List;

/**
 * 物流Service接口
 */
public interface LogisticsService {
    
    Logistics findById(Long logisticsId);
    
    Logistics findByOrderId(Long orderId);
    
    List<Logistics> findAll();
    
    boolean createLogistics(Logistics logistics);
    
    boolean updateLogistics(Logistics logistics);
}
