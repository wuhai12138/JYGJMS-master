package com.summ.mapper;

import com.summ.model.JOrderMonth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.OrderMonthReq;
import com.summ.model.response.OrderMonthRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-05-29
 */
public interface JOrderMonthMapper extends BaseMapper<JOrderMonth> {

    List<OrderMonthRes> getOrderMonthList(@Param("orderMonthReq") OrderMonthReq orderMonthReq);
    Integer getOrderMonthListCount(@Param("orderMonthReq") OrderMonthReq orderMonthReq);

}
