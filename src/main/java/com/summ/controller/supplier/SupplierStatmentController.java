package com.summ.controller.supplier;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.request.SupplierStatmentDetailReq;
import com.summ.model.request.TeacherStatmentDetailReq;
import com.summ.model.response.ModelRes;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * Created by summ on 18/1/18.
 */
@Controller
@RequestMapping("/supplier/statment")
public class SupplierStatmentController extends AutoMapperController{

    /**
     * 带教对账单明细
     * @param supplierStatmentDetailReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/find/detail")
    public Object findDetail(@RequestBody SupplierStatmentDetailReq supplierStatmentDetailReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            supplierStatmentDetailReq.setAdminId(jAdmin.getAdminId());
            supplierStatmentDetailReq.setPage(supplierStatmentDetailReq.getSize() * (supplierStatmentDetailReq.getPage()-1));
            Map<String, Object> map = new HashedMap();
            map.put("count", jSupplierStatmentMapper.getSupplierStatmentDetailCount(supplierStatmentDetailReq));
            map.put("list", jSupplierStatmentMapper.getSupplierStatmentDetail(supplierStatmentDetailReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
