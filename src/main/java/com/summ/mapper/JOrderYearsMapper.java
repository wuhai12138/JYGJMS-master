package com.summ.mapper;

import com.summ.model.JOrderYears;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.OrderYearsReq;
import com.summ.model.response.OrderYearsRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JOrderYears 表数据库控制层接口
 *
 */
public interface JOrderYearsMapper extends BaseMapper<JOrderYears> {

    /**
     * 包年订单列表
     * @param orderYearsReq
     * @return
     */
    List<OrderYearsRes> getOrderYearsList(@Param("orderYearsReq") OrderYearsReq orderYearsReq);
    Integer getOrderYearsListCount(@Param("orderYearsReq") OrderYearsReq orderYearsReq);

}