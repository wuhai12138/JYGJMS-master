package com.summ.mapper;

import com.summ.model.JDictInfo;
import com.summ.model.JNannyCharacter;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.NannyCharacterRes;
import com.summ.model.response.NannySkillRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * JNannyCharacter 表数据库控制层接口
 */
public interface JNannyCharacterMapper extends BaseMapper<JNannyCharacter> {

    List<NannyCharacterRes> getSelectedCharacter(int nannyId);

    List<JDictInfo> getUnselectedCharacter(@Param("nannyId") int nannyId, @Param("name") String name);
}