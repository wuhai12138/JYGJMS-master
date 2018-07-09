package com.summ.controller.order;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JOrderYearsMapper;
import com.summ.model.*;
import com.summ.model.request.OrderScheduleReq;
import com.summ.model.request.ServiceWeeksTimeReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.OrderScheduleRes;
import com.summ.utils.DateUtil;
import com.summ.utils.JsonUtil;
import com.summ.utils.NannyWorkTimeUtil;
import com.summ.utils.ResponseUtil;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 包年订单日程
 *
 * @author summ
 */
@Controller
@RequestMapping("order/years/schedule")
public class OrderYearsScheduleController extends AutoMapperController {
    /**
     * 生成包年订单日程
     *
     * @param reqMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/generate")
    public Object generate(@RequestBody Map reqMap) {
        try {
            //服务师日程列表用以存放日程的服务师
            List<JScheduleNanny> scheduleNannyList = new ArrayList<JScheduleNanny>();
            //日程列表用以存放订单日程
            List<JOrderSchedule> orderScheduleList = new ArrayList<JOrderSchedule>();

            Long orderId = (Long) reqMap.get("orderId");
            Integer nannyId = (Integer) reqMap.get("nannyId");
            Date startDate = new Date((Long) reqMap.get("startDate"));
            Date endDate = new Date((Long) reqMap.get("endDate"));
            Integer scheduleType = (Integer) reqMap.get("scheduleType");
            Integer teacherId = (Integer) reqMap.get("teacherId");

            /**验证服务师工时*/
            //获取合同订单列表
            JOrderYears jOrderYears = jOrderYearsMapper.selectById(orderId);

            List<JNannyInfo> jNannyInfoList = jNannyJobLevelGoodsMapper.signNannyAndGoods(nannyId,jOrderYears.getGoodsId());
            if (jNannyInfoList.size()==0){
                return new ModelRes(ModelRes.Status.FAILED, "服务师工种不匹配 ! !", null);
            }

            List<JNannyShop> jNannyShopList = jNannyShopMapper.signNannyShopByShop(nannyId,jOrderYears.getShopId());
            if (jNannyShopList.size()==0){
                return new ModelRes(ModelRes.Status.FAILED, "服务师门店不匹配 ! !", null);
            }

            //目前只使用验证一个订单，所以日期只需改变数组第一个订单的时间
            jOrderYears.setStartDate(startDate);
            jOrderYears.setEndDate(new Date(endDate.getTime() + 1));
            jOrderYears.setTeacherId(teacherId);
            //连接mongodb数据库
            mongoDBUtil = MongoDBUtil.getInstance(new MongoConfig());

            //存储订单列表对应的星期列表和时间列表和日期列表
            List<Date> startDateList = new ArrayList<Date>();
            List<Date> endDateList = new ArrayList<Date>();
            /**根据配单类型更新合同订单状态*/
            if (scheduleType == 179) {
                //试工中
                jOrderYears.setOrderStatus(143);
            }
            if (scheduleType == 180) {
                //开工中
                jOrderYears.setOrderStatus(149);
            }

            Map mongoMap = new HashedMap();
            mongoMap.put("serviceId", jOrderYears.getServiceId());
            Map mapJson = JsonUtil.json2Map(mongoDBUtil.query("customer_service", mongoMap));

            //计划书星期列表：[{"value":"周一","id":"1","active":true},{"value":"周二","id":"2","active":true},{"value":"周三","id":"3","active":true},
            // {"value":"周四","id":"4","active":true},{"value":"周五","id":"5","active":true},{"value":"周六","id":"6","active":true},{"value":"周天","id":"7","active":true}]
            List<Map> weeks = (List<Map>) mapJson.get("weekListTime");
            //计划书每天时间列表：[{"startId":23,"endId":25,"startTime":"03:30","start":"00:00","end":"24:00","endTime":"","firstSelect":false,"or":1},{"startId":37,"endId":39,"startTime":"03:30","start":"00:00","end":"24:00","endTime":"","firstSelect":false,"or":1}]
            List<Map> times = (List<Map>) mapJson.get("addTime");
            //计算出每天的所有时间id数组
            List<Long> timeList = new ArrayList<Long>();
            List<Long> scheduleTimeList = new ArrayList<Long>();
            for (int j = 0; j < times.size(); j++) {
                timeList.addAll(NannyWorkTimeUtil.id2Value((Integer) times.get(j).get("startId"), (Integer) times.get(j).get("endId")));
                scheduleTimeList.addAll(NannyWorkTimeUtil.id2Value((Integer) times.get(j).get("startId")+1, (Integer) times.get(j).get("endId")-1));
            }


            startDateList.add(jOrderYears.getStartDate());
            endDateList.add(jOrderYears.getEndDate());


            //循环订单判断订单与服务师是否冲突
            ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
            //服务师id
            serviceWeeksTimeReq.setNannyId(nannyId);
            //订单计划书需求时间值列表
            serviceWeeksTimeReq.setLongList(timeList);
            serviceWeeksTimeReq.setScheduleLongList(scheduleTimeList);
            //订单开始结束时间
            serviceWeeksTimeReq.setStartDate(jOrderYears.getStartDate());
            serviceWeeksTimeReq.setEndDate(jOrderYears.getEndDate());
            //weekList为星期列表，用于验证服务师的日程与订单是否冲突
            List<String> weekListTemp = new ArrayList<String>();
            for (int i = 0; i < weeks.size(); i++) {
                if ("true".equals(weeks.get(i).get("active").toString())) {
                    if ("周一".equals(weeks.get(i).get("value").toString())) {
                        serviceWeeksTimeReq.setWeekday("周一");
                        weekListTemp.add("周一");
                    }
                    if ("周二".equals(weeks.get(i).get("value").toString())) {
                        serviceWeeksTimeReq.setWeekday("周二");
                        weekListTemp.add("周二");
                    }
                    if ("周三".equals(weeks.get(i).get("value").toString())) {
                        serviceWeeksTimeReq.setWeekday("周三");
                        weekListTemp.add("周三");
                    }
                    if ("周四".equals(weeks.get(i).get("value").toString())) {
                        serviceWeeksTimeReq.setWeekday("周四");
                        weekListTemp.add("周四");
                    }
                    if ("周五".equals(weeks.get(i).get("value").toString())) {
                        serviceWeeksTimeReq.setWeekday("周五");
                        weekListTemp.add("周五");
                    }
                    if ("周六".equals(weeks.get(i).get("value").toString())) {
                        serviceWeeksTimeReq.setWeekday("周六");
                        weekListTemp.add("周六");
                    }
                    if ("周天".equals(weeks.get(i).get("value").toString())) {
                        serviceWeeksTimeReq.setWeekday("周天");
                        weekListTemp.add("周天");
                    }
                }
            }
            serviceWeeksTimeReq.setWeekList(weekListTemp);

            /**换算开始结束时间*/
            Map map1 = JsonUtil.Obj2Map(serviceWeeksTimeReq);
            map1.put("startDate",new Date((Long) map1.get("startDate")));
            map1.put("endDate",new Date((Long) map1.get("endDate")));
            System.out.println(">>>>>>>>sql>>>>>>>>>>>>>" + map1.toString());
            //判断该服务师的工时是否符合订单时间要求
            List<JNannyInfo> signNannyWorkTime = jNannyWorkTimeMapper.signNannyWorkTime(map1);
            if (signNannyWorkTime.size() < 1) {
                return new ModelRes(ModelRes.Status.FAILED, "服务师工时不匹配 ! !", null);
            }


            /**循环每个订单服务计划书的每天开始结束时间id，验证日程是否冲突*/
            for (int i=0;i<times.size();i++){
                serviceWeeksTimeReq.setEndId((Integer) times.get(i).get("endId"));
                serviceWeeksTimeReq.setStartId((Integer) times.get(i).get("startId"));

                /**换算开始结束时间*/
                Map signScheduleMap = JsonUtil.Obj2Map(serviceWeeksTimeReq);
                signScheduleMap.put("startDate", new Date((Long) signScheduleMap.get("startDate")));
                signScheduleMap.put("endDate", new Date((Long) signScheduleMap.get("endDate")));

                /**判断该服务师的日程是否符合订单时间要求*/
                List<JOrderSchedule> signNannySchedule = jOrderScheduleMapper.signNannySchedule(signScheduleMap);
                if (signNannySchedule.size() > 0) {
                    return new ModelRes(ModelRes.Status.FAILED, "服务师日程占用 ! !", ResponseUtil.List2Map(signNannySchedule));
                }
            }



            /**生成日程*/
            //循环计划书中的weekListTime 即一周的七天
            for (int i = 0; i < weeks.size(); i++) {
                if ("true".equals(weeks.get(i).get("active").toString())) {
                    if ("周一".equals(weeks.get(i).get("value").toString())) {
                        List<Date> dateList = DateUtil.weekAndDay(1, jOrderYears.getStartDate(), jOrderYears.getEndDate());
                        //循环每天的时间区间，几个区间则分别生成几个区间的日程
                        for (int k = 0; k < times.size(); k++) {
                            //根据订单开始结束日期计算出有多少个周一，并循环
                            for (int j = 0; j < dateList.size(); j++) {
                                //日程信息
                                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                jOrderSchedule.setOrderType(165);
                                jOrderSchedule.setScheduleStatus(152);
                                jOrderSchedule.setPayStatus(157);
                                jOrderSchedule.setScheduleType(scheduleType);
                                jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                //订单id
                                jOrderSchedule.setOrderId(jOrderYears.getOrderId());
                                jOrderSchedule.setCustomerId(jOrderYears.getCustomerId());
                                jOrderSchedule.setShopId(jOrderYears.getShopId());
                                jOrderSchedule.setServiceId(jOrderYears.getServiceId());
                                jOrderSchedule.setHouseId(jOrderYears.getHouseId());
                                jOrderSchedule.setGoodsId(jOrderYears.getGoodsId());
                                //价格改变为实时变动的，此处生成日程时不做输入
                                //单价
//                                jOrderSchedule.setUnitPrice(jGoodsContract.getPrice());
                                //总价 单价乘以上工小时数，格式为big decimal，保留两位小数
//                                jOrderSchedule.setTotalPrice(jGoodsContract.getPrice().multiply(new BigDecimal(((Integer) times.get(k).get("endId") - (Integer) times.get(k).get("startId")) / 2f)).setScale(2));
                                //成本（取决于服务师薪资）
//                                jOrderSchedule.setCost(new BigDecimal(nannyInfoRes.getNannyLevelInfo()).multiply(new BigDecimal(((Integer) times.get(k).get("endId") - (Integer) times.get(k).get("startId")) / 2f)).setScale(2));
                                jOrderSchedule.setScheduleDate(dateList.get(j));
                                jOrderSchedule.setWeekday("周一");
                                orderScheduleList.add(jOrderSchedule);
                                jOrderScheduleMapper.insert(jOrderSchedule);


                            }
                        }
                    }
                    if ("周二".equals(weeks.get(i).get("value").toString())) {
                        List<Date> dateList = DateUtil.weekAndDay(2, jOrderYears.getStartDate(), jOrderYears.getEndDate());
                        for (int k = 0; k < times.size(); k++) {
                            //根据订单开始结束日期计算出有多少个周一，并循环
                            for (int j = 0; j < dateList.size(); j++) {
                                //日程信息
                                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                jOrderSchedule.setOrderType(165);
                                jOrderSchedule.setScheduleStatus(152);
                                jOrderSchedule.setPayStatus(157);
                                jOrderSchedule.setScheduleType(scheduleType);
                                jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                //订单id
                                jOrderSchedule.setOrderId(jOrderYears.getOrderId());
                                jOrderSchedule.setCustomerId(jOrderYears.getCustomerId());
                                jOrderSchedule.setShopId(jOrderYears.getShopId());
                                jOrderSchedule.setServiceId(jOrderYears.getServiceId());
                                jOrderSchedule.setHouseId(jOrderYears.getHouseId());
                                jOrderSchedule.setGoodsId(jOrderYears.getGoodsId());
                                jOrderSchedule.setScheduleDate(dateList.get(j));
                                jOrderSchedule.setWeekday("周二");
                                orderScheduleList.add(jOrderSchedule);
                                jOrderScheduleMapper.insert(jOrderSchedule);

                            }
                        }
                    }
                    if ("周三".equals(weeks.get(i).get("value").toString())) {
                        List<Date> dateList = DateUtil.weekAndDay(3, jOrderYears.getStartDate(), jOrderYears.getEndDate());
                        for (int k = 0; k < times.size(); k++) {
                            //根据订单开始结束日期计算出有多少个周一，并循环
                            for (int j = 0; j < dateList.size(); j++) {
                                //日程信息
                                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                jOrderSchedule.setOrderType(165);
                                jOrderSchedule.setScheduleStatus(152);
                                jOrderSchedule.setPayStatus(157);
                                jOrderSchedule.setScheduleType(scheduleType);
                                jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                //订单id
                                jOrderSchedule.setOrderId(jOrderYears.getOrderId());
                                jOrderSchedule.setCustomerId(jOrderYears.getCustomerId());
                                jOrderSchedule.setShopId(jOrderYears.getShopId());
                                jOrderSchedule.setServiceId(jOrderYears.getServiceId());
                                jOrderSchedule.setHouseId(jOrderYears.getHouseId());
                                jOrderSchedule.setGoodsId(jOrderYears.getGoodsId());
                                jOrderSchedule.setScheduleDate(dateList.get(j));
                                jOrderSchedule.setWeekday("周三");
                                orderScheduleList.add(jOrderSchedule);
                                jOrderScheduleMapper.insert(jOrderSchedule);

                            }
                        }
                    }
                    if ("周四".equals(weeks.get(i).get("value").toString())) {
                        List<Date> dateList = DateUtil.weekAndDay(4, jOrderYears.getStartDate(), jOrderYears.getEndDate());
                        for (int k = 0; k < times.size(); k++) {
                            //根据订单开始结束日期计算出有多少个周一，并循环
                            for (int j = 0; j < dateList.size(); j++) {
                                //日程信息
                                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                jOrderSchedule.setOrderType(165);
                                jOrderSchedule.setScheduleStatus(152);
                                jOrderSchedule.setScheduleType(scheduleType);
                                jOrderSchedule.setPayStatus(157);
                                jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                //订单id
                                jOrderSchedule.setOrderId(jOrderYears.getOrderId());
                                jOrderSchedule.setCustomerId(jOrderYears.getCustomerId());
                                jOrderSchedule.setShopId(jOrderYears.getShopId());
                                jOrderSchedule.setServiceId(jOrderYears.getServiceId());
                                jOrderSchedule.setHouseId(jOrderYears.getHouseId());
                                jOrderSchedule.setGoodsId(jOrderYears.getGoodsId());
                                jOrderSchedule.setScheduleDate(dateList.get(j));
                                jOrderSchedule.setWeekday("周四");
                                orderScheduleList.add(jOrderSchedule);
                                jOrderScheduleMapper.insert(jOrderSchedule);

                            }
                        }
                    }
                    if ("周五".equals(weeks.get(i).get("value").toString())) {
                        List<Date> dateList = DateUtil.weekAndDay(5, jOrderYears.getStartDate(), jOrderYears.getEndDate());
                        for (int k = 0; k < times.size(); k++) {
                            //根据订单开始结束日期计算出有多少个周一，并循环
                            for (int j = 0; j < dateList.size(); j++) {
                                //日程信息
                                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                jOrderSchedule.setOrderType(165);
                                jOrderSchedule.setScheduleStatus(152);
                                jOrderSchedule.setScheduleType(scheduleType);
                                jOrderSchedule.setPayStatus(157);
                                jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                //订单id
                                jOrderSchedule.setOrderId(jOrderYears.getOrderId());
                                jOrderSchedule.setCustomerId(jOrderYears.getCustomerId());
                                jOrderSchedule.setShopId(jOrderYears.getShopId());
                                jOrderSchedule.setServiceId(jOrderYears.getServiceId());
                                jOrderSchedule.setHouseId(jOrderYears.getHouseId());
                                jOrderSchedule.setGoodsId(jOrderYears.getGoodsId());
                                jOrderSchedule.setScheduleDate(dateList.get(j));
                                jOrderSchedule.setWeekday("周五");
                                orderScheduleList.add(jOrderSchedule);
                                jOrderScheduleMapper.insert(jOrderSchedule);

                            }
                        }
                    }
                    if ("周六".equals(weeks.get(i).get("value").toString())) {
                        List<Date> dateList = DateUtil.weekAndDay(6, jOrderYears.getStartDate(), jOrderYears.getEndDate());
                        for (int k = 0; k < times.size(); k++) {
                            //根据订单开始结束日期计算出有多少个周一，并循环
                            for (int j = 0; j < dateList.size(); j++) {
                                //日程信息
                                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                jOrderSchedule.setOrderType(165);
                                jOrderSchedule.setScheduleStatus(152);
                                jOrderSchedule.setScheduleType(scheduleType);
                                jOrderSchedule.setPayStatus(157);
                                jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                //订单id
                                jOrderSchedule.setOrderId(jOrderYears.getOrderId());
                                jOrderSchedule.setCustomerId(jOrderYears.getCustomerId());
                                jOrderSchedule.setShopId(jOrderYears.getShopId());
                                jOrderSchedule.setServiceId(jOrderYears.getServiceId());
                                jOrderSchedule.setHouseId(jOrderYears.getHouseId());
                                jOrderSchedule.setGoodsId(jOrderYears.getGoodsId());
                                jOrderSchedule.setScheduleDate(dateList.get(j));
                                jOrderSchedule.setWeekday("周六");
                                orderScheduleList.add(jOrderSchedule);
                                jOrderScheduleMapper.insert(jOrderSchedule);

                            }
                        }
                    }
                    if ("周天".equals(weeks.get(i).get("value").toString())) {
                        List<Date> dateList = DateUtil.weekAndDay(0, jOrderYears.getStartDate(), jOrderYears.getEndDate());
                        for (int k = 0; k < times.size(); k++) {
                            //根据订单开始结束日期计算出有多少个周一，并循环
                            for (int j = 0; j < dateList.size(); j++) {
                                //日程信息
                                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                jOrderSchedule.setOrderType(165);
                                jOrderSchedule.setScheduleStatus(152);
                                jOrderSchedule.setScheduleType(scheduleType);
                                jOrderSchedule.setPayStatus(157);
                                jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                //订单id
                                jOrderSchedule.setOrderId(jOrderYears.getOrderId());
                                jOrderSchedule.setCustomerId(jOrderYears.getCustomerId());
                                jOrderSchedule.setShopId(jOrderYears.getShopId());
                                jOrderSchedule.setServiceId(jOrderYears.getServiceId());
                                jOrderSchedule.setHouseId(jOrderYears.getHouseId());
                                jOrderSchedule.setGoodsId(jOrderYears.getGoodsId());
                                jOrderSchedule.setScheduleDate(dateList.get(j));
                                jOrderSchedule.setWeekday("周天");
                                orderScheduleList.add(jOrderSchedule);
                                jOrderScheduleMapper.insert(jOrderSchedule);
                            }
                        }
                    }
                }
            }


            //添加日程

            //添加日程的服务师
            for (int i = 0; i < orderScheduleList.size(); i++) {
                JScheduleNanny jScheduleNanny = new JScheduleNanny(orderScheduleList.get(i).getScheduleId(), nannyId, 1);
                scheduleNannyList.add(jScheduleNanny);
                jScheduleNannyMapper.insert(jScheduleNanny);
            }
            if (orderScheduleList.size()==0){
                return new ModelRes(ModelRes.Status.FAILED, "该时间段无符合计划书的日程  !", null);
            }
            //更新订单状态
            jOrderYearsMapper.updateById(jOrderYears);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderScheduleReq orderScheduleReq){
        try {
            orderScheduleReq.setOrderType(165);
            orderScheduleReq.setPage(orderScheduleReq.getSize() * (orderScheduleReq.getPage() - 1));
            List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getScheduleList(orderScheduleReq);

            for (int i=0;i<orderScheduleResList.size();i++){
                orderScheduleResList.get(i).setUnitPrice(orderScheduleResList.get(i).getScheduleCurrentPrice());

                //判断日程总价是否为0，是则实时计算出当前总价
                if (orderScheduleResList.get(i).getTotalPrice().compareTo(new BigDecimal(0))==0){
                    orderScheduleResList.get(i).setTotalPrice(orderScheduleResList.get(i).getScheduleCurrentPrice().multiply(new BigDecimal(String.valueOf((orderScheduleResList.get(i).getEndTime()-orderScheduleResList.get(i).getStartTime()) / 2f))).setScale(2));
                }
                //判断服务师工资是否为0，是则实时计算出服务师当前工资
                if (orderScheduleResList.get(i).getCost().compareTo(new BigDecimal(0))==0){
                    orderScheduleResList.get(i).setCost(new BigDecimal(orderScheduleResList.get(i).getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((orderScheduleResList.get(i).getEndTime()-orderScheduleResList.get(i).getStartTime()) / 2f))).setScale(2));
                }
            }
            Map map = ResponseUtil.List2Map(orderScheduleResList);
            map.put("count",jOrderScheduleMapper.getScheduleCount(orderScheduleReq));
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/clock")
    public Object suspend(@RequestBody Map<String, List<Integer>> jOrderScheduleList, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            List<Integer> list = jOrderScheduleList.get("list");
            List<JOrderSchedule> orderScheduleList = new ArrayList<JOrderSchedule>();

            for (int i = 0; i < list.size(); i++) {
                final JOrderSchedule jOrderSchedule = new JOrderSchedule();
                jOrderSchedule.setScheduleStatus(153);
                jOrderSchedule.setPayStatus(158);
                jOrderSchedule.setScheduleId(list.get(i));
                jOrderSchedule.setClockTime(new Date());
                jOrderSchedule.setClockId(jAdmin.getAdminId());
                orderScheduleList.add(jOrderSchedule);
                jOrderScheduleMapper.updateById(jOrderSchedule);

                /**给客户和服务师添加积分*/
                final JOrderSchedule jOrderSchedule1 = jOrderScheduleMapper.selectById(jOrderSchedule.getScheduleId());
                List<JScheduleNanny> jScheduleNannyList = jScheduleNannyMapper.selectList(new Wrapper<JScheduleNanny>() {
                    @Override
                    public String getSqlSegment() {
                        return "where scheduleId="+jOrderSchedule1.getScheduleId();
                    }
                });
                JCustomer jCustomer = jCustomerMapper.selectById(jOrderSchedule1.getCustomerId());
                JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(jScheduleNannyList.get(0).getNannyId());
                jCustomer.setCustomerCredit(jCustomer.getCustomerCredit()+1);
                jNannyInfo.setNannyCredit(jNannyInfo.getNannyCredit()+1);
                jCustomerMapper.updateById(jCustomer);
                jNannyInfoMapper.updateById(jNannyInfo);

            }
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
