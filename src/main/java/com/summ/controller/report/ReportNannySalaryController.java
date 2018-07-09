package com.summ.controller.report;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JReportSalary;
import com.summ.model.request.SalaryReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.ReportSalaryRes;
import com.summ.utils.ResponseUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *服务师工资报表
 * @author summ
 */
@Controller
@RequestMapping("/report/nanny/salary")
public class ReportNannySalaryController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody SalaryReq salaryReq, ServletRequest request){
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            salaryReq.setAdminId(jAdmin.getAdminId());

            List<ReportSalaryRes> reportSalaryResList = jReportSalaryMapper.getSalaryList(salaryReq);
            /**判断工资金额确认表里是否有数据，没有则实时生成*/
            if (reportSalaryResList.size()==0){
                /**用于获取星级工时合计*/
                List<ReportSalaryRes> worktimeList = jReportSalaryMapper.countNannyLevelWorktime(salaryReq);
                /**用于获取奖励和退款*/
                List<ReportSalaryRes> salaryList = jReportSalaryMapper.countNannySalary(salaryReq);

                for (ReportSalaryRes reportSalaryRes : worktimeList){
                    for (ReportSalaryRes reportSalaryRes1 : salaryList){
                        if (reportSalaryRes.getShopId().equals(reportSalaryRes1.getShopId())){
                            reportSalaryRes.setRewards(reportSalaryRes1.getRewards());
                            reportSalaryRes.setOrderRefund(reportSalaryRes1.getOrderRefund());
                        }
                    }
                    //reportSalaryRes.setIndividualIncomeTax((reportSalaryRes.getHourlySalary().add(reportSalaryRes.getRewards()).subtract(reportSalaryRes.getOrderRefund())).multiply(new BigDecimal(0.06)).setScale(2,BigDecimal.ROUND_HALF_UP));
                    reportSalaryRes.setIndividualIncomeTax(new BigDecimal(0));
                    reportSalaryRes.setRealSalary((reportSalaryRes.getHourlySalary().add(reportSalaryRes.getRewards()).subtract(reportSalaryRes.getOrderRefund())));
                }
                return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",ResponseUtil.List2Map(worktimeList));
            }else {
                return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !", ResponseUtil.List2Map(reportSalaryResList));

            }
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JReportSalary jReportSalary){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "server err !",jReportSalaryMapper.updateById(jReportSalary));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");

        }

    }
}
