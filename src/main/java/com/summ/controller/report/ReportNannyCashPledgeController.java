package com.summ.controller.report;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JReportSalary;
import com.summ.model.request.NannyCashPledgeReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * 服务师保证金报表
 * @author summ
 */
@Controller
@RequestMapping("/report/nanny/cash/pledge")
public class ReportNannyCashPledgeController extends AutoMapperController{

    /**
     * 服务师服务质量保证金名单汇总
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object find(@RequestBody Map map){
        try {
            Date startDate = new Date((Long) map.get("startDate"));
            Date endDate = new Date((Long) map.get("endDate"));
            return new ModelRes(ModelRes.Status.SUCCESS, "server err !", ResponseUtil.List2Map(jNannyCashPledgeRecordMapper.getReportCashPledge(startDate,endDate)));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");

        }

    }

    /**
     * 服务师服务质量保证金明细
     * @param nannyCashPledgeReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    public Object findDetail(@RequestBody NannyCashPledgeReq nannyCashPledgeReq){
        try {
            nannyCashPledgeReq.setPage((nannyCashPledgeReq.getPage()-1)*nannyCashPledgeReq.getSize());
            return new ModelRes(ModelRes.Status.SUCCESS, "server err !", ResponseUtil.List2Map(jNannyCashPledgeRecordMapper.getReportCashPledgeDetailList(nannyCashPledgeReq),jNannyCashPledgeRecordMapper.getReportCashPledgeDetailCount(nannyCashPledgeReq)));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");

        }

    }

}
