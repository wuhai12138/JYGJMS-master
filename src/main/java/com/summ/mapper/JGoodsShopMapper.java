package com.summ.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.JGoodsContract;
import com.summ.model.JGoodsShop;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summ
 * @since 2018-06-22
 */
public interface JGoodsShopMapper extends BaseMapper<JGoodsShop> {

    List<JGoodsContract> getGoodsByShop(Integer shopId);
}
