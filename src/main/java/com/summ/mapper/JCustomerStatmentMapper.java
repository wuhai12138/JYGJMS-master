package com.summ.mapper;

import com.summ.model.JCustomerStatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.CustomerStatmentReq;
import com.summ.model.response.CustomerCouponListRes;
import com.summ.model.response.CustomerStatmentRes;
import com.summ.model.response.ReportCustomerRechargeRes;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * JCustomerStatment 表数据库控制层接口
 *
 */
public interface JCustomerStatmentMapper extends BaseMapper<JCustomerStatment> {

    int getCount(@Param("customerStatmentReq") CustomerStatmentReq customerStatmentReq);

    List<CustomerStatmentRes> getStatmentList(@Param("customerStatmentReq") CustomerStatmentReq customerStatmentReq);

    /**
     * 根据日期获取对账单，不做分页
     * @param customerStatmentReq
     * @return
     */
    List<CustomerStatmentRes> getStatmentListByDate(@Param("customerStatmentReq") CustomerStatmentReq customerStatmentReq);

    CustomerStatmentRes getStatmentDetail(int statmentId);

    /**
     * 查询某个时间段内某客户的充值总金额
     */
    BigDecimal getCustomerRechargeMoney(@Param("customerId") Integer customerId, @Param("startDate")Date startDate,@Param("endDate")Date endDate);


    /**
     * 客户充值报表
     * @param map
     * @return
     */
    List<ReportCustomerRechargeRes> getReportCustomerRecharge(@Param("map") Map map);
    Integer getReportCustomerRechargeCount(@Param("map") Map map);

    List<CustomerStatmentRes> getCustomerRechargeDetail(@Param("map") Map map);
    Integer getCustomerRechargeDetailCount(@Param("map") Map map);
}