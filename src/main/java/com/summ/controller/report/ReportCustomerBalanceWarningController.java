package com.summ.controller.report;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.request.ScheduleReq;
import com.summ.model.response.CustomerBalanceWarnRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户余额提醒表
 *
 * @author summ
 */
@Controller
@RequestMapping("/report/customer")
public class ReportCustomerBalanceWarningController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/balance/warn")
    public Object find(@RequestBody ScheduleReq scheduleReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            scheduleReq.setAdminId(jAdmin.getAdminId());
            scheduleReq.setPage(scheduleReq.getSize() * (scheduleReq.getPage() - 1));
            /**根据客户id sum出的结果*/
            List<CustomerBalanceWarnRes> customerBalanceWarnResList = jOrderScheduleMapper.getCustomerScheduleSum(scheduleReq);
            /**所有符合条件的日程*/
            List<CustomerBalanceWarnRes> customerBalanceWarnResArrayList = jOrderScheduleMapper.getCustomerSchedule(scheduleReq);

            for (CustomerBalanceWarnRes customerBalanceWarnRes : customerBalanceWarnResList) {
                /**查询当前客户固定时间内充值总额*/
                customerBalanceWarnRes.setTotalCharge(jCustomerStatmentMapper.getCustomerRechargeMoney(customerBalanceWarnRes.getCustomerId(),scheduleReq.getStartDate(),scheduleReq.getEndDate()));

                /**如果所有日程总价大于客户余额
                 * 则计算需充值金额和充值日期*/
                if (customerBalanceWarnRes.getTotalPrice().compareTo(customerBalanceWarnRes.getCustomerBalance()) == 1) {
                    /**充值金额*/
                    customerBalanceWarnRes.setTotalToCharge(customerBalanceWarnRes.getTotalPrice().subtract(customerBalanceWarnRes.getCustomerBalance()));


                    /**计算最后充值日期*/
                    for (CustomerBalanceWarnRes customerBalanceWarnRes1 : customerBalanceWarnResArrayList) {
                        /**找出相同客户的日程*/
                        if (customerBalanceWarnRes.getCustomerId().equals(customerBalanceWarnRes1.getCustomerId())) {
                            /**将单个日程的价格加在临时字段tempMoney里*/
                            customerBalanceWarnRes.setTempMoney(customerBalanceWarnRes.getTempMoney().add(customerBalanceWarnRes1.getTotalPrice()));
                            /**判断临时字段与客户余额，如果客户余额不够，则将最近的日程日期添加到最后充值日期字段*/
                            if (customerBalanceWarnRes.getTempMoney().compareTo(customerBalanceWarnRes.getCustomerBalance()) == 1) {
                                customerBalanceWarnRes.setRechargeDate(customerBalanceWarnRes.getScheduleDate());
                            }
                        }
                    }
                }

            }

            return new ModelRes(ModelRes.Status.SUCCESS, "server err !", ResponseUtil.List2Map(customerBalanceWarnResList, jOrderScheduleMapper.getCustomerScheduleSumCount(scheduleReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");

        }

    }
}
