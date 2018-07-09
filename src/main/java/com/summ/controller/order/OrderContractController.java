package com.summ.controller.order;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JOrderContractMapper;
import com.summ.model.*;
import com.summ.model.request.OrderContractReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.OrderContractRes;
import com.summ.utils.*;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.*;

/**
 * 合同订单
 *
 * @author summ
 * @date 18/1/11
 */
@Controller
@RequestMapping("/order/contract")
public class OrderContractController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JOrderContract jOrderContract, @RequestBody Map mapService, ServletRequest request) {
        try {

            /**添加或更新服务计划书*/
            JCustomerHouse jCustomerHouse = jCustomerHouseMapper.selectById(Long.valueOf(jOrderContract.getHouseId()));
            JCustomerService jCustomerService = new JCustomerService();
            //如果该房产已有服务计划书，则更新该计划书
            if (jCustomerHouse.getServiceId() != 0) {
                jCustomerService.setServiceId(jCustomerHouse.getServiceId());
            }
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jCustomerService.setAdminId(jAdmin.getAdminId());
            jCustomerService.setCustomerId(jOrderContract.getCustomerId());
            jCustomerService.setHouseId(jOrderContract.getHouseId());
            jCustomerService.setShopId(jCustomerHouse.getShopId());

            //将服务计划书简略信息存入mysql，详细信息存入mongodb
            Map mapJson = JsonUtil.json2Map((String) mapService.get("serviceDetail"));
            List<Map> weeks = (List<Map>) mapJson.get("weekListTime");
            List<Map> times = (List<Map>) mapJson.get("addTime");
            StringBuffer stringBufferServiceDetail = new StringBuffer();
            Map<String, List<String>> serviceDetailMap = new HashedMap();
            List weekList = new ArrayList();
            for (int i = 0; i < weeks.size(); i++) {
                if ("true".equals(weeks.get(i).get("active").toString())) {
                    if ("周一".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周一");
                        stringBufferServiceDetail.append("周一,");
                    }
                    if ("周二".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周二");
                        stringBufferServiceDetail.append("周二,");
                    }
                    if ("周三".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周三");
                        stringBufferServiceDetail.append("周三,");
                    }
                    if ("周四".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周四");
                        stringBufferServiceDetail.append("周四,");
                    }
                    if ("周五".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周五");
                        stringBufferServiceDetail.append("周五,");
                    }
                    if ("周六".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周六");
                        stringBufferServiceDetail.append("周六,");
                    }
                    if ("周天".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周天");
                        stringBufferServiceDetail.append("周日,");
                    }
                }
            }
            serviceDetailMap.put("week", weekList);
            List timeList = new ArrayList();
            for (int i = 0; i < times.size(); i++) {
                stringBufferServiceDetail.append(NannyWorkTimeUtil.id2Time((Integer) times.get(i).get("startId")));
                stringBufferServiceDetail.append("-");
                stringBufferServiceDetail.append(NannyWorkTimeUtil.id2Time((Integer) times.get(i).get("endId")));
            }
            jCustomerService.setServiceDetail(stringBufferServiceDetail.toString());

            /**如果有服务计划书则更新 否则新增*/
            if (jCustomerHouse.getServiceId() != 0) {
                jCustomerServiceMapper.updateById(jCustomerService);
            } else {
                jCustomerServiceMapper.insert(jCustomerService);
                jCustomerHouse.setServiceId(jCustomerService.getServiceId());
                jCustomerHouseMapper.updateById(jCustomerHouse);
            }

            Map map = new HashedMap();
            map.put("serviceDetail", mapService.get("serviceDetail"));
            map.put("serviceId", jCustomerService.getServiceId());
            System.out.println("map>>>>>>>>>>>>>>>>>>>" + map.toString());
            mongoDBUtil = MongoDBUtil.getInstance(mongoConfig);
            /**如果有服务计划书则更新 否则新增*/
            if (jCustomerHouse.getServiceId() != 0) {
                Map serviceIdMap = new HashMap();
                serviceIdMap.put("serviceId", jCustomerService.getServiceId());
                mongoDBUtil.delete("customer_service", serviceIdMap);
                mongoDBUtil.insert(map, "customer_service");
            } else {
                mongoDBUtil.insert(map, "customer_service");
            }

//            Map map = new HashMap();
//            map.put("houseId",jOrderContract.getHouseId());
//            List<JCustomerService> jCustomerServiceList = jCustomerServiceMapper.selectByMap(map);
//            if (jCustomerServiceList.size()==0){
//                return new ModelRes(ModelRes.Status.ERROR, "服务计划书不存在！");
//            }
//            JCustomerService jCustomerService = jCustomerServiceList.get(0);
            jOrderContract.setServiceId(jCustomerService.getServiceId());
            /**订单处理状态为待处理*/
            jOrderContract.setOrderStatus(226);
            jOrderContract.setCreateId(jAdmin.getAdminId());
//            jOrderContract.setCreateTime(new Date());
            jOrderContract.setShopId(jCustomerHouse.getShopId());
            jOrderContractMapper.insert(jOrderContract);
            /**发送短信给门店手机号*/
            JShop jShop = jShopMapper.selectById(Long.valueOf(jOrderContract.getShopId()));
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderContract.getCustomerId()));
            SendSMSUtil.notifyShop(jCustomer.getCustomerPhone(), jShop.getShopMobile(), jCustomer.getCustomerName());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderContractReq orderContractReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            orderContractReq.setAdminId(jAdmin.getAdminId());
            orderContractReq.setPage(orderContractReq.getSize() * (orderContractReq.getPage() - 1));
            List<OrderContractRes> orderContractResList = jOrderContractMapper.getContractList(orderContractReq);
            Map map = ResponseUtil.List2Map(orderContractResList);
            map.put("count", jOrderContractMapper.getContractCount(orderContractReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JOrderContract jOrderContract, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jOrderContract.setModifyId(jAdmin.getAdminId());
            jOrderContract.setModifyTime(new Date());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderContractMapper.updateById(jOrderContract));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 管家查看订单，订单状态从待处理改至预约中
     *
     * @param jOrderContract1
     * @return
     */
    @ResponseBody
    @RequestMapping("/dispose")
    public Object dispose(@RequestBody JOrderContract jOrderContract1, ServletRequest request) {
        try {
            JOrderContract jOrderContract = jOrderContractMapper.selectById(Long.valueOf(jOrderContract1.getOrderId()));
            if (226==jOrderContract.getOrderStatus()){
                JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
                jOrderContract.setOrderStatus(138);
                jOrderContract.setModifyId(jAdmin.getAdminId());
                jOrderContract.setModifyTime(new Date());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderContractMapper.updateById(jOrderContract));
            }else {
                return new ModelRes(ModelRes.Status.FAILED, "订单已处理  !",jOrderContract);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 订单关闭
     *
     * @param jOrderContract
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/close")
    public Object close(@RequestBody JOrderContract jOrderContract, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");


            List<JOrderSchedule> jOrderScheduleList = jOrderScheduleMapper.getOrderScheduleUnCheckOut(jOrderContract.getOrderId(), 163);
            if (jOrderScheduleList.size() > 0) {
                for (JOrderSchedule jOrderSchedule : jOrderScheduleList) {
                    jOrderSchedule.setCancelTime(new Date());
                    jOrderSchedule.setCancelId(jAdmin.getAdminId());
                    jOrderSchedule.setScheduleStatus(155);
                    jOrderSchedule.setRemark(jOrderSchedule.getRemark() + " 关闭原因：" + jOrderContract.getRemark());
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                }
            }
            JOrderContract jOrderContract1 = jOrderContractMapper.selectById(Long.valueOf(jOrderContract.getOrderId()));
            jOrderContract1.setOrderCloseStatus(213);
            jOrderContract1.setModifyId(jAdmin.getAdminId());
            jOrderContract1.setModifyTime(new Date());
            jOrderContract1.setRemark(StringUtil.isBlank(jOrderContract1.getRemark())?"关闭原因：" + jOrderContract.getRemark():jOrderContract1.getRemark()+",关闭原因：" + jOrderContract.getRemark());
            jOrderContractMapper.updateById(jOrderContract1);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/goods/find")
    public Object findGoods(@RequestBody OrderContractReq orderContractReq) {
        try {
            Map map = new HashMap();
            map.put("orderType", 163);
            map.put("isDel",16);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(jGoodsContractMapper.selectByMap(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
