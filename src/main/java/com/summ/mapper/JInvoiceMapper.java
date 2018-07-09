package com.summ.mapper;

import com.summ.model.JInvoice;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.InvoiceRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JInvoice 表数据库控制层接口
 *
 */
public interface JInvoiceMapper extends BaseMapper<JInvoice> {

    List<InvoiceRes> getInvoice(@Param("map") Map map);
    Integer getInvoiceCount(@Param("map") Map map);

    List<Map> getCompany();

}