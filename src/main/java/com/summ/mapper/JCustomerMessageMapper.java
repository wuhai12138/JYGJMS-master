package com.summ.mapper;

import com.summ.model.JCustomerMessage;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.CustomerFeedbackReq;
import com.summ.model.response.CustomerFeedbackRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JCustomerMessage 表数据库控制层接口
 *
 */
public interface JCustomerMessageMapper extends BaseMapper<JCustomerMessage> {

    /**
     * 客户反馈消息
     * @param customerFeedbackReq
     * @return
     */
    int getCustomerFeedbackCount(@Param("customerFeedbackReq") CustomerFeedbackReq customerFeedbackReq);
    List<CustomerFeedbackRes> getCustomerFeedbackList(@Param("customerFeedbackReq") CustomerFeedbackReq customerFeedbackReq);
    CustomerFeedbackRes getCustomerFeedbackDetail(int feedbackId);

}