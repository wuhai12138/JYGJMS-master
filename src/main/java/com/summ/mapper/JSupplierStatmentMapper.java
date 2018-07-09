package com.summ.mapper;

import com.summ.model.JSupplierStatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.SupplierStatmentDetailReq;
import com.summ.model.request.TeacherStatmentDetailReq;
import com.summ.model.response.SupplierStatmentDetailRes;
import com.summ.model.response.TeacherStatmentDetailRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JSupplierStatment 表数据库控制层接口
 *
 */
public interface JSupplierStatmentMapper extends BaseMapper<JSupplierStatment> {

    Integer getSupplierStatmentDetailCount(@Param("supplierStatmentDetailReq") SupplierStatmentDetailReq supplierStatmentDetailReq);

    List<SupplierStatmentDetailRes> getSupplierStatmentDetail(@Param("supplierStatmentDetailReq") SupplierStatmentDetailReq supplierStatmentDetailReq);

}