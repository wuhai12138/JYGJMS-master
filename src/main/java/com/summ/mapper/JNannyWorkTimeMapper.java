package com.summ.mapper;

import com.summ.model.JNannyInfo;
import com.summ.model.JNannyWorkTime;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.NannyWortTimeByOrderReq;
import com.summ.model.response.NannyHotWorkTimeRes;
import com.summ.model.response.TimeAndWeekRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JNannyWorkTime 表数据库控制层接口
 *
 */
public interface JNannyWorkTimeMapper extends BaseMapper<JNannyWorkTime> {
    List<JNannyInfo> signNannyWorkTime(@Param("map") Map map);

    List<NannyHotWorkTimeRes> getHotNannyWorkTime(@Param("nannyWortTimeByOrderReq") NannyWortTimeByOrderReq nannyWortTimeByOrderReq);

    List<TimeAndWeekRes> getHotNannyScheduleTime(NannyWortTimeByOrderReq nannyWortTimeByOrderReq);

}