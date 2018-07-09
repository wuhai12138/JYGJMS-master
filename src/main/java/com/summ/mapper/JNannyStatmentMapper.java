package com.summ.mapper;

import com.summ.model.JNannyStatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.NannyStatmentDetailReq;
import com.summ.model.request.NannyStatmentReq;
import com.summ.model.request.NannyStatmentRewardsAndPunishmentsReq;
import com.summ.model.response.NannyStatmentDetailRes;
import com.summ.model.response.NannyStatmentRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JNannyStatment 表数据库控制层接口
 *
 */
public interface JNannyStatmentMapper extends BaseMapper<JNannyStatment> {

    List<NannyStatmentRes> getNannyStatmentList(@Param("nannyStatmentReq") NannyStatmentReq nannyStatmentReq);

    Integer getNannyStatmentListCount(@Param("nannyStatmentReq") NannyStatmentReq nannyStatmentReq);

    List<NannyStatmentDetailRes> getNannyStatmentDetail(@Param("nannyStatmentDetailReq") NannyStatmentDetailReq nannyStatmentDetailReq);

    Integer getNannyStatmentDetailCount(@Param("nannyStatmentDetailReq") NannyStatmentDetailReq nannyStatmentDetailReq);

    List<NannyStatmentDetailRes> getNannyStatmentRewardsAndPunishmentsDetail(@Param("map") Map map);

    Integer getNannyStatmentRewardsAndPunishmentsDetailCount(@Param("map") Map map);

    List<JNannyStatment> getNannyStatmentByScheduleId(int scheduleId);


}