package com.summ.mapper;

import com.summ.model.JAdminType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.AdminTypeRes;

import java.util.List;

/**
 *
 * JAdminType 表数据库控制层接口
 *
 */
public interface JAdminTypeMapper extends BaseMapper<JAdminType> {

    List<AdminTypeRes> getAdminTypeById(int adminId);


}