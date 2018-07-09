package com.summ.mapper;

import com.summ.model.JCustomerMessageDepartment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.CustomerFeedbackDepartmentRes;

import java.util.List;

/**
 *
 * JCustomerMessageDepartment 表数据库控制层接口
 *
 */
public interface JCustomerMessageDepartmentMapper extends BaseMapper<JCustomerMessageDepartment> {

    /**
     * 获取反馈部门
     * @param messageId
     * @return
     */
    List<CustomerFeedbackDepartmentRes> getCustomerFeedbackDepartment(Integer messageId);
}