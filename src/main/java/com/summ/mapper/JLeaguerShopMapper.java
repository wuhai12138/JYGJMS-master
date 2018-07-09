package com.summ.mapper;

import com.summ.model.JLeaguerShop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.LeaguerShopRes;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 *
 * JLeaguerShop 表数据库控制层接口
 *
 */
public interface JLeaguerShopMapper extends BaseMapper<JLeaguerShop> {

    List<LeaguerShopRes> getLeaguerShop(Integer leaguerId);
}