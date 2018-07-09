package com.summ.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.JNannyScheduleFeedback;
import com.summ.model.request.CustomerScheduleFeedBackReq;
import com.summ.model.response.CustomerScheduleFeedbackRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-06-12
 */
public interface JNannyScheduleFeedbackMapper extends BaseMapper<JNannyScheduleFeedback> {

    List<CustomerScheduleFeedbackRes> getListBuReq(@Param("customerScheduleFeedBackReq") CustomerScheduleFeedBackReq customerScheduleFeedBackReq);
    Integer getListBuReqCount(@Param("customerScheduleFeedBackReq") CustomerScheduleFeedBackReq customerScheduleFeedBackReq);
}
