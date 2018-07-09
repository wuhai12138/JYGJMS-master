package com.summ.mapper;

import com.summ.model.JNannyInfo;
import com.summ.model.JNannyJobLevelGoods;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JNannyJobLevelGoods 表数据库控制层接口
 *
 */
public interface JNannyJobLevelGoodsMapper extends BaseMapper<JNannyJobLevelGoods> {

    /**
     * 验证服务师能否做此产品
     * @param nannyId
     * @param goodsId
     * @return
     */
    List<JNannyInfo> signNannyAndGoods(@Param("nannyId") Integer nannyId, @Param("goodsId") Integer goodsId);

}