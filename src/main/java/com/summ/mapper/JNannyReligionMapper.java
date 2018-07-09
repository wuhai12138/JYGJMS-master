package com.summ.mapper;

import com.summ.model.JDictInfo;
import com.summ.model.JNannyReligion;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.NannyJobLevelRes;
import com.summ.model.response.NannyReligionRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JNannyReligion 表数据库控制层接口
 *
 */
public interface JNannyReligionMapper extends BaseMapper<JNannyReligion> {
    List<NannyReligionRes> getSelectedReligion(int nannyId);
    List<JDictInfo> getUnselectedReligion(@Param("nannyId") int nannyId, @Param("name") String name);

}