package com.summ.mapper;

import com.summ.model.JAccess;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * JAccess 表数据库控制层接口
 *
 * @author summ
 */
public interface JAccessMapper extends BaseMapper<JAccess> {

    List<JAccess> getListOrderBySort();

}