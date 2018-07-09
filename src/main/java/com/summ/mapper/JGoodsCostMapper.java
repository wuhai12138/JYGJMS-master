package com.summ.mapper;

import com.summ.model.JGoodsCost;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.OrderTempSupplierRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JGoodsCost 表数据库控制层接口
 *
 */
public interface JGoodsCostMapper extends BaseMapper<JGoodsCost> {
    List<OrderTempSupplierRes> getSupplierList(@Param("orderId") Integer orderId, @Param("supplierName") String supplierName);

}