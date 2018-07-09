package com.summ.controller.customer;

import com.summ.controller.basic.AutoMapperController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  废弃
 * Created by summ on 18/1/27.
 */
@Controller
@RequestMapping("/customer/message")
public class CustomerMessageController extends AutoMapperController{

//    @ResponseBody
//    @RequestMapping(value = "/insert")
//    public Object insert(@RequestBody Map map, ServletRequest request) {
//        try {
//            JAdmin admin = (JAdmin) request.getAttribute("admin");
//            String content = (String) map.get("content");
//            Integer customerId = (Integer) map.get("customerId");
//            Integer orderId = (Integer) map.get("orderId");
//            Integer scheduleId = (Integer) map.get("scheduleId");
//            List<Integer> list = (List<Integer>) map.get("department");
//
//            JCustomerFeedback jCustomerFeedback = new JCustomerFeedback();
//            jCustomerFeedback.setContent(content);
//            jCustomerFeedback.setCustomerId(customerId);
//            jCustomerFeedback.setNoteAdmin(admin.getAdminId());
//            jCustomerFeedback.setOrderId(orderId);
//            jCustomerFeedback.setScheduleId(scheduleId);
//            jCustomerFeedbackMapper.insert(jCustomerFeedback);
//
//            List<JCustomerFeedbackDepartment> jCustomerFeedbackList = new ArrayList<JCustomerFeedbackDepartment>();
//            for (Integer ii : list){
//                JCustomerFeedbackDepartment jCustomerFeedbackDepartment = new JCustomerFeedbackDepartment(jCustomerFeedback.getFeedbackId(),ii);
//                jCustomerFeedbackList.add(jCustomerFeedbackDepartment);
//            }
//            jCustomerFeedbackDepartmentMapper.insertBatch(jCustomerFeedbackList);
//            return new ModelRes(ModelRes.Status.SUCCESS, "添加客户反馈 !",null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ModelRes(ModelRes.Status.ERROR, "server err !");
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/find")
//    public Object find(@RequestBody CustomerFeedbackReq customerFeedbackRes,ServletRequest request) {
//        try {
//            JAdmin admin = (JAdmin) request.getAttribute("admin");
//            customerFeedbackRes.setPage((customerFeedbackRes.getPage()-1)*customerFeedbackRes.getSize());
//            Map map = new HashedMap();
//            map.put("count",jCustomerFeedbackMapper.getCustomerFeedbackCount(customerFeedbackRes));
//            map.put("list",jCustomerFeedbackMapper.getCustomerFeedbackList(customerFeedbackRes));
//            return new ModelRes(ModelRes.Status.SUCCESS, "查找客户反馈列表 !",map);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ModelRes(ModelRes.Status.ERROR, "server err !");
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/update")
//    public Object update(@RequestBody JCustomerFeedback jCustomerFeedback) {
//        try {
//            return new ModelRes(ModelRes.Status.SUCCESS, "更新客户反馈 !",jCustomerFeedbackMapper.updateById(jCustomerFeedback));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ModelRes(ModelRes.Status.ERROR, "server err !");
//        }
//    }
//
//    /**
//     * 客户反馈详情以及管理员跟踪记录的列表
//     * @param jCustomerFeedback
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/follow/find")
//    public Object detail(@RequestBody JCustomerFeedback jCustomerFeedback) {
//        try {
//            CustomerFeedbackRes customerFeedbackRes = jCustomerFeedbackMapper.getCustomerFeedbackDetail(jCustomerFeedback.getFeedbackId());
//            List<CustomerFeedbackFollowRes> list = jCustomerFeedbackFollowMapper.getCustomerFeedbackFollowList(jCustomerFeedback.getFeedbackId());
//            if (list.size()>0){
//                customerFeedbackRes.setCustomerFeedbackFollowResList(list);
//            }
//            return new ModelRes(ModelRes.Status.SUCCESS, "查找客户反馈详情 !",customerFeedbackRes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ModelRes(ModelRes.Status.ERROR, "server err !");
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/follow/insert")
//    public Object detail(@RequestBody JCustomerFeedbackFollow jCustomerFeedbackFollow,ServletRequest request) {
//        try {
//            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
//            jCustomerFeedbackFollow.setAdminId(jAdmin.getAdminId());
//            return new ModelRes(ModelRes.Status.SUCCESS, "添加反馈跟踪 !",jCustomerFeedbackFollowMapper.insert(jCustomerFeedbackFollow));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ModelRes(ModelRes.Status.ERROR, "server err !");
//        }
//    }



}
