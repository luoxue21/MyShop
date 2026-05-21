package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商家Mapper接口
 */
@Mapper
public interface MerchantMapper {
    
    /**
     * 根据ID查询商家
     */
    Merchant findById(@Param("merchantId") Long merchantId);
    
    /**
     * 查询所有商家
     */
    List<Merchant> findAll();
    
    /**
     * 查询正常状态的商家
     */
    List<Merchant> findActiveMerchants();
    
    /**
     * 新增商家
     */
    int insert(Merchant merchant);
    
    /**
     * 更新商家信息
     */
    int update(Merchant merchant);
    
    /**
     * 删除商家
     */
    int deleteById(@Param("merchantId") Long merchantId);
}
