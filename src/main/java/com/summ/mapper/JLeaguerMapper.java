package com.summ.mapper;

import com.summ.model.JLeaguer;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.LeaguerReq;
import com.summ.model.request.SupplierReq;
import com.summ.model.response.LeaguerListRes;
import com.summ.model.response.SupplierListRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JLeaguer 表数据库控制层接口
 *
 */
public interface JLeaguerMapper extends BaseMapper<JLeaguer> {
    List<LeaguerListRes> getLeaguerList(@Param("leaguerReq") LeaguerReq leaguerReq);

    Integer getLeaguerListCount(@Param("leaguerReq") LeaguerReq leaguerReq);

    LeaguerListRes getLeaguerDetail(Integer id);

}