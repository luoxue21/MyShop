package cn.edu.sdust.shopping.mapper;

import cn.edu.sdust.shopping.entity.Logistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物流Mapper接口
 */
@Mapper
public interface LogisticsMapper {
    
    /**
     * 根据ID查询物流记录
     */
    Logistics findById(@Param("logisticsId") Long logisticsId);
    
    /**
     * 根据订单ID查询物流记录
     */
    Logistics findByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 查询所有物流记录
     */
    List<Logistics> findAll();
    
    /**
     * 新增物流记录
     */
    int insert(Logistics logistics);
    
    /**
     * 更新物流记录
     */
    int update(Logistics logistics);
}
