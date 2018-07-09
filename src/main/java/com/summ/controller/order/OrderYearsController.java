package com.summ.controller.order;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.OrderContractReq;
import com.summ.model.request.OrderYearsReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.OrderContractRes;
import com.summ.model.response.OrderYearsRes;
import com.summ.utils.*;
import com.summ.utils.mongodb.MongoDBUtil;
import com.sun.jdi.LongValue;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 包年订单
 * @author summ
 */
@Controller
@RequestMapping("order/years")
public class OrderYearsController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JOrderYears jOrderYears, @RequestBody Map mapService, ServletRequest request) {
        try {
            /**添加或更新服务计划书*/
            JCustomerHouse jCustomerHouse = jCustomerHouseMapper.selectById(Long.valueOf(jOrderYears.getHouseId()));
            JCustomerService jCustomerService = new JCustomerService();
            //如果该房产已有服务计划书，则更新该计划书
            if (jCustomerHouse.getServiceId() != 0) {
                jCustomerService.setServiceId(jCustomerHouse.getServiceId());
            }
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jCustomerService.setAdminId(jAdmin.getAdminId());
            jCustomerService.setCustomerId(jOrderYears.getCustomerId());
            jCustomerService.setHouseId(jOrderYears.getHouseId());
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
                        stringBufferServiceDetail.append("周一,");
                        weekList.add("周一");
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
            for (int i = 0; i < times.size(); i++) {
                stringBufferServiceDetail.append(NannyWorkTimeUtil.id2Time((Integer) times.get(i).get("startId")));
                stringBufferServiceDetail.append("-");
                stringBufferServiceDetail.append(NannyWorkTimeUtil.id2Time((Integer) times.get(i).get("endId")));
            }
            jCustomerService.setServiceDetail(stringBufferServiceDetail.toString());

            /**如果有服务计划书则更新 否则新增*/
            if (jCustomerHouse.getServiceId() == 0) {
                jCustomerServiceMapper.insert(jCustomerService);
                jCustomerHouse.setServiceId(jCustomerService.getServiceId());
                jCustomerHouseMapper.updateById(jCustomerHouse);
            } else {
                jCustomerServiceMapper.updateById(jCustomerService);
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

            jOrderYears.setServiceId(jCustomerService.getServiceId());
            /**订单处理状态为待处理*/
            jOrderYears.setOrderStatus(226);
            jOrderYears.setCreateId(jAdmin.getAdminId());
            jOrderYears.setCreateTime(new Date());
            jOrderYears.setShopId(jCustomerHouse.getShopId());
            /**计算订单年费*/
            if (jOrderYears.getSalary().compareTo(new BigDecimal(7000)) == 1){
                jOrderYears.setServiceFees(new BigDecimal(500*jOrderYears.getMonth()));
            }else {
                jOrderYears.setServiceFees(new BigDecimal(300*jOrderYears.getMonth()));
            }
            jOrderYears.setAgencyFees(jOrderYears.getSalary().multiply(new BigDecimal(0.2)));

            /**计算服务费*/
            jOrderYearsMapper.insert(jOrderYears);
            /**发送短信给门店手机号*/
            JShop jShop = jShopMapper.selectById(Long.valueOf(jOrderYears.getShopId()));
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderYears.getCustomerId()));
            SendSMSUtil.notifyShop(jCustomer.getCustomerPhone(), jShop.getShopMobile(), jCustomer.getCustomerName());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderYearsReq orderYearsReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            orderYearsReq.setAdminId(jAdmin.getAdminId());
            orderYearsReq.setPage(orderYearsReq.getSize() * (orderYearsReq.getPage() - 1));
            List<OrderYearsRes> orderYearsResList = jOrderYearsMapper.getOrderYearsList(orderYearsReq);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(orderYearsResList,jOrderYearsMapper.getOrderYearsListCount(orderYearsReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JOrderYears jOrderYears, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jOrderYears.setModifyTime(new Date());
            jOrderYears.setModifyId(jAdmin.getAdminId());
            /**判断有没有修改年费*/
            JOrderYears jOrderYears1 = jOrderYearsMapper.selectById(Long.valueOf(jOrderYears.getOrderId()));
            if (jOrderYears.getSalary()!=null){
                /**计算订单年费*/
                if (jOrderYears.getSalary().compareTo(new BigDecimal(7000)) == 1){
                    jOrderYears.setServiceFees(new BigDecimal(500*jOrderYears1.getMonth()));
                }else {
                    jOrderYears.setServiceFees(new BigDecimal(300*jOrderYears1.getMonth()));
                }
                jOrderYears.setAgencyFees(jOrderYears.getSalary().multiply(new BigDecimal(0.2)));
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderYearsMapper.updateById(jOrderYears));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 包年订单关闭（暂不做退款）
     * @param jOrderYears
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/close")
    public Object close(@RequestBody JOrderYears jOrderYears, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            List<JOrderSchedule> jOrderScheduleList = jOrderScheduleMapper.getOrderScheduleToBeFinished(jOrderYears.getOrderId(), 165);
            if (jOrderScheduleList.size() > 0) {
                for (JOrderSchedule jOrderSchedule : jOrderScheduleList) {
                    jOrderSchedule.setCancelTime(new Date());
                    jOrderSchedule.setCancelId(jAdmin.getAdminId());
                    jOrderSchedule.setRemark(StringUtil.isBlank(jOrderSchedule.getRemark())?"关闭原因：" + jOrderYears.getRemark():jOrderSchedule.getRemark() + ",关闭原因：" + jOrderYears.getRemark());
                    jOrderSchedule.setScheduleStatus(155);
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                }
            }
            JOrderYears jOrderYears1 = jOrderYearsMapper.selectById(Long.valueOf(jOrderYears.getOrderId()));
            jOrderYears1.setOrderCloseStatus(213);
            jOrderYears1.setModifyId(jAdmin.getAdminId());
            jOrderYears1.setModifyTime(new Date());
            jOrderYears1.setRemark(StringUtil.isBlank(jOrderYears1.getRemark())?"关闭原因：" + jOrderYears.getRemark():jOrderYears1.getRemark() + ",关闭原因：" + jOrderYears.getRemark());
            jOrderYearsMapper.updateById(jOrderYears1);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 管家查看订单，订单状态从待处理改至预约中
     *
     * @param jOrderYears1
     * @return
     */
    @ResponseBody
    @RequestMapping("/dispose")
    public Object dispose(@RequestBody JOrderYears jOrderYears1, ServletRequest request) {
        try {
            JOrderYears jOrderYears = jOrderYearsMapper.selectById(Long.valueOf(jOrderYears1.getOrderId()));
            if (226==jOrderYears.getOrderStatus()){
                JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
                jOrderYears.setModifyId(jAdmin.getAdminId());
                jOrderYears.setOrderStatus(138);
                jOrderYears.setModifyTime(new Date());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderYearsMapper.updateById(jOrderYears));
            }else {
                return new ModelRes(ModelRes.Status.FAILED, "订单已处理  !", jOrderYears);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/pay")
    public Object pay(@RequestBody JOrderYears map, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            JOrderYears jOrderYears = jOrderYearsMapper.selectById(Long.valueOf(map.getOrderId()));
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderYears.getCustomerId()));
            jCustomer.setCustomerBalance(jCustomer.getCustomerBalance().subtract(jOrderYears.getServiceFees()));

            JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jOrderYears.getCustomerId()),
                    jOrderYears.getCustomerId(),jOrderYears.getGoodsId(), jOrderYears.getHouseId(),
                    jOrderYears.getOrderId(),165,0,
                    jOrderYears.getShopId(), "", 0.0,
                    jOrderYears.getStartDate(), new Date(), 240, jOrderYears.getServiceFees(),
                    48, jAdmin.getAdminId(), 49, 53, jCustomer.getCustomerBalance(),
                    "", "");
            jOrderYears.setModifyId(jAdmin.getAdminId());
            jOrderYears.setModifyTime(new Date());
            jOrderYears.setPayStatus(236);

            jCustomerMapper.updateById(jCustomer);
            jOrderYearsMapper.updateById(jOrderYears);
            jCustomerStatmentMapper.insert(jCustomerStatment);
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
            map.put("orderType", 165);
            map.put("isDel",16);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(jGoodsContractMapper.selectByMap(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
