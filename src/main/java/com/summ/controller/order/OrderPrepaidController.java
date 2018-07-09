package com.summ.controller.order;

import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JCustomerPrepaidMapper;
import com.summ.model.JAdmin;
import com.summ.model.JCustomerPrepaid;
import com.summ.model.JGoodsContract;
import com.summ.model.request.CustomerPrepaidReq;
import com.summ.model.request.GoodsReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import java.util.Date;

/**
 * 小程序客户预约订单模块
 */
@RestController
@RequestMapping("/order/prepaid")
public class OrderPrepaidController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/find")
    public Object insert(@RequestBody CustomerPrepaidReq customerPrepaidReq) {
        try {
            customerPrepaidReq.setPage((customerPrepaidReq.getPage()-1)*customerPrepaidReq.getSize());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jCustomerPrepaidMapper.getCustomerPrepaidList(customerPrepaidReq),jCustomerPrepaidMapper.getCustomerPrepaidListCount(customerPrepaidReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JCustomerPrepaid jCustomerPrepaid, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jCustomerPrepaid.setModifyDate(new Date());
            jCustomerPrepaid.setModifyId(jAdmin.getAdminId());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !",jCustomerPrepaidMapper.updateById(jCustomerPrepaid));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
