package com.summ.controller.customer;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.CustomerStatmentReq;
import com.summ.model.response.CustomerStatmentRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.ExcelUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 客户对账单
 *
 * @author summ
 * @date 17/11/29
 */
@Controller
@RequestMapping("/customer/statment")
public class CustomerStatmentController extends AutoMapperController {

    /**
     * CRUD for customer statment
     *
     * @param jCustomerStatment
     * @return
     */

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JCustomerStatment jCustomerStatment, HttpServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jCustomerStatment.setAdminId(jAdmin.getAdminId());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功!", jCustomerStatmentMapper.insert(jCustomerStatment));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody CustomerStatmentReq customerStatmentReq) {
        try {
            CustomerStatmentRes customerStatm = new CustomerStatmentRes();
            customerStatm.getStatmentId();
            customerStatmentReq.setPage(customerStatmentReq.getSize() * (customerStatmentReq.getPage() - 1));
            Map map = new HashedMap();
            map.put("count", jCustomerStatmentMapper.getCount(customerStatmentReq));
            map.put("list", jCustomerStatmentMapper.getStatmentList(customerStatmentReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/detail")
    public Object findDetail(@RequestBody JCustomerStatment jCustomerStatment) {
        try {
            CustomerStatmentRes customerStatmentRes = jCustomerStatmentMapper.getStatmentDetail(jCustomerStatment.getStatmentId());
            System.out.println(customerStatmentRes.toString());
            /**根据对账单里的服务师类型查找不同表的信息*/
            if (null != customerStatmentRes) {
                if (null != customerStatmentRes.getSupplierId()) {
                    switch (customerStatmentRes.getSupplierId()) {
                        /**自营服务师*/
                        case 1:
                            JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(customerStatmentRes.getNannyId()));
                            customerStatmentRes.setNannyName(jNannyInfo.getNannyName());
                            break;
                        /**自营带教*/
                        case -1:
                            JTeacher jTeacher = jTeacherMapper.selectById(Long.valueOf(customerStatmentRes.getNannyId()));
                            customerStatmentRes.setNannyName(jTeacher.getTeacherName());
                            break;
                        /**供应商*/
                        default:
                            JSupplier jSupplier = jSupplierMapper.selectById(Long.valueOf(customerStatmentRes.getNannyId()));
                            JCompanyData jCompanyData = jCompanyDataMapper.selectById(Long.valueOf(jSupplier.getCompanyId()));
                            customerStatmentRes.setNannyName(jCompanyData.getCompanyName());
                            break;
                    }
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", customerStatmentRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 导出
     * @param customerStatmentReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/export")
    public String export(@RequestBody CustomerStatmentReq customerStatmentReq) {
        try {
            return ExcelUtil.ExportCustomerStatment(jCustomerStatmentMapper.getStatmentList(customerStatmentReq));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
