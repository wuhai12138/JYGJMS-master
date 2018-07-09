package com.summ.mapper;

import com.summ.model.JScheduleNanny;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.CommentReq;
import com.summ.model.response.ScheduleRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JScheduleNanny 表数据库控制层接口
 *
 */
public interface JScheduleNannyMapper extends BaseMapper<JScheduleNanny> {
    List<ScheduleRes> getScheduleComment(@Param("commentReq") CommentReq commentReq);
    Integer getScheduleCommentCount(@Param("commentReq") CommentReq commentReq);
}