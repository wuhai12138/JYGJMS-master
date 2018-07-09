package com.summ.mapper;

import com.summ.model.JDictType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * JDictType 表数据库控制层接口
 *
 */
public interface JDictTypeMapper extends BaseMapper<JDictType> {
    List<JDictType> selectTypeList();
}