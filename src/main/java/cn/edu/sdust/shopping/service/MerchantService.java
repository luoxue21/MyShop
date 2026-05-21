package cn.edu.sdust.shopping.service;

import cn.edu.sdust.shopping.entity.Merchant;

import java.util.List;

/**
 * 商家Service接口
 */
public interface MerchantService {
    
    List<Merchant> findAll();
    
    List<Merchant> findActiveMerchants();
    
    Merchant findById(Long merchantId);
    
    boolean addMerchant(Merchant merchant);
    
    boolean updateMerchant(Merchant merchant);
    
    boolean deleteMerchant(Long merchantId);
}
