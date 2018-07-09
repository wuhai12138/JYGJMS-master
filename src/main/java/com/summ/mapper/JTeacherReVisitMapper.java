package com.summ.mapper;

import com.summ.model.JTeacher;
import com.summ.model.JTeacherReVisit;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * JTeacherReVisit 表数据库控制层接口
 *
 */
public interface JTeacherReVisitMapper extends BaseMapper<JTeacherReVisit> {

    List<JTeacher> getRevisitTeacherByCustomer(int houseId);
}