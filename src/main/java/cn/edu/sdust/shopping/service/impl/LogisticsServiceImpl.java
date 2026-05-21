package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.Logistics;
import cn.edu.sdust.shopping.mapper.LogisticsMapper;
import cn.edu.sdust.shopping.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流Service实现类
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {
    
    @Autowired
    private LogisticsMapper logisticsMapper;
    
    @Override
    public Logistics findById(Long logisticsId) {
        return logisticsMapper.findById(logisticsId);
    }
    
    @Override
    public Logistics findByOrderId(Long orderId) {
        return logisticsMapper.findByOrderId(orderId);
    }
    
    @Override
    public List<Logistics> findAll() {
        return logisticsMapper.findAll();
    }
    
    @Override
    public boolean createLogistics(Logistics logistics) {
        return logisticsMapper.insert(logistics) > 0;
    }
    
    @Override
    public boolean updateLogistics(Logistics logistics) {
        return logisticsMapper.update(logistics) > 0;
    }
}
