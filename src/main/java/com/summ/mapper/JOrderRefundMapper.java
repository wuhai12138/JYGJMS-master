package com.summ.mapper;

import com.summ.model.JOrderRefund;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.OrderRefundRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JOrderRefund 表数据库控制层接口
 *
 */
public interface JOrderRefundMapper extends BaseMapper<JOrderRefund> {

    List<OrderRefundRes> getOrderRefundList(@Param("map") Map map);
    Integer getOrderRefundListCount(@Param("map") Map map);

    List<OrderRefundRes> getContractRefundDetail(int refundId);
    List<OrderRefundRes> getTempRefundDetail(int orderId);

    OrderRefundRes getScheduleNannyInfo(@Param("supplierId") int supplierId,@Param("scheduleId") int scheduleId);

}