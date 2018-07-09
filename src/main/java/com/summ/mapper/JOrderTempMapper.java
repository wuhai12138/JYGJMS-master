package com.summ.mapper;

import com.summ.model.JOrderTemp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.OrderTempReq;
import com.summ.model.response.OrderTempRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JOrderTemp 表数据库控制层接口
 *
 */
public interface JOrderTempMapper extends BaseMapper<JOrderTemp> {
    List<OrderTempRes> getTempList(@Param("orderTempReq")OrderTempReq orderTempReq);
    Integer getTempCount(@Param("orderTempReq")OrderTempReq orderTempReq);

}