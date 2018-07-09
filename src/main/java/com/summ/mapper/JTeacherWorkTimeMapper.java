package com.summ.mapper;

import com.summ.model.JTeacher;
import com.summ.model.JTeacherWorkTime;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.TeacherWortTimeByOrderReq;
import com.summ.model.response.TeacherWorkTimeRes;
import com.summ.model.response.TimeAndWeekRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JTeacherWorkTime 表数据库控制层接口
 *
 */
public interface JTeacherWorkTimeMapper extends BaseMapper<JTeacherWorkTime> {


    List<TeacherWorkTimeRes> getTeacherWorkTime(@Param("teacherWortTimeByOrderReq") TeacherWortTimeByOrderReq teacherWortTimeByOrderReq);

    List<TimeAndWeekRes> getTeacherScheduleTime(TeacherWortTimeByOrderReq teacherWortTimeByOrderReq);


}