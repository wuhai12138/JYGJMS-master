package com.summ.mapper;

import com.summ.model.JCouponList;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.CustomerCouponReq;
import com.summ.model.request.OrderTempChargeReq;
import com.summ.model.response.CustomerCouponListRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JCouponList 表数据库控制层接口
 *
 */
public interface JCouponListMapper extends BaseMapper<JCouponList> {

    int deleteCoupon(int id);

    List<CustomerCouponListRes> getListById(@Param("customerCouponReq") CustomerCouponReq customerCouponReq);

    Integer getCount(@Param("customerCouponReq") CustomerCouponReq customerCouponReq);

    /**
     * 获取临时订单不可用优惠券
     * @param orderTempChargeReq
     * @return
     */
    List<CustomerCouponListRes> getUnUseCouponList(@Param("orderTempChargeReq") OrderTempChargeReq orderTempChargeReq);
    /**
     * 获取临时订单可用优惠券
     * @param orderTempChargeReq
     * @return
     */
    List<CustomerCouponListRes> getUsedCouponList(@Param("orderTempChargeReq") OrderTempChargeReq orderTempChargeReq);


}