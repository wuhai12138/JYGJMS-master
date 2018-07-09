package com.summ.mapper;

import com.summ.model.JOrderMonthPrepaid;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.OrderMonthPrepaidReq;
import com.summ.model.response.OrderMonthPrepaidRes;
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
public interface JOrderMonthPrepaidMapper extends BaseMapper<JOrderMonthPrepaid> {

    List<OrderMonthPrepaidRes> getPrepaidList(@Param("orderMonthPrepaidReq") OrderMonthPrepaidReq orderMonthPrepaidReq);
    Integer getPrepaidListCount(@Param("orderMonthPrepaidReq") OrderMonthPrepaidReq orderMonthPrepaidReq);
}
