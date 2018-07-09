package com.summ.controller.order;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.OrderContractReq;
import com.summ.model.request.OrderMonthReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.OrderMonthRes;
import com.summ.utils.*;
import com.summ.utils.mongodb.MongoDBUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 包月订单
 */
@Controller
@RequestMapping("/order/month")
public class OrderMonthController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JOrderMonth jOrderMonth, @RequestBody Map mapService, ServletRequest request) {
        try {
            /**添加或更新服务计划书*/
            JCustomerHouse jCustomerHouse = jCustomerHouseMapper.selectById(Long.valueOf(jOrderMonth.getHouseId()));
            JCustomerService jCustomerService = new JCustomerService();
            //如果该房产已有服务计划书，则更新该计划书
            if (jCustomerHouse.getServiceId() != 0) {
                jCustomerService.setServiceId(jCustomerHouse.getServiceId());
            }
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jCustomerService.setAdminId(jAdmin.getAdminId());
            jCustomerService.setHouseId(jOrderMonth.getHouseId());
            jCustomerService.setCustomerId(jOrderMonth.getCustomerId());
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
                    if ("周五".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周五");
                        stringBufferServiceDetail.append("周五,");
                    }
                    if ("周四".equals(weeks.get(i).get("value").toString())) {
                        weekList.add("周四");
                        stringBufferServiceDetail.append("周四,");
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

            jOrderMonth.setServiceId(jCustomerService.getServiceId());
            /**订单处理状态为待处理*/
            jOrderMonth.setOrderStatus(226);
            jOrderMonth.setCreateId(jAdmin.getAdminId());
            jOrderMonth.setCreateTime(new Date());
            jOrderMonth.setShopId(jCustomerHouse.getShopId());

            /**计算服务费*/
            jOrderMonthMapper.insert(jOrderMonth);
            /**发送短信给门店手机号*/
            JShop jShop = jShopMapper.selectById(Long.valueOf(jOrderMonth.getShopId()));
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderMonth.getCustomerId()));
            SendSMSUtil.notifyShop(jCustomer.getCustomerPhone(), jShop.getShopMobile(), jCustomer.getCustomerName());

            /**生成预支付订单表信息*/

            Calendar prepaidDate = Calendar.getInstance();
            Integer day = (Integer) mapService.get("prepaidDate");
            prepaidDate.setTimeInMillis(jOrderMonth.getStartDate().getTime());
            if (day>=prepaidDate.get(Calendar.DAY_OF_MONTH)){
                prepaidDate.set(Calendar.DAY_OF_MONTH,day);
            }else {
                prepaidDate.set(Calendar.DAY_OF_MONTH,day);
                prepaidDate.add(Calendar.MONTH,1);
            }
            for (int i = 0; i < jOrderMonth.getMonth(); i++) {
                JOrderMonthPrepaid jOrderMonthPrepaid = new JOrderMonthPrepaid();
                jOrderMonthPrepaid.setOrderId(jOrderMonth.getOrderId());
                jOrderMonthPrepaid.setCreateDate(new Date());
                jOrderMonthPrepaid.setCreateId(jAdmin.getAdminId());
                jOrderMonthPrepaid.setCustomerId(jOrderMonth.getCustomerId());
                jOrderMonthPrepaid.setNannySalary(new BigDecimal((String) mapService.get("nannySalary")));
                jOrderMonthPrepaid.setOrderMoney(new BigDecimal((String) mapService.get("orderMoney")));
                prepaidDate.add(Calendar.MONTH, i);
                jOrderMonthPrepaid.setPrepaidDate(prepaidDate.getTime());
                jOrderMonthPrepaidMapper.insert(jOrderMonthPrepaid);
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderMonthReq orderMonthReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            orderMonthReq.setAdminId(jAdmin.getAdminId());
            orderMonthReq.setPage(orderMonthReq.getSize() * (orderMonthReq.getPage() - 1));
            List<OrderMonthRes> orderMonthList = jOrderMonthMapper.getOrderMonthList(orderMonthReq);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(orderMonthList, jOrderMonthMapper.getOrderMonthListCount(orderMonthReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JOrderMonth jOrderMonth, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jOrderMonth.setModifyTime(new Date());
            jOrderMonth.setModifyId(jAdmin.getAdminId());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderMonthMapper.updateById(jOrderMonth));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 管家查看订单，订单状态从待处理改至预约中
     *
     * @param jOrderMonth1
     * @return
     */
    @ResponseBody
    @RequestMapping("/dispose")
    public Object dispose(@RequestBody JOrderMonth jOrderMonth1, ServletRequest request) {
        try {
            JOrderMonth jOrderMonth = jOrderMonthMapper.selectById(Long.valueOf(jOrderMonth1.getOrderId()));
            if (226 == jOrderMonth.getOrderStatus()) {
                JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
                jOrderMonth.setModifyId(jAdmin.getAdminId());
                jOrderMonth.setModifyTime(new Date());
                jOrderMonth.setOrderStatus(138);
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jOrderMonthMapper.updateById(jOrderMonth));
            } else {
                return new ModelRes(ModelRes.Status.FAILED, "订单已处理  !", jOrderMonth);
            }
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
            map.put("orderType", 277);
            map.put("isDel", 16);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(jGoodsContractMapper.selectByMap(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
