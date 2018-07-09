package com.summ.mapper;

import com.summ.model.JDictInfo;
import com.summ.model.JNannyJobLevel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.NannyJobLevelRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JNannyJobLevel 表数据库控制层接口
 *
 */
public interface JNannyJobLevelMapper extends BaseMapper<JNannyJobLevel> {
    List<NannyJobLevelRes> getSelectedLevelList(int nannyId);
    List<JDictInfo> getUnselectedLevelList(@Param("nannyId") int nannyId, @Param("name") String name);

}