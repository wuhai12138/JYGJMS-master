package com.summ.mapper;

import com.summ.model.JNannyTrain;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.JTrain;
import com.summ.model.response.NannyTrainRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JNannyTrain 表数据库控制层接口
 *
 */
public interface JNannyTrainMapper extends BaseMapper<JNannyTrain> {

    List<JTrain> getUnselectedTrainList(@Param("nannyId") int nannyId, @Param("trainName") String trainName);

    List<NannyTrainRes> getSelectedTrainList(int nannyId);
}