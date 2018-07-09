package com.summ.mapper;

import com.summ.model.AASql;
import com.summ.model.JNannyWorkTimeDic;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * JNannyWorkTimeDic 表数据库控制层接口
 *
 */
public interface JNannyWorkTimeDicMapper extends BaseMapper<JNannyWorkTimeDic> {
    List<String> test(AASql sql);
}