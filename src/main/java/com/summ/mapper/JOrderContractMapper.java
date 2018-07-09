package com.summ.mapper;

import com.summ.model.JOrderContract;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.OrderContractReq;
import com.summ.model.response.OrderContractRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JOrderContract 表数据库控制层接口
 *
 */
public interface JOrderContractMapper extends BaseMapper<JOrderContract> {

    List<OrderContractRes> getContractList(@Param("orderContractReq") OrderContractReq orderContractReq);

    Integer getContractCount(@Param("orderContractReq") OrderContractReq orderContractReq);

}