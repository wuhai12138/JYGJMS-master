package com.summ.mapper;

import com.summ.model.JWithdrawal;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.WithdrawalReq;
import com.summ.model.response.WithdrawalRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JWithdrawal 表数据库控制层接口
 *
 */
public interface JWithdrawalMapper extends BaseMapper<JWithdrawal> {

    List<WithdrawalRes> getWithdrawal(@Param("map") Map map);
    Integer getWithdrawalCount(@Param("map") Map map);

}