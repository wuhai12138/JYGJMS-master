package com.summ.mapper;

import com.summ.model.JAdminShop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.AdminShopRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JAdminShop 表数据库控制层接口
 *
 */
public interface JAdminShopMapper extends BaseMapper<JAdminShop> {

    List<AdminShopRes> getList(int adminId);

}