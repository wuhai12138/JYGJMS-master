package com.summ.controller.order;


import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.OrderMonthPrepaidReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.OrderMonthPrepaidRes;
import com.summ.utils.OrderUtil;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/order/month/prepaid")
public class OrderMonthPrepaidController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/pay")
    public Object pay(@RequestBody JOrderMonthPrepaid map, ServletRequest request) {
        try {
            JOrderMonthPrepaid jOrderMonthPrepaid = jOrderMonthPrepaidMapper.selectById(map.getPrepaidId());
            JOrderMonth jOrderMonth = jOrderMonthMapper.selectById(jOrderMonthPrepaid.getOrderId());
            JCustomer jCustomer = jCustomerMapper.selectById(jOrderMonth.getCustomerId());
            JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(jOrderMonthPrepaid.getNannyId());
            JDictInfo jDictInfo = jDictInfoMapper.selectById(jNannyInfo.getNannyLevel());
            BigDecimal customerBanlance = jCustomer.getCustomerBalance();
            if (customerBanlance.compareTo(jOrderMonthPrepaid.getOrderMoney())==-1){
                return new ModelRes(ModelRes.Status.FAILED, "客户余额不足请充值  !", null);
            }
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            customerBanlance = customerBanlance.subtract(jOrderMonthPrepaid.getOrderMoney());
            JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jOrderMonthPrepaid.getCustomerId()),
                    jOrderMonthPrepaid.getCustomerId(),jOrderMonth.getGoodsId(),jOrderMonth.getHouseId(),jOrderMonth.getOrderId(),
                    277,0,jOrderMonth.getShopId(),"",0.00,null,new Date(),278,jOrderMonthPrepaid.getOrderMoney(),
                    48,jAdmin.getAdminId(),49,53,customerBanlance,"","");
            jCustomer.setCustomerBalance(customerBanlance);
            JNannyStatment jNannyStatment = new JNannyStatment(OrderUtil.generateStamentNumber(jOrderMonthPrepaid.getNannyId()),
                    jOrderMonthPrepaid.getNannyId(),0,jOrderMonth.getOrderId(),jOrderMonth.getShopId(),jOrderMonth.getHouseId(),
                    jOrderMonth.getCustomerId(),279,jOrderMonthPrepaid.getNannySalary(),277,jOrderMonth.getGoodsId(),"",
                    0.00,null,"",jAdmin.getAdminId(),jNannyInfo.getNannyLevel(),new BigDecimal(jDictInfo.getInfo()),1);
            jOrderMonthPrepaid.setModifyDate(new Date());
            jOrderMonthPrepaid.setModifyId(jAdmin.getAdminId());
            jOrderMonthPrepaid.setPayStatus(53);
            jOrderMonth.setPayStatus(53);
            jOrderMonth.setModifyTime(new Date());
            jOrderMonth.setModifyId(jAdmin.getAdminId());

            jCustomerStatmentMapper.insert(jCustomerStatment);
            jNannyStatmentMapper.insert(jNannyStatment);
            jCustomerMapper.updateById(jCustomer);
            jOrderMonthMapper.updateById(jOrderMonth);
            jOrderMonthPrepaidMapper.updateById(jOrderMonthPrepaid);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderMonthPrepaidReq orderMonthPrepaidReq, ServletRequest request) {
        try {
            orderMonthPrepaidReq.setPage((orderMonthPrepaidReq.getPage()-1)*orderMonthPrepaidReq.getSize());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(jOrderMonthPrepaidMapper.getPrepaidList(orderMonthPrepaidReq),jOrderMonthPrepaidMapper.getPrepaidListCount(orderMonthPrepaidReq)));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody final JOrderMonthPrepaid map, ServletRequest request) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderMonthPrepaidMapper.updateById(map));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody final JOrderMonthPrepaid map, ServletRequest request) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderMonthPrepaidMapper.insert(map));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
