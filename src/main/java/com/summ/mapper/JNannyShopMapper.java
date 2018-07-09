package com.summ.mapper;

import com.summ.model.JNannyShop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JNannyShop 表数据库控制层接口
 *
 */
public interface JNannyShopMapper extends BaseMapper<JNannyShop> {

    List<JNannyShop> signNannyShopByShop(@Param("nannyId") Integer nannyId, @Param("shopId")Integer shopId);
}