package com.summ.controller.invoice;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JInvoice;
import com.summ.model.JSupplierInvoice;
import com.summ.model.request.InvoiceReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.JsonUtil;
import com.summ.utils.ResponseUtil;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 发票模块
 * @author summ
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController extends AutoMapperController{

    /**
     * 查找公司信息
     * @param jInvoice
     * @return
     */
    @ResponseBody
    @RequestMapping("/company")
    public Object getCompany(@RequestBody JInvoice jInvoice) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"",ResponseUtil.List2Map(jInvoiceMapper.getCompany()));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 添加开票申请
     * @param jInvoice
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JInvoice jInvoice, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jInvoice.setCreateId(jAdmin.getAdminId());
            jInvoice.setCreateDate(new Date());
            jInvoice.setInvoiceDate(new Date());
            jInvoice.setInvoicingId(jAdmin.getAdminId());
            jInvoice.setInvoiceStatus(262);
            return new ModelRes(ModelRes.Status.SUCCESS,"",jInvoiceMapper.insert(jInvoice));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JInvoice jInvoice) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"",jInvoiceMapper.updateById(jInvoice));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody InvoiceReq invoiceReq) {
        try {
            invoiceReq.setPage(invoiceReq.getSize() * (invoiceReq.getPage() - 1));
            Map map = JsonUtil.Obj2Map(invoiceReq);
            map.put("startDate", new Date((Long) map.get("startDate")));
            map.put("endDate", new Date((Long) map.get("endDate")));

            return new ModelRes(ModelRes.Status.SUCCESS,"", ResponseUtil.List2Map(jInvoiceMapper.getInvoice(map),jInvoiceMapper.getInvoiceCount(map)));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
