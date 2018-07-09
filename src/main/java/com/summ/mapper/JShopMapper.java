package com.summ.mapper;

import com.summ.model.JShop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.ShopReq;
import com.summ.model.response.ShopRes;
import com.summ.utils.JsonUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JShop 表数据库控制层接口
 *
 */
public interface JShopMapper extends BaseMapper<JShop> {

    List<JShop> getAllShop();

    List<ShopRes> getShopList(@Param("shopReq")ShopReq shopReq);
}