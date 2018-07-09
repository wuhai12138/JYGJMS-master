package com.summ.mapper;

import com.summ.model.JCustomerAlyStatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.JCustomerWeiXinStatment;
import com.summ.model.request.CustomerStatmentReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * JCustomerAlyStatment 表数据库控制层接口
 */
public interface JCustomerAlyStatmentMapper extends BaseMapper<JCustomerAlyStatment> {
    /**
     * 根据日期获取对账单，不做分页
     *
     * @param customerStatmentReq
     * @return
     */
    List<JCustomerAlyStatment> getAlyStatmentListByDate(@Param("customerStatmentReq") CustomerStatmentReq customerStatmentReq);

}