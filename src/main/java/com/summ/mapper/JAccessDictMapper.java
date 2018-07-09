package com.summ.mapper;

import com.summ.model.JAccess;
import com.summ.model.JAccessDict;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * JAccessDict 表数据库控制层接口
 *
 */
public interface JAccessDictMapper extends BaseMapper<JAccessDict> {

    List<JAccess> getAccessUrlById(int adminTypeId);


}