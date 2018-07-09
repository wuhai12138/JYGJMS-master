package com.summ.controller.report;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.request.NannyInsuranceReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.NannyInsuranceRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 服务师保险报表
 * @author summ
 */
@Controller
@RequestMapping("/report/nanny/insurance")
public class ReportNannyInsuranceController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody NannyInsuranceReq nannyInsuranceReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            nannyInsuranceReq.setAdminId(jAdmin.getAdminId());
            nannyInsuranceReq.setPage((nannyInsuranceReq.getPage()-1)*nannyInsuranceReq.getSize());
            /**本月购买保险总人数*/
            List<NannyInsuranceRes> nannyInsuranceResList = jNannyInsuranceMapper.getNannyInsuranceList(nannyInsuranceReq);
//            /**上月购买保险人数*/
//            nannyInsuranceReq.setInsuranceDate(nannyInsuranceReq.getLastInsuranceDate());
//            List<NannyInsuranceRes> lastNannyInsuranceResList = jNannyInsuranceMapper.getNannyInsuranceList(nannyInsuranceReq);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", ResponseUtil.List2Map(nannyInsuranceResList,jNannyInsuranceMapper.getNannyInsuranceListCount(nannyInsuranceReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
