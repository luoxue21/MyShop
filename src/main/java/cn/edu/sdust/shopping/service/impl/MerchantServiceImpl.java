package cn.edu.sdust.shopping.service.impl;

import cn.edu.sdust.shopping.entity.Merchant;
import cn.edu.sdust.shopping.mapper.MerchantMapper;
import cn.edu.sdust.shopping.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家Service实现类
 */
@Service
public class MerchantServiceImpl implements MerchantService {
    
    @Autowired
    private MerchantMapper merchantMapper;
    
    @Override
    public List<Merchant> findAll() {
        return merchantMapper.findAll();
    }
    
    @Override
    public List<Merchant> findActiveMerchants() {
        return merchantMapper.findActiveMerchants();
    }
    
    @Override
    public Merchant findById(Long merchantId) {
        return merchantMapper.findById(merchantId);
    }
    
    @Override
    public boolean addMerchant(Merchant merchant) {
        if (merchant.getStatus() == null) {
            merchant.setStatus(1);
        }
        return merchantMapper.insert(merchant) > 0;
    }
    
    @Override
    public boolean updateMerchant(Merchant merchant) {
        return merchantMapper.update(merchant) > 0;
    }
    
    @Override
    public boolean deleteMerchant(Long merchantId) {
        return merchantMapper.deleteById(merchantId) > 0;
    }
}
