package com.summ.controller.order;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.*;
import com.summ.model.response.*;
import com.summ.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/schedule")
public class ScheduleController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody ScheduleReq scheduleReq, ServletRequest request) {
        try {
            scheduleReq.setPage(scheduleReq.getSize() * (scheduleReq.getPage() - 1));
            JAdmin admin = (JAdmin) request.getAttribute("admin");
            scheduleReq.setAdminId(admin.getAdminId());

            switch (scheduleReq.getOrderType()) {
                case 163:
                    /**合同订单*/
                    List<ScheduleRes> scheduleContractResList = jOrderScheduleMapper.getContractScheduleList(scheduleReq);
                    for (int i = 0; i < scheduleContractResList.size(); i++) {
                        scheduleContractResList.get(i).setUnitPrice(scheduleContractResList.get(i).getScheduleCurrentPrice());

                        //判断服务师工资是否为0，是则实时计算出服务师当前工资
                        if (scheduleContractResList.get(i).getCost().compareTo(new BigDecimal(0)) == 0) {
                            scheduleContractResList.get(i).setCost(new BigDecimal(scheduleContractResList.get(i).getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((scheduleContractResList.get(i).getEndTime() - scheduleContractResList.get(i).getStartTime()) / 2f))).setScale(2));
                        }
                        //判断日程总价是否为0，是则实时计算出当前总价
                        if (scheduleContractResList.get(i).getTotalPrice().compareTo(new BigDecimal(0)) == 0) {
                            scheduleContractResList.get(i).setTotalPrice(scheduleContractResList.get(i).getScheduleCurrentPrice().multiply(new BigDecimal(String.valueOf((scheduleContractResList.get(i).getEndTime() - scheduleContractResList.get(i).getStartTime()) / 2f))).setScale(2));
                        }
                    }
                    Map mapContract = ResponseUtil.List2Map(scheduleContractResList);
                    mapContract.put("count", jOrderScheduleMapper.getContractScheduleListCount(scheduleReq));
                    return new ModelRes(ModelRes.Status.SUCCESS, "查找合同订单日程 !", mapContract);
                case 164:
                    /**临时订单*/
                    List<ScheduleRes> scheduleTempResList = jOrderScheduleMapper.getAllTempScheduleList(scheduleReq);
                    for (ScheduleRes scheduleRes : scheduleTempResList) {
                        /**判断是不是需要实时计算成本 0则表明需要实时计算*/
                        if (scheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                            /**判断日程是自营还是供应商提供 1为自营*/
                            if (scheduleRes.getSupplierId() == 1) {
                                JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(scheduleRes.getNannyId()));
                                JDictInfo jDictInfo = jDictInfoMapper.selectById(Long.valueOf(jNannyInfo.getNannyLevel()));
                                /**根据服务师星级小时工资与日程工作时长计算成本*/
                                scheduleRes.setNannyName(jNannyInfo.getNannyName());
                                scheduleRes.setNannyPhone(jNannyInfo.getNannyPhone());
                                scheduleRes.setCost(new BigDecimal(jDictInfo.getInfo()).multiply(new BigDecimal(String.valueOf((scheduleRes.getEndTime() - scheduleRes.getStartTime()) / 2f))).setScale(2));
                            } else if (scheduleRes.getSupplierId() == -1) {
                                JTeacher jTeacher = jTeacherMapper.selectById(Long.valueOf(scheduleRes.getNannyId()));
                                scheduleRes.setNannyName(jTeacher.getTeacherName());
                                scheduleRes.setNannyPhone(jTeacher.getTeacherPhone());
                                /**此处验证带教工时*/
                                //...
                            } else {
                                JSupplier jSupplier = jSupplierMapper.selectById(Long.valueOf(scheduleRes.getSupplierId()));
                                JCompanyData jCompanyData = jCompanyDataMapper.selectById(Long.valueOf(jSupplier.getCompanyId()));
                                Map map = new HashMap();
                                map.put("goodsId", scheduleRes.getGoodsId());
                                map.put("supplierId", scheduleRes.getSupplierId());
                                List<JGoodsCost> jGoodsCostList = jGoodsCostMapper.selectByMap(map);
                                /**根据供应商单位成本与订单产品的数量（平方米/个数）计算成本*/
                                scheduleRes.setNannyPhone(jSupplier.getPhone());
                                scheduleRes.setNannyName(jCompanyData.getCompanyName());
                                scheduleRes.setCost(jGoodsCostList.get(0).getCost().multiply(new BigDecimal(scheduleRes.getServiceAmount())).setScale(2));
                            }
                        }
                    }
                    Map mapTemp = ResponseUtil.List2Map(scheduleTempResList);
                    mapTemp.put("count", jOrderScheduleMapper.getAllTempScheduleListCount(scheduleReq));
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", mapTemp);
                case 165:
                    /**包年订单*/
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(jOrderScheduleMapper.getAllYearsScheduleList(scheduleReq), jOrderScheduleMapper.getAllYearsScheduleListCount(scheduleReq)));
                case 277:
                    /**包月订单*/
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(jOrderScheduleMapper.getAllMonthScheduleList(scheduleReq), jOrderScheduleMapper.getAllMonthScheduleListCount(scheduleReq)));
                default:
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取客户日程的反馈
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/customer/feedback/find")
    public Object findFeedback(@RequestBody CustomerScheduleFeedBackReq customerScheduleFeedBackReq) {
        try {
            customerScheduleFeedBackReq.setPage((customerScheduleFeedBackReq.getPage() - 1) * customerScheduleFeedBackReq.getSize());
            List<CustomerScheduleFeedbackRes> customerScheduleFeedbackResList = jCustomerScheduleFeedbackMapper.getListBuReq(customerScheduleFeedBackReq);
            for (CustomerScheduleFeedbackRes jCustomerScheduleFeedback : customerScheduleFeedbackResList) {
                jCustomerScheduleFeedback.setPhote(Consts.customerScheduleFeedbackUrlRes + jCustomerScheduleFeedback.getPhote());
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(customerScheduleFeedbackResList, jCustomerScheduleFeedbackMapper.getListBuReqCount(customerScheduleFeedBackReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取服务师日程的反馈
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/nanny/feedback/find")
    public Object findNannyFeedback(@RequestBody CustomerScheduleFeedBackReq customerScheduleFeedBackReq) {
        try {
            customerScheduleFeedBackReq.setPage((customerScheduleFeedBackReq.getPage() - 1) * customerScheduleFeedBackReq.getSize());
            List<CustomerScheduleFeedbackRes> customerScheduleFeedbackResList = jNannyScheduleFeedbackMapper.getListBuReq(customerScheduleFeedBackReq);
            for (CustomerScheduleFeedbackRes jCustomerScheduleFeedback : customerScheduleFeedbackResList) {
                jCustomerScheduleFeedback.setPhote(Consts.nannyScheduleFeedbackUrlRes + jCustomerScheduleFeedback.getPhote());
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(customerScheduleFeedbackResList, jNannyScheduleFeedbackMapper.getListBuReqCount(customerScheduleFeedBackReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 日程批量修改价格
     *
     * @param schedulePriceReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/update/price")
    public Object updatePrice(@RequestBody SchedulePriceReq schedulePriceReq) {
        try {
            List<JOrderSchedule> jOrderScheduleList = jOrderScheduleMapper.selectBatchIds(schedulePriceReq.getScheduleIdList());
            for (JOrderSchedule jOrderSchedule : jOrderScheduleList) {
                if (jOrderSchedule.getScheduleStatus() != 152) {
                    return new ModelRes(ModelRes.Status.FAILED, "有日程状态不是待完工 !", jOrderSchedule);
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jOrderScheduleMapper.updateSchedulePrice(JsonUtil.Obj2Map(schedulePriceReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 查找符合日程要求的服务师
     *
     * @param map1
     * @return
     */
    @ResponseBody
    @RequestMapping("/nanny")
    public Object update(@RequestBody Map map1) {
        try {
            JOrderSchedule jOrderSchedule = jOrderScheduleMapper.selectById(Long.valueOf((Integer) map1.get("scheduleId")));
            ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
            serviceWeeksTimeReq.setLongList(NannyWorkTimeUtil.id2Value(jOrderSchedule.getStartTime(), jOrderSchedule.getEndTime()));
            serviceWeeksTimeReq.setWeekday(DateUtil.dateAndday(jOrderSchedule.getScheduleDate()));
            serviceWeeksTimeReq.setStartId(jOrderSchedule.getStartTime());
            serviceWeeksTimeReq.setEndId(jOrderSchedule.getEndTime());
            List<String> weekList = new ArrayList<String>();
            serviceWeeksTimeReq.setWeekList(weekList);
            serviceWeeksTimeReq.setStartDate(jOrderSchedule.getScheduleDate());
            serviceWeeksTimeReq.setEndDate(new Date(jOrderSchedule.getScheduleDate().getTime() + (24 * 3600 * 1000 - 1)));

            /**换算开始结束时间*/
            Map map = JsonUtil.Obj2Map(serviceWeeksTimeReq);
            map.put("startDate", new Date((Long) map.get("startDate")));
            map.put("endDate", new Date((Long) map.get("endDate")));

            List<OrderTempNannyRes> list = jNannyInfoMapper.getOrderTempNannyList(map);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(list));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 若选供应商，查找供应商列表
     *
     * @param map
     * @return
     */
    @RequestMapping("/supplier")
    @ResponseBody
    public Object getSupplierInfo(@RequestBody Map map) {
        try {
            Integer orderId = (Integer) map.get("orderId");
            Integer orderType = (Integer) map.get("orderType");
            String supplierName = (String) map.get("supplierName");
            /**非临时订单直接返回空数组*/
            if (orderType != 164) {
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(new ArrayList()));
            }
            List<OrderTempSupplierRes> list = jGoodsCostMapper.getSupplierList(orderId, supplierName);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(list));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }

    }

    /**
     * 若选带教，查找符合订单时间和带教工时的带教列表
     *
     * @param map1
     * @return
     */
    @RequestMapping("/teacher")
    @ResponseBody
    public Object getTeacherInfo(@RequestBody Map map1) {
        try {
            Integer scheduleId = (Integer) map1.get("scheduleId");
            JOrderSchedule jOrderSchedule = jOrderScheduleMapper.selectById(Long.valueOf(scheduleId));
            ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
            serviceWeeksTimeReq.setLongList(NannyWorkTimeUtil.id2Value(jOrderSchedule.getStartTime(), jOrderSchedule.getEndTime()));
            serviceWeeksTimeReq.setWeekday(jOrderSchedule.getWeekday());
            serviceWeeksTimeReq.setStartId(jOrderSchedule.getStartTime());
            serviceWeeksTimeReq.setEndId(jOrderSchedule.getEndTime());
            List<String> weekList = new ArrayList<String>();
            serviceWeeksTimeReq.setWeekList(weekList);
            serviceWeeksTimeReq.setStartDate(jOrderSchedule.getScheduleDate());
            serviceWeeksTimeReq.setEndDate(new Date(jOrderSchedule.getScheduleDate().getTime() + (24 * 3600 * 1000 - 1)));

            /**换算开始结束时间*/
            Map map = JsonUtil.Obj2Map(serviceWeeksTimeReq);
            map.put("startDate", new Date((Long) map.get("startDate")));
            map.put("endDate", new Date((Long) map.get("endDate")));

            List<TeacherRes> list = jTeacherMapper.getOrderTeacherList(map);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", ResponseUtil.List2Map(list));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }

    }

    /**
     * 取消
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/cancel")
    public Object cancel(@RequestBody Map jOrderScheduleList) {
        try {
            List<Integer> list = (List<Integer>) jOrderScheduleList.get("list");
            String reason = (String) jOrderScheduleList.get("reason");

            for (int i = 0; i < list.size(); i++) {
                JOrderSchedule jOrderSchedule = jOrderScheduleMapper.selectById(Long.valueOf(list.get(i)));
                if (152 == jOrderSchedule.getScheduleStatus()) {
                    jOrderSchedule.setScheduleStatus(155);
                    jOrderSchedule.setCancelTime(new Date());
                    jOrderSchedule.setRemark(jOrderSchedule.getRemark() + "-" + reason);
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "该状态无法取消  !", jOrderSchedule);
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
