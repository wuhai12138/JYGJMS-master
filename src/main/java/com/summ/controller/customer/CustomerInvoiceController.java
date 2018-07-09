package com.summ.controller.customer;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JCustomer;
import com.summ.model.JCustomerInvoice;
import com.summ.model.JLeaguerInvoice;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 客户发票
 * @author summ
 */
@Controller
@RequestMapping("/customer/invoice")
public class CustomerInvoiceController extends AutoMapperController{

    /**
     * 获取客户最大发票金额(订单总金额减去开票总金额)
     * @param jCustomer
     * @return
     */
    @ResponseBody
    @RequestMapping("/maximum")
    public Object maximum(@RequestBody JCustomer jCustomer) {
        try {
            List<JCustomerInvoice> jCustomerInvoiceList = jCustomerInvoiceMapper.selectList(new EntityWrapper<JCustomerInvoice>());
            if (jCustomerInvoiceList.size()==0){
                return new ModelRes(ModelRes.Status.SUCCESS,"", jCustomerInvoiceMapper.getCustomerPayMoney(jCustomer.getCustomerId()));
            }else {
                return new ModelRes(ModelRes.Status.SUCCESS,"", jCustomerInvoiceMapper.getCustomerMaxInvoiceMoney(jCustomer.getCustomerId()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JCustomerInvoice jCustomerInvoice) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"",jCustomerInvoiceMapper.insert(jCustomerInvoice));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody JCustomerInvoice jCustomerInvoice) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"", ResponseUtil.List2Map(jCustomerInvoiceMapper.selectList(new EntityWrapper<JCustomerInvoice>())));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
