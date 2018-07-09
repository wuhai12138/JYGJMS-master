package com.summ.mapper;

import com.summ.model.JCustomerWeiXinStatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.CustomerStatmentReq;
import com.summ.model.response.CustomerStatmentRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JCustomerWeiXinStatment 表数据库控制层接口
 *
 */
public interface JCustomerWeiXinStatmentMapper extends BaseMapper<JCustomerWeiXinStatment> {
    /**
     * 根据日期获取对账单，不做分页
     * @param customerStatmentReq
     * @return
     */
    List<JCustomerWeiXinStatment> getWeiXinStatmentListByDate(@Param("customerStatmentReq") CustomerStatmentReq customerStatmentReq);
}