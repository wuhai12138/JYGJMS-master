package com.summ.mapper;

import com.summ.model.JReportSalary;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.SalaryReq;
import com.summ.model.response.ReportSalaryRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * JReportSalary 表数据库控制层接口
 *
 */
public interface JReportSalaryMapper extends BaseMapper<JReportSalary> {

    /**
     * 查询已有月份工资报表
     * @param salaryReq
     * @return
     */
    List<ReportSalaryRes> getSalaryList(@Param("salaryReq") SalaryReq salaryReq);

    /**
     * 查询指定月份，指定门店的工时（三星工时，四星工时，五星工时）
     * @param salaryReq
     * @return
     */
    List<ReportSalaryRes> countNannyLevelWorktime(@Param("salaryReq") SalaryReq salaryReq);

    List<ReportSalaryRes> countNannySalary(@Param("salaryReq") SalaryReq salaryReq);

}