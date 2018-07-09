package com.summ.mapper;

import com.summ.model.JCouponShop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.CouponGoodsRes;
import com.summ.model.response.CouponShopRes;

import java.util.List;

/**
 *
 * JCouponShop 表数据库控制层接口
 *
 */
public interface JCouponShopMapper extends BaseMapper<JCouponShop> {

    List<CouponShopRes> getCouponShop(int couponId);
}