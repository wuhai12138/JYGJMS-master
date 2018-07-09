package com.summ.mapper;

import com.summ.model.JNannyInfo;
import com.summ.model.JOrderSchedule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.OrderScheduleReq;
import com.summ.model.request.ScheduleReq;
import com.summ.model.response.CustomerBalanceWarnRes;
import com.summ.model.response.OrderScheduleRes;
import com.summ.model.response.ScheduleRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JOrderSchedule 表数据库控制层接口
 *
 */
public interface JOrderScheduleMapper extends BaseMapper<JOrderSchedule> {

    List<OrderScheduleRes> getScheduleList(@Param("orderScheduleReq")OrderScheduleReq orderScheduleReq);

    List<OrderScheduleRes> getScheduleListByList(List<Integer> list);

    Integer getScheduleCount(@Param("orderScheduleReq")OrderScheduleReq orderScheduleReq);

    List<JOrderSchedule> signNannySchedule(@Param("map") Map map);

    List<OrderScheduleRes> getTempScheduleList(int orderId);

    List<OrderScheduleRes> getTempScheduleListByList(List<Integer> list);

    /**
     * 日程综合模块接口
     * @param scheduleReq
     * @return
     */
    List<ScheduleRes> getContractScheduleList(@Param("scheduleReq") ScheduleReq scheduleReq);
    Integer getContractScheduleListCount(@Param("scheduleReq") ScheduleReq scheduleReq);
    List<ScheduleRes> getAllTempScheduleList(@Param("scheduleReq") ScheduleReq scheduleReq);
    Integer getAllTempScheduleListCount(@Param("scheduleReq") ScheduleReq scheduleReq);
    List<ScheduleRes> getAllYearsScheduleList(@Param("scheduleReq") ScheduleReq scheduleReq);
    Integer getAllYearsScheduleListCount(@Param("scheduleReq") ScheduleReq scheduleReq);
    List<ScheduleRes> getAllMonthScheduleList(@Param("scheduleReq") ScheduleReq scheduleReq);
    Integer getAllMonthScheduleListCount(@Param("scheduleReq") ScheduleReq scheduleReq);
    Integer updateSchedulePrice(@Param("map") Map map);

    /**
     * 客户余额提醒表
     * @param
     */
    List<CustomerBalanceWarnRes> getCustomerScheduleSum(@Param("scheduleReq") ScheduleReq scheduleReq);
    Integer getCustomerScheduleSumCount(@Param("scheduleReq") ScheduleReq scheduleReq);
    List<CustomerBalanceWarnRes> getCustomerSchedule(@Param("scheduleReq") ScheduleReq scheduleReq);

    /**
     * 查找未结算的订单日程
     * @param orderId
     * @param orderType
     * @return
     */
    List<JOrderSchedule> getOrderScheduleUnCheckOut(@Param("orderId") Integer orderId,@Param("orderType") Integer orderType);
    List<JOrderSchedule> getOrderScheduleToBeFinished(@Param("orderId") Integer orderId,@Param("orderType") Integer orderType);

    /**
     * 根据日程id获取日程详细信息
     * @param scheduleId
     */
    OrderScheduleRes getScheduleDetail(int scheduleId);
}