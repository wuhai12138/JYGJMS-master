package com.summ.mapper;

import com.summ.model.JAppBannerGoods;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.BannerGoodsRes;
import com.summ.model.response.CouponGoodsRes;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-06-22
 */
public interface JAppBannerGoodsMapper extends BaseMapper<JAppBannerGoods> {

    List<BannerGoodsRes> getBannerGoods(int bannerId);
}
