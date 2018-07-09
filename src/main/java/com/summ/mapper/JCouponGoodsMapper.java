package com.summ.mapper;

import com.summ.model.JCouponGoods;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.CouponGoodsRes;

import java.util.List;

/**
 *
 * JCouponGoods 表数据库控制层接口
 *
 */
public interface JCouponGoodsMapper extends BaseMapper<JCouponGoods> {
    List<CouponGoodsRes> getCouponGoods(int couponId);
}