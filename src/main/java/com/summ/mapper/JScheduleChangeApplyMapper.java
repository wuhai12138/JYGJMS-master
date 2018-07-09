package com.summ.mapper;

import com.summ.model.JScheduleChangeApply;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.ScheduleChangeApplyReq;
import com.summ.model.response.ScheduleChangeApplyRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-06-15
 */
public interface JScheduleChangeApplyMapper extends BaseMapper<JScheduleChangeApply> {

    List<ScheduleChangeApplyRes> getChangeByCustomer(Integer customer);
    List<ScheduleChangeApplyRes> getChangeByNanny(Integer nanny);
    List<ScheduleChangeApplyRes> getChangeList(@Param("scheduleChangeApplyReq") ScheduleChangeApplyReq scheduleChangeApplyReq);
    Integer getChangeListCount(@Param("scheduleChangeApplyReq") ScheduleChangeApplyReq scheduleChangeApplyReq);
}
