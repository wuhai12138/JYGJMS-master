package com.summ.mapper;

import com.summ.model.JSupplier;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.SupplierReq;
import com.summ.model.response.SupplierListRes;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * JSupplier 表数据库控制层接口
 */
public interface JSupplierMapper extends BaseMapper<JSupplier> {
    List<SupplierListRes> getSupplierList(@Param("supplierReq") SupplierReq supplierReq);

    Integer getSupplierListCount(@Param("supplierReq") SupplierReq supplierReq);

    SupplierListRes getSupplierDetail(Integer id);
}