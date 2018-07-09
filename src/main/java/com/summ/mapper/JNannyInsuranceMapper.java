package com.summ.mapper;

import com.summ.model.JNannyInsurance;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.NannyInsuranceReq;
import com.summ.model.response.NannyInsuranceRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JNannyInsurance 表数据库控制层接口
 *
 */
public interface JNannyInsuranceMapper extends BaseMapper<JNannyInsurance> {

    List<NannyInsuranceRes> getNannyInsuranceList(@Param("nannyInsuranceReq") NannyInsuranceReq nannyInsuranceReq);

    Integer getNannyInsuranceListCount(@Param("nannyInsuranceReq") NannyInsuranceReq nannyInsuranceReq);
}