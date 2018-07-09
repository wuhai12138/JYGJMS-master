package com.summ.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.JCustomerPrepaid;
import com.summ.model.response.CustomerPrepaidRes;
import com.summ.model.request.CustomerPrepaidReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-06-11
 */
public interface JCustomerPrepaidMapper extends BaseMapper<JCustomerPrepaid> {
    List<CustomerPrepaidRes> getCustomerPrepaidList(@Param("customerPrepaidReq") CustomerPrepaidReq customerPrepaidReq);
    Integer getCustomerPrepaidListCount(@Param("customerPrepaidReq") CustomerPrepaidReq customerPrepaidReq);
}
