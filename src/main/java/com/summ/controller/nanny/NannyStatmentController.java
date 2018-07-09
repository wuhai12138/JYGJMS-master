package com.summ.controller.nanny;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JNannyInfo;
import com.summ.model.JNannyStatment;
import com.summ.model.request.NannyStatmentDetailReq;
import com.summ.model.request.NannyStatmentReq;
import com.summ.model.request.NannyStatmentRewardsAndPunishmentsReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.NannyStatmentRes;
import com.summ.utils.JsonUtil;
import com.summ.utils.OrderUtil;
import com.summ.utils.ResponseUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务师对账单
 * Created by summ on 18/1/18.
 */
@Controller
@RequestMapping("/nanny/statment")
public class NannyStatmentController extends AutoMapperController{

    /**
     * 门店工资明细
     * @param nannyStatmentReq
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/find/list")
    public Object findList(@RequestBody NannyStatmentReq nannyStatmentReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            nannyStatmentReq.setAdminId(jAdmin.getAdminId());
            nannyStatmentReq.setPage(nannyStatmentReq.getSize() * (nannyStatmentReq.getPage()-1));
            List<NannyStatmentRes> nannyStatmentResList = jNannyStatmentMapper.getNannyStatmentList(nannyStatmentReq);
            for (NannyStatmentRes nannyStatmentRes : nannyStatmentResList){
                /**合计工时*/
                nannyStatmentRes.setTotalTimes(nannyStatmentRes.getOrderContractTimes()+nannyStatmentRes.getOrderTempTimes()+nannyStatmentRes.getOrderGrouponTimes());
                /**合计工资*/
                nannyStatmentRes.setTotalSalary(nannyStatmentRes.getOrderContractMoney().add(nannyStatmentRes.getOrderTempMoney()).add(nannyStatmentRes.getOrderGrouponMoney()));
                /** 实际发放工资 */
                nannyStatmentRes.setRealSalary(nannyStatmentRes.getTotalSalary().add(nannyStatmentRes.getReward()).subtract(nannyStatmentRes.getOrderRefund()));
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(nannyStatmentResList,jNannyStatmentMapper.getNannyStatmentListCount(nannyStatmentReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 服务师对账单明细
     * @param nannyStatmentDetailReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/find/detail")
    public Object findDetail(@RequestBody NannyStatmentDetailReq nannyStatmentDetailReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            nannyStatmentDetailReq.setAdminId(jAdmin.getAdminId());
            nannyStatmentDetailReq.setPage(nannyStatmentDetailReq.getSize() * (nannyStatmentDetailReq.getPage()-1));
            Map<String, Object> map = new HashedMap();
            map.put("count", jNannyStatmentMapper.getNannyStatmentDetailCount(nannyStatmentDetailReq));
            map.put("list", jNannyStatmentMapper.getNannyStatmentDetail(nannyStatmentDetailReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JNannyStatment jNannyStatment, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jNannyStatment.setAdminId(jAdmin.getAdminId());
            jNannyStatment.setStatmentNanny(OrderUtil.generateStamentNumber(jNannyStatment.getNannyId()));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyStatmentMapper.insert(jNannyStatment));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 服务师奖惩
     * @param nannyStatmentRewardsAndPunishmentsReq
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/RewardsAndPunishments/find")
    public Object RewardsAndPunishments(@RequestBody NannyStatmentRewardsAndPunishmentsReq nannyStatmentRewardsAndPunishmentsReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            nannyStatmentRewardsAndPunishmentsReq.setAdminId(jAdmin.getAdminId());
            nannyStatmentRewardsAndPunishmentsReq.setPage(nannyStatmentRewardsAndPunishmentsReq.getSize() * (nannyStatmentRewardsAndPunishmentsReq.getPage()-1));
            Map<String, Object> map = new HashedMap();
            Map map1 = JsonUtil.Obj2Map(nannyStatmentRewardsAndPunishmentsReq);
            map1.put("startDate",new Date((Long) map1.get("startDate")));
            map1.put("endDate",new Date((Long) map1.get("endDate")));
            map.put("count", jNannyStatmentMapper.getNannyStatmentRewardsAndPunishmentsDetailCount(map1));
            map.put("list", jNannyStatmentMapper.getNannyStatmentRewardsAndPunishmentsDetail(map1));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
