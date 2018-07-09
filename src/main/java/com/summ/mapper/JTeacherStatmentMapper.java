package com.summ.mapper;

import com.summ.model.JTeacherStatment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.NannyStatmentDetailReq;
import com.summ.model.request.TeacherStatmentDetailReq;
import com.summ.model.response.NannyStatmentDetailRes;
import com.summ.model.response.TeacherStatmentDetailRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * JTeacherStatment 表数据库控制层接口
 *
 */
public interface JTeacherStatmentMapper extends BaseMapper<JTeacherStatment> {

    Integer getTeacherStatmentDetailCount(@Param("teacherStatmentDetailReq") TeacherStatmentDetailReq teacherStatmentDetailReq);

    List<TeacherStatmentDetailRes> getTeacherStatmentDetail(@Param("teacherStatmentDetailReq") TeacherStatmentDetailReq teacherStatmentDetailReq);

}