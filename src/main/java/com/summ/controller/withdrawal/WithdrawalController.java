package com.summ.controller.withdrawal;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JCustomer;
import com.summ.model.JCustomerStatment;
import com.summ.model.JWithdrawal;
import com.summ.model.request.WithdrawalReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.JsonUtil;
import com.summ.utils.OrderUtil;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 提现模块
 */
@Controller
@RequestMapping("/withdrawal")
public class WithdrawalController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JWithdrawal jWithdrawal, ServletRequest request) {
        try {
            if (jWithdrawal.getMoney()==null||jWithdrawal.getMoney().compareTo(new BigDecimal(0))!=1){
                return new ModelRes(ModelRes.Status.FAILED, "提现金额不符合规范 !", null);
            }
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jWithdrawalMapper.insert(jWithdrawal));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JWithdrawal jWithdrawal) {
        try {
            JWithdrawal jWithdrawal1 = jWithdrawalMapper.selectById(Long.valueOf(jWithdrawal.getWithdrawalId()));
            jWithdrawal.setRemark(jWithdrawal1.getRemark()+" "+jWithdrawal.getRemark());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jWithdrawalMapper.updateById(jWithdrawal));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 审核不通过
     * @param jWithdrawal
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uncheck")
    public Object uncheck(@RequestBody JWithdrawal jWithdrawal,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jWithdrawal.setCheckStatus(246);
            jWithdrawal.setCheckId(jAdmin.getAdminId());
            jWithdrawal.setCheckDate(new Date());
            JWithdrawal jWithdrawal1 = jWithdrawalMapper.selectById(Long.valueOf(jWithdrawal.getWithdrawalId()));
            jWithdrawal.setRemark(jWithdrawal1.getRemark()+" "+jWithdrawal.getRemark());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jWithdrawalMapper.updateById(jWithdrawal));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 打款成功
     * @param jWithdrawal
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/pay/success")
    public Object paySuccess(@RequestBody JWithdrawal jWithdrawal,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jWithdrawal.setCheckStatus(247);
            jWithdrawal.setPayId(jAdmin.getAdminId());
            jWithdrawal.setPayDate(new Date());
            JWithdrawal jWithdrawal1 = jWithdrawalMapper.selectById(Long.valueOf(jWithdrawal.getWithdrawalId()));
            jWithdrawal.setRemark(jWithdrawal1.getRemark()+" "+jWithdrawal.getRemark());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jWithdrawalMapper.updateById(jWithdrawal));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 打款失败
     * @param jWithdrawal
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/pay/fail")
    public Object payFail(@RequestBody JWithdrawal jWithdrawal,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jWithdrawal.setCheckStatus(248);
            jWithdrawal.setPayId(jAdmin.getAdminId());
            jWithdrawal.setPayDate(new Date());
            JWithdrawal jWithdrawal1 = jWithdrawalMapper.selectById(Long.valueOf(jWithdrawal.getWithdrawalId()));
            jWithdrawal.setRemark(jWithdrawal1.getRemark()+" "+jWithdrawal.getRemark());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jWithdrawalMapper.updateById(jWithdrawal));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody WithdrawalReq withdrawalReq, ServletRequest request) {
        try {
            withdrawalReq.setPage(withdrawalReq.getSize() * (withdrawalReq.getPage() - 1));
            Map map = JsonUtil.Obj2Map(withdrawalReq);
            map.put("startDate", new Date((Long) map.get("startDate")));
            map.put("endDate", new Date((Long) map.get("endDate")));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jWithdrawalMapper.getWithdrawal(map),jWithdrawalMapper.getWithdrawalCount(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/apply/data")
    public Object findApplyData(@RequestBody Map map, ServletRequest request) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jWithdrawalMapper.selectByMap(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 审核通过
     * @param jWithdrawal2
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Object check(@RequestBody JWithdrawal jWithdrawal2, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            JWithdrawal jWithdrawal = jWithdrawalMapper.selectById(Long.valueOf(jWithdrawal2.getWithdrawalId()));
            jWithdrawal.setCheckStatus(245);
            jWithdrawal.setCheckId(jAdmin.getAdminId());
            jWithdrawal.setCheckDate(new Date());
            JWithdrawal jWithdrawal1 = jWithdrawalMapper.selectById(Long.valueOf(jWithdrawal.getWithdrawalId()));
            jWithdrawal.setRemark(jWithdrawal1.getRemark()+" "+jWithdrawal.getRemark());
            jWithdrawalMapper.updateById(jWithdrawal);

            /**客户提现*/
            if (jWithdrawal.getApplyType()==249){
                JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jWithdrawal.getApplyId()));
                jCustomer.setCustomerBalance(jCustomer.getCustomerBalance().subtract(jWithdrawal.getMoney()));
                /***客户对账单*/
                JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jWithdrawal.getApplyId()),
                        jWithdrawal.getApplyId(), 0, 0, 0,
                        163, 0, 0, "", 0.0, new Date(),
                        new Date(), 41,jWithdrawal.getMoney(), 0, jAdmin.getAdminId(), 49, 53, jCustomer.getCustomerBalance(), "", "");
                jCustomerStatmentMapper.insert(jCustomerStatment);
                jCustomerMapper.updateById(jCustomer);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
