package com.summ.controller.order;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JGoodsContractMapper;
import com.summ.mapper.JNannyJobLevelGoodsMapper;
import com.summ.mapper.JScheduleNannyMapper;
import com.summ.model.*;
import com.summ.model.request.OrderScheduleReq;
import com.summ.model.request.ScheduleClockReq;
import com.summ.model.request.ScheduleReq;
import com.summ.model.request.ServiceWeeksTimeReq;
import com.summ.model.response.*;
import com.summ.utils.*;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIAttribute;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.ls.LSException;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * 订单日程
 *
 * @author summ
 * @date 18/1/13
 */

@Controller
@RequestMapping("/order/schedule")
public class OrderScheduleController extends AutoMapperController {

    /**
     * 生成合同订单日程
     *
     * @param reqMap
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping("/generate")
    public Object insert(@RequestBody Map reqMap) {
        try {
            //日程列表用以存放订单日程
            List<JOrderSchedule> orderScheduleList = new ArrayList<JOrderSchedule>();
            //服务师日程列表用以存放日程的服务师
            List<JScheduleNanny> scheduleNannyList = new ArrayList<JScheduleNanny>();

            List<Long> orderIdList = (List<Long>) reqMap.get("orderId");
            final Integer nannyId = (Integer) reqMap.get("nannyId");
            Date startDate = DateUtil.parseLongToDate((Long) reqMap.get("startDate"));
            Date endDate = DateUtil.parseLongToDate((Long) reqMap.get("endDate"));
            /**将结束日期格式化成"yyyy-MM-dd"格式*/
            endDate=DateUtil.parseStringToDate(DateUtil.parseDateToString(endDate,DateUtil.PATTERN_yyyy_MM_dd),DateUtil.PATTERN_yyyy_MM_dd);
            Integer scheduleType = (Integer) reqMap.get("scheduleType");
            Integer teacherId = (Integer) reqMap.get("teacherId");



            //获取合同订单列表
            List<JOrderContract> jOrderContractList = jOrderContractMapper.selectBatchIds(orderIdList);
            /**验证服务师工种是否符合订单要求*/
            for (JOrderContract jOrderContract : jOrderContractList){
                List<JNannyInfo> jNannyInfoList = jNannyJobLevelGoodsMapper.signNannyAndGoods(nannyId,jOrderContract.getGoodsId());
                if (jNannyInfoList.size()==0){
                    return new ModelRes(ModelRes.Status.FAILED, "服务师工种不匹配 ! !", null);
                }

                List<JNannyShop> jNannyShopList = jNannyShopMapper.signNannyShopByShop(nannyId,jOrderContract.getShopId());
                if (jNannyShopList.size()==0){
                    return new ModelRes(ModelRes.Status.FAILED, "服务师门店不匹配 ! !", null);
                }
            }


            /**验证服务师工时*/
            //目前只使用验证一个订单，所以日期只需改变数组第一个订单的时间,订单时间只修改结束时间,结束时间未最后一笔日程的时间,在日程生成结束后更新
//            jOrderContractList.get(0).setStartDate(startDate);
//            jOrderContractList.get(0).setEndDate(new Date(endDate.getTime() + 1));
            jOrderContractList.get(0).setTeacherId(teacherId);
            //连接mongodb数据库
            mongoDBUtil = MongoDBUtil.getInstance(new MongoConfig());

            //存储订单列表对应的星期列表和时间列表和日期列表
            List<Date> startDateLIst = new ArrayList<Date>();
            List<Date> endDateLIst = new ArrayList<Date>();
            List<List<Map>> weeksList = new ArrayList<List<Map>>();
            List<List<Long>> timesList = new ArrayList<List<Long>>();
            List<List<Long>> scheduleTimesList = new ArrayList<List<Long>>();
            /**timeIdList存放每个订单的开始结束时间id的数组，用于验证服务师日程时间*/
            List<List<Map>> timeIdList = new ArrayList<>();
            for (int i = 0; i < jOrderContractList.size(); i++) {
                /**根据配单类型更新合同订单状态*/
                if (scheduleType == 179) {
                    //试工中
                    jOrderContractList.get(i).setOrderStatus(143);
                }
                if (scheduleType == 180) {
                    //开工中
                    jOrderContractList.get(i).setOrderStatus(149);
                }

                Map mongoMap = new HashedMap();
                mongoMap.put("serviceId", jOrderContractList.get(i).getServiceId());
                Map mapJson = JsonUtil.json2Map(mongoDBUtil.query("customer_service", mongoMap));

                List<Map> weeks = (List<Map>) mapJson.get("weekListTime");
                List<Map> times = (List<Map>) mapJson.get("addTime");
                timeIdList.add(times);
                //计算出每天的所有时间id数组
                List<Long> timeList = new ArrayList<Long>();
                List<Long> scheduleTimeList = new ArrayList<Long>();
                for (int j = 0; j < times.size(); j++) {
                    timeList.addAll(NannyWorkTimeUtil.id2Value((Integer) times.get(j).get("startId"), (Integer) times.get(j).get("endId")));
                    scheduleTimeList.addAll(NannyWorkTimeUtil.id2Value((Integer) times.get(j).get("startId")+1, (Integer) times.get(j).get("endId")-1));
                }

                weeksList.add(weeks);
                timesList.add(timeList);
                scheduleTimesList.add(scheduleTimeList);
                startDateLIst.add(jOrderContractList.get(i).getStartDate());
                endDateLIst.add(jOrderContractList.get(i).getEndDate());

            }

            //循环订单判断订单与服务师是否冲突
            for (int x = 0; x < orderIdList.size(); x++) {
                ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
                //服务师id
                serviceWeeksTimeReq.setNannyId(nannyId);
                //订单计划书需求时间值列表
                serviceWeeksTimeReq.setLongList(timesList.get(x));
                serviceWeeksTimeReq.setScheduleLongList(scheduleTimesList.get(x));
                //订单开始结束时间
                serviceWeeksTimeReq.setStartDate(startDate);
                serviceWeeksTimeReq.setEndDate(endDate);
                //weekList为星期列表，用于验证服务师的日程与订单是否冲突
                List<String> weekListTemp = new ArrayList<String>();
                for (int i = 0; i < weeksList.get(x).size(); i++) {
                    if ("true".equals(weeksList.get(x).get(i).get("active").toString())) {
                        if ("周一".equals(weeksList.get(x).get(i).get("value").toString())) {
                            serviceWeeksTimeReq.setWeekday("周一");
                            weekListTemp.add("周一");
                        }
                        if ("周三".equals(weeksList.get(x).get(i).get("value").toString())) {
                            serviceWeeksTimeReq.setWeekday("周三");
                            weekListTemp.add("周三");
                        }
                        if ("周二".equals(weeksList.get(x).get(i).get("value").toString())) {
                            serviceWeeksTimeReq.setWeekday("周二");
                            weekListTemp.add("周二");
                        }
                        if ("周四".equals(weeksList.get(x).get(i).get("value").toString())) {
                            serviceWeeksTimeReq.setWeekday("周四");
                            weekListTemp.add("周四");
                        }
                        if ("周五".equals(weeksList.get(x).get(i).get("value").toString())) {
                            serviceWeeksTimeReq.setWeekday("周五");
                            weekListTemp.add("周五");
                        }
                        if ("周六".equals(weeksList.get(x).get(i).get("value").toString())) {
                            serviceWeeksTimeReq.setWeekday("周六");
                            weekListTemp.add("周六");
                        }
                        if ("周天".equals(weeksList.get(x).get(i).get("value").toString())) {
                            serviceWeeksTimeReq.setWeekday("周天");
                            weekListTemp.add("周天");
                        }
                    }
                }
                serviceWeeksTimeReq.setWeekList(weekListTemp);

                /**换算开始结束时间*/
                Map map1 = JsonUtil.Obj2Map(serviceWeeksTimeReq);
                map1.put("startDate", new Date((Long) map1.get("startDate")));
                map1.put("endDate", new Date((Long) map1.get("endDate")));

                System.out.println(">>>>>>>>sql>>>>>>>>>>>>>" + map1.toString());
                //判断该服务师的工时是否符合订单时间要求
                List<JNannyInfo> signNannyWorkTime = jNannyWorkTimeMapper.signNannyWorkTime(map1);
                if (signNannyWorkTime.size() < 1) {
                    return new ModelRes(ModelRes.Status.FAILED, "服务师工时不匹配 ! !", null);
                }

                /**循环每个订单服务计划书的每天开始结束时间id，验证日程是否冲突*/
                List<Map> times=timeIdList.get(x);
                for (int i=0;i<times.size();i++){
                    serviceWeeksTimeReq.setStartId((Integer) times.get(i).get("startId"));
                    serviceWeeksTimeReq.setEndId((Integer) times.get(i).get("endId"));

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


            }


            /**生成日程*/
            //订单相关信息
//            List<JOrderContract> jOrderContractList = jOrderContractMapper.selectBatchIds(orderIdList);
            for (int m = 0; m < jOrderContractList.size(); m++) {
                Integer houseId = jOrderContractList.get(m).getHouseId();
                Map mapService = new HashedMap();
                mapService.put("houseId", houseId);
                //根据房产id获取服务计划书id
                List<JCustomerService> jCustomerService = jCustomerServiceMapper.selectByMap(mapService);
                //根据服务计划书去MongoDB获取计划书详情json
                Map mapMongo = new HashedMap();
                mapMongo.put("serviceId", jCustomerService.get(0).getServiceId());
                mongoDBUtil = MongoDBUtil.getInstance(new MongoConfig());
                String serviceJson = mongoDBUtil.query("customer_service", mapMongo);
                //解析json
                Map mapJson = JsonUtil.json2Map(serviceJson);
                //计划书星期列表：[{"value":"周一","id":"1","active":true},{"value":"周二","id":"2","active":true},{"value":"周三","id":"3","active":true},{"value":"周四","id":"4","active":true},{"value":"周五","id":"5","active":true},{"value":"周六","id":"6","active":true},{"value":"周天","id":"7","active":true}]
                List<Map> weeks = (List<Map>) mapJson.get("weekListTime");
                //计划书每天时间列表：[{"startId":23,"endId":25,"startTime":"03:30","start":"00:00","end":"24:00","endTime":"","firstSelect":false,"or":1},{"startId":37,"endId":39,"startTime":"03:30","start":"00:00","end":"24:00","endTime":"","firstSelect":false,"or":1}]
                List<Map> times = (List<Map>) mapJson.get("addTime");
                System.out.println(">>>>>>>times>>>" + times);

                //循环计划书中的weekListTime 即一周的七天
                for (int i = 0; i < weeks.size(); i++) {
                    if ("true".equals(weeks.get(i).get("active").toString())) {
                        if ("周一".equals(weeks.get(i).get("value").toString())) {
                            List<Date> dateList = DateUtil.weekAndDay(1, startDate, endDate);
                            //循环每天的时间区间，几个区间则分别生成几个区间的日程
                            for (int k = 0; k < times.size(); k++) {
                                //根据订单开始结束日期计算出有多少个周一，并循环
                                for (int j = 0; j < dateList.size(); j++) {
                                    //日程信息
                                    JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                    jOrderSchedule.setOrderType(163);
                                    jOrderSchedule.setScheduleStatus(152);
                                    jOrderSchedule.setPayStatus(157);
                                    jOrderSchedule.setScheduleType(scheduleType);
                                    jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                    jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                    //订单id
                                    jOrderSchedule.setOrderId(jOrderContractList.get(m).getOrderId());
                                    jOrderSchedule.setCustomerId(jOrderContractList.get(m).getCustomerId());
                                    jOrderSchedule.setShopId(jOrderContractList.get(m).getShopId());
                                    jOrderSchedule.setHouseId(jOrderContractList.get(m).getHouseId());
                                    jOrderSchedule.setServiceId(jOrderContractList.get(m).getServiceId());
                                    jOrderSchedule.setGoodsId(jOrderContractList.get(m).getGoodsId());
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
                                    //添加日程
                                    jOrderScheduleMapper.insert(jOrderSchedule);


                                }
                            }
                        }
                        if ("周二".equals(weeks.get(i).get("value").toString())) {
                            List<Date> dateList = DateUtil.weekAndDay(2, startDate, endDate);
                            for (int k = 0; k < times.size(); k++) {
                                //根据订单开始结束日期计算出有多少个周一，并循环
                                for (int j = 0; j < dateList.size(); j++) {
                                    //日程信息
                                    JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                    jOrderSchedule.setOrderType(163);
                                    jOrderSchedule.setScheduleStatus(152);
                                    jOrderSchedule.setPayStatus(157);
                                    jOrderSchedule.setScheduleType(scheduleType);
                                    jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                    jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                    //订单id
                                    jOrderSchedule.setOrderId(jOrderContractList.get(m).getOrderId());
                                    jOrderSchedule.setCustomerId(jOrderContractList.get(m).getCustomerId());
                                    jOrderSchedule.setShopId(jOrderContractList.get(m).getShopId());
                                    jOrderSchedule.setHouseId(jOrderContractList.get(m).getHouseId());
                                    jOrderSchedule.setServiceId(jOrderContractList.get(m).getServiceId());
                                    jOrderSchedule.setGoodsId(jOrderContractList.get(m).getGoodsId());
                                    jOrderSchedule.setScheduleDate(dateList.get(j));
                                    jOrderSchedule.setWeekday("周二");
                                    orderScheduleList.add(jOrderSchedule);
                                    //添加日程
                                    jOrderScheduleMapper.insert(jOrderSchedule);

                                }
                            }
                        }
                        if ("周三".equals(weeks.get(i).get("value").toString())) {
                            List<Date> dateList = DateUtil.weekAndDay(3, startDate, endDate);
                            for (int k = 0; k < times.size(); k++) {
                                //根据订单开始结束日期计算出有多少个周一，并循环
                                for (int j = 0; j < dateList.size(); j++) {
                                    //日程信息
                                    JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                    jOrderSchedule.setOrderType(163);
                                    jOrderSchedule.setScheduleStatus(152);
                                    jOrderSchedule.setPayStatus(157);
                                    jOrderSchedule.setScheduleType(scheduleType);
                                    jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                    jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                    //订单id
                                    jOrderSchedule.setOrderId(jOrderContractList.get(m).getOrderId());
                                    jOrderSchedule.setCustomerId(jOrderContractList.get(m).getCustomerId());
                                    jOrderSchedule.setShopId(jOrderContractList.get(m).getShopId());
                                    jOrderSchedule.setHouseId(jOrderContractList.get(m).getHouseId());
                                    jOrderSchedule.setServiceId(jOrderContractList.get(m).getServiceId());
                                    jOrderSchedule.setGoodsId(jOrderContractList.get(m).getGoodsId());
                                    jOrderSchedule.setScheduleDate(dateList.get(j));
                                    jOrderSchedule.setWeekday("周三");
                                    orderScheduleList.add(jOrderSchedule);
                                    //添加日程
                                    jOrderScheduleMapper.insert(jOrderSchedule);

                                }
                            }
                        }
                        if ("周四".equals(weeks.get(i).get("value").toString())) {
                            List<Date> dateList = DateUtil.weekAndDay(4, startDate, endDate);
                            for (int k = 0; k < times.size(); k++) {
                                //根据订单开始结束日期计算出有多少个周一，并循环
                                for (int j = 0; j < dateList.size(); j++) {
                                    //日程信息
                                    JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                    jOrderSchedule.setOrderType(163);
                                    jOrderSchedule.setScheduleStatus(152);
                                    jOrderSchedule.setPayStatus(157);
                                    jOrderSchedule.setScheduleType(scheduleType);
                                    jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                    jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                    //订单id
                                    jOrderSchedule.setOrderId(jOrderContractList.get(m).getOrderId());
                                    jOrderSchedule.setCustomerId(jOrderContractList.get(m).getCustomerId());
                                    jOrderSchedule.setShopId(jOrderContractList.get(m).getShopId());
                                    jOrderSchedule.setHouseId(jOrderContractList.get(m).getHouseId());
                                    jOrderSchedule.setServiceId(jOrderContractList.get(m).getServiceId());
                                    jOrderSchedule.setGoodsId(jOrderContractList.get(m).getGoodsId());
                                    jOrderSchedule.setScheduleDate(dateList.get(j));
                                    jOrderSchedule.setWeekday("周四");
                                    orderScheduleList.add(jOrderSchedule);
                                    //添加日程
                                    jOrderScheduleMapper.insert(jOrderSchedule);

                                }
                            }
                        }
                        if ("周五".equals(weeks.get(i).get("value").toString())) {
                            List<Date> dateList = DateUtil.weekAndDay(5, startDate, endDate);
                            for (int k = 0; k < times.size(); k++) {
                                //根据订单开始结束日期计算出有多少个周一，并循环
                                for (int j = 0; j < dateList.size(); j++) {
                                    //日程信息
                                    JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                    jOrderSchedule.setOrderType(163);
                                    jOrderSchedule.setScheduleStatus(152);
                                    jOrderSchedule.setPayStatus(157);
                                    jOrderSchedule.setScheduleType(scheduleType);
                                    jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                    jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                    //订单id
                                    jOrderSchedule.setOrderId(jOrderContractList.get(m).getOrderId());
                                    jOrderSchedule.setCustomerId(jOrderContractList.get(m).getCustomerId());
                                    jOrderSchedule.setShopId(jOrderContractList.get(m).getShopId());
                                    jOrderSchedule.setHouseId(jOrderContractList.get(m).getHouseId());
                                    jOrderSchedule.setServiceId(jOrderContractList.get(m).getServiceId());
                                    jOrderSchedule.setGoodsId(jOrderContractList.get(m).getGoodsId());
                                    jOrderSchedule.setScheduleDate(dateList.get(j));
                                    jOrderSchedule.setWeekday("周五");
                                    orderScheduleList.add(jOrderSchedule);
                                    //添加日程
                                    jOrderScheduleMapper.insert(jOrderSchedule);

                                }
                            }
                        }
                        if ("周六".equals(weeks.get(i).get("value").toString())) {
                            List<Date> dateList = DateUtil.weekAndDay(6, startDate, endDate);
                            for (int k = 0; k < times.size(); k++) {
                                //根据订单开始结束日期计算出有多少个周一，并循环
                                for (int j = 0; j < dateList.size(); j++) {
                                    //日程信息
                                    JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                    jOrderSchedule.setOrderType(163);
                                    jOrderSchedule.setScheduleStatus(152);
                                    jOrderSchedule.setPayStatus(157);
                                    jOrderSchedule.setScheduleType(scheduleType);
                                    jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                    jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                    //订单id
                                    jOrderSchedule.setOrderId(jOrderContractList.get(m).getOrderId());
                                    jOrderSchedule.setCustomerId(jOrderContractList.get(m).getCustomerId());
                                    jOrderSchedule.setShopId(jOrderContractList.get(m).getShopId());
                                    jOrderSchedule.setHouseId(jOrderContractList.get(m).getHouseId());
                                    jOrderSchedule.setServiceId(jOrderContractList.get(m).getServiceId());
                                    jOrderSchedule.setGoodsId(jOrderContractList.get(m).getGoodsId());
                                    jOrderSchedule.setScheduleDate(dateList.get(j));
                                    jOrderSchedule.setWeekday("周六");
                                    orderScheduleList.add(jOrderSchedule);
                                    //添加日程
                                    jOrderScheduleMapper.insert(jOrderSchedule);

                                }
                            }
                        }
                        if ("周天".equals(weeks.get(i).get("value").toString())) {
                            List<Date> dateList = DateUtil.weekAndDay(0, startDate, endDate);
                            for (int k = 0; k < times.size(); k++) {
                                //根据订单开始结束日期计算出有多少个周一，并循环
                                for (int j = 0; j < dateList.size(); j++) {
                                    //日程信息
                                    JOrderSchedule jOrderSchedule = new JOrderSchedule();
                                    jOrderSchedule.setOrderType(163);
                                    jOrderSchedule.setScheduleStatus(152);
                                    jOrderSchedule.setPayStatus(157);
                                    jOrderSchedule.setScheduleType(scheduleType);
                                    jOrderSchedule.setStartTime((Integer) times.get(k).get("startId"));
                                    jOrderSchedule.setEndTime((Integer) times.get(k).get("endId"));
                                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getStartTime()));
                                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jOrderSchedule.getEndTime()));
                                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue((Integer) times.get(k).get("startId"), (Integer) times.get(k).get("endId")));
                                    //订单id
                                    jOrderSchedule.setOrderId(jOrderContractList.get(m).getOrderId());
                                    jOrderSchedule.setCustomerId(jOrderContractList.get(m).getCustomerId());
                                    jOrderSchedule.setShopId(jOrderContractList.get(m).getShopId());
                                    jOrderSchedule.setHouseId(jOrderContractList.get(m).getHouseId());
                                    jOrderSchedule.setServiceId(jOrderContractList.get(m).getServiceId());
                                    jOrderSchedule.setGoodsId(jOrderContractList.get(m).getGoodsId());
                                    jOrderSchedule.setScheduleDate(dateList.get(j));
                                    jOrderSchedule.setWeekday("周天");
                                    orderScheduleList.add(jOrderSchedule);
                                    //添加日程
                                    jOrderScheduleMapper.insert(jOrderSchedule);

                                }
                            }
                        }
                    }
                }
            }


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
            jOrderContractList.get(0).setEndDate(endDate);
            jOrderContractMapper.updateById(jOrderContractList.get(0));

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderScheduleReq orderScheduleReq) {
        try {
            orderScheduleReq.setOrderType(163);
            orderScheduleReq.setPage(orderScheduleReq.getSize() * (orderScheduleReq.getPage() - 1));
            List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getScheduleList(orderScheduleReq);
            JOrderContract jOrderContract = jOrderContractMapper.selectById(Long.valueOf(orderScheduleReq.getOrderId()));
            JCustomerHouse jCustomerHouse = jCustomerHouseMapper.selectById(Long.valueOf(jOrderContract.getHouseId()));


            for (int i = 0; i < orderScheduleResList.size(); i++) {
                orderScheduleResList.get(i).setUnitPrice(orderScheduleResList.get(i).getScheduleCurrentPrice());
                if (orderScheduleResList.get(i).getClockLatitude() != null) {
                    orderScheduleResList.get(i).setClockDistance(TencentMapUtil.getDistance(orderScheduleResList.get(i).getClockLongitude(), orderScheduleResList.get(i).getClockLatitude(), jCustomerHouse.getLongitude(), jCustomerHouse.getLatitude()));
                }
                //判断服务师工资是否为0，是则实时计算出服务师当前工资
                if (orderScheduleResList.get(i).getCost().compareTo(new BigDecimal(0)) == 0) {
                    orderScheduleResList.get(i).setCost(new BigDecimal(orderScheduleResList.get(i).getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((orderScheduleResList.get(i).getEndTime() - orderScheduleResList.get(i).getStartTime()) / 2f))).setScale(2));
                }
                //判断日程总价是否为0，是则实时计算出当前总价
                if (orderScheduleResList.get(i).getTotalPrice().compareTo(new BigDecimal(0)) == 0) {
                    orderScheduleResList.get(i).setTotalPrice(orderScheduleResList.get(i).getScheduleCurrentPrice().multiply(new BigDecimal(String.valueOf((orderScheduleResList.get(i).getEndTime() - orderScheduleResList.get(i).getStartTime()) / 2f))).setScale(2));
                }
            }
            Map map = ResponseUtil.List2Map(orderScheduleResList);
            map.put("count", jOrderScheduleMapper.getScheduleCount(orderScheduleReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 暂停（停用）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/suspend")
    public Object suspend(@RequestBody Map<String, List<Integer>> jOrderScheduleList) {
        try {
            List<Integer> list = jOrderScheduleList.get("list");
            List<JOrderSchedule> orderScheduleList = new ArrayList<JOrderSchedule>();

            for (int i = 0; i < list.size(); i++) {
                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                jOrderSchedule.setScheduleId(list.get(i));
                jOrderSchedule.setScheduleStatus(154);
                jOrderSchedule.setSuspendTime(new Date());
                orderScheduleList.add(jOrderSchedule);
                jOrderScheduleMapper.updateById(jOrderSchedule);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 签到
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping("/clock")
    public Object clock(@RequestBody ScheduleClockReq scheduleClockReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            /**查询日程列表*/
            List<Integer> list = scheduleClockReq.getList();
            List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getScheduleListByList(list);

            for (OrderScheduleRes orderScheduleRes : orderScheduleResList) {
                if (orderScheduleRes.getScheduleStatus() == 153) {
                    return new ModelRes(ModelRes.Status.FAILED, "有订单已签到 !", orderScheduleRes);
                }
                if (orderScheduleRes.getScheduleStatus() == 155) {
                    return new ModelRes(ModelRes.Status.FAILED, "有订单已取消 !", orderScheduleRes);
                }
                /**判断签到时间在不在服务时间点之后**/
                if (DateUtil.parseStringToDate(DateUtil.parseDateToString(orderScheduleRes.getScheduleDate(),DateUtil.PATTERN_yyyy_MM_dd)+orderScheduleRes.getEndTimeValue(),DateUtil.PATTERN_yyyy_MM_HHmm).after(new Date())) {
                    return new ModelRes(ModelRes.Status.FAILED, "有订单未到签到时间，无法签到 !", orderScheduleRes);
                }
                //判断服务师工资是否为0，是则实时计算出服务师当前工资
                /** orderPriceType标记订单是否为自定义价格（即手动修改的价格,1表示手动修改） */
                int orderPriceType=1;
                if (orderScheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                    orderPriceType=0;
                    orderScheduleRes.setCost(new BigDecimal(orderScheduleRes.getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                }
                orderScheduleRes.setUnitPrice(orderScheduleRes.getScheduleCurrentPrice());
                //判断日程总价是否为0，是则实时计算出当前总价A
                if (orderScheduleRes.getTotalPrice().compareTo(new BigDecimal(0)) == 0) {
                    orderScheduleRes.setTotalPrice(orderScheduleRes.getScheduleCurrentPrice().multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                }

                /**订单*/
                JOrderContract jOrderContractBalance = jOrderContractMapper.selectById(Long.valueOf(orderScheduleRes.getOrderId()));
                /**客户*/
                JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderContractBalance.getCustomerId()));
                /**余额*/
                BigDecimal customerBalance = jCustomer.getCustomerBalance();


                Map map = new HashedMap();
                map.put("scheduleId", orderScheduleRes.getScheduleId());
                List<JScheduleNanny> jScheduleNannyList = jScheduleNannyMapper.selectByMap(map);
                if (jScheduleNannyList.get(0).getSupplierId() == 1) {
                    JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(jScheduleNannyList.get(0).getNannyId()));
                    if (jNannyInfo.getNannyStatus() != 56) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师未培训成功", jNannyInfo);
                    }
                    if (jNannyInfo.getDataSignStatus() != 255) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师工作信息验证未通过！请先通过验证", jNannyInfo);
                    }
                }

                String serviceTime = orderScheduleRes.getStartTimeValue() + "-" + orderScheduleRes.getEndTimeValue();
                Double serviceTimeLength = Double.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f);


                /**新增服务师对账单*/
                JNannyStatment jNannyStatment = new JNannyStatment(OrderUtil.generateStamentNumber(jCustomer.getCustomerId()), jScheduleNannyList.get(0).getNannyId(),
                        orderScheduleRes.getScheduleId(), orderScheduleRes.getOrderId(),
                        jOrderContractBalance.getShopId(), jOrderContractBalance.getHouseId(), jOrderContractBalance.getCustomerId(),
                        orderScheduleRes.getScheduleType()==179?281:162, orderScheduleRes.getCost(), 163, jOrderContractBalance.getGoodsId(), serviceTime, serviceTimeLength,
                        orderScheduleRes.getScheduleDate(), "",jAdmin.getAdminId(),orderScheduleRes.getNannyLevel(),new BigDecimal(orderScheduleRes.getNannyCurrentPayment()),orderPriceType);
                jNannyStatmentMapper.insert(jNannyStatment);

                /**计算客户余额*/
                customerBalance = customerBalance.subtract(orderScheduleRes.getTotalPrice());

                /**新增客户对账单*/
                JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jCustomer.getCustomerId()),
                        jOrderContractBalance.getCustomerId(), jOrderContractBalance.getGoodsId(), jOrderContractBalance.getHouseId(),
                        jOrderContractBalance.getOrderId(), 163, orderScheduleRes.getScheduleId(),
                        jOrderContractBalance.getShopId(), serviceTime, serviceTimeLength,
                        orderScheduleRes.getScheduleDate(), new Date(), 39, orderScheduleRes.getTotalPrice(),
                        48, jAdmin.getAdminId(), 49, 53, customerBalance,
                        "", "");
                jCustomerStatmentMapper.insert(jCustomerStatment);
                /**修改客户账户余额*/
                jCustomer.setCustomerBalance(customerBalance);
                /**给客户添加积分*/
                jCustomer.setCustomerCredit(jCustomer.getCustomerCredit()+1);
                jCustomerMapper.updateById(jCustomer);

                JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(orderScheduleRes.getNannyId());
                jNannyInfo.setNannyCredit(jNannyInfo.getNannyCredit()+1);
                jNannyInfoMapper.updateById(jNannyInfo);


                /**修改日程状态*/
                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                jOrderSchedule.setClockLatitude(scheduleClockReq.getClockLatitude());
                jOrderSchedule.setClockLongitude(scheduleClockReq.getClockLongitude());
                jOrderSchedule.setRemark(orderScheduleRes.getRemark()+scheduleClockReq.getReason());
                jOrderSchedule.setScheduleId(orderScheduleRes.getScheduleId());
                /**日程支付状态为已支付*/
                jOrderSchedule.setPayStatus(158);
                jOrderSchedule.setClockId(jAdmin.getAdminId());
                jOrderSchedule.setClockTime(new Date());
                jOrderSchedule.setScheduleStatus(153);
                jOrderScheduleMapper.updateById(jOrderSchedule);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 完工日程并生成服务师对账单（停用）
     *
     * @param list
     * @return
     */
    @ResponseBody
    @RequestMapping("/complete")
    public Object complete(@RequestBody Map<String, List<Integer>> list,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            //服务师对账单列表
            List<JNannyStatment> jNannyStatmentList = new ArrayList<JNannyStatment>();

            //根据日程id列表查出日程信息
            List<Integer> integerList = list.get("list");
            List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getScheduleListByList(integerList);

            //判断支付状态
            for (int i = 0; i < orderScheduleResList.size(); i++) {
                if (orderScheduleResList.get(i).getPayStatus() == 158) {
                    return new ModelRes(ModelRes.Status.FAILED, "有订单已结算 !", null);
                }
            }

            //存放并循环更新客户余额
            //获取客户初始余额
            JOrderContract jOrderContractBalance = jOrderContractMapper.selectById(Long.valueOf(orderScheduleResList.get(0).getOrderId()));
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderContractBalance.getCustomerId()));

            //判断日程价格和服务师薪资是否需要实时计算
            for (int i = 0; i < orderScheduleResList.size(); i++) {
                //判断服务师工资是否为0，是则实时计算出服务师当前工资
                if (orderScheduleResList.get(i).getCost().compareTo(new BigDecimal(0)) == 0) {
                    orderScheduleResList.get(i).setCost(new BigDecimal(orderScheduleResList.get(i).getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((orderScheduleResList.get(i).getEndTime() - orderScheduleResList.get(i).getStartTime()) / 2f))).setScale(2));
                }
            }


            for (int i = 0; i < orderScheduleResList.size(); i++) {

                //新增客户对账单
                JOrderContract jOrderContract = jOrderContractMapper.selectById(Long.valueOf(orderScheduleResList.get(i).getOrderId()));
                String serviceTime = orderScheduleResList.get(i).getStartTimeValue() + "-" + orderScheduleResList.get(i).getEndTimeValue();
                Double serviceTimeLength = Double.valueOf((orderScheduleResList.get(i).getEndTime() - orderScheduleResList.get(i).getStartTime()) / 2f);


                //新增服务师对账单
                Map map = new HashedMap();
                map.put("scheduleId", orderScheduleResList.get(i).getScheduleId());
                List<JScheduleNanny> jScheduleNannyList = jScheduleNannyMapper.selectByMap(map);
//                JNannyStatment jNannyStatment = new JNannyStatment(OrderUtil.generateStamentNumber(jCustomer.getCustomerId()), jScheduleNannyList.get(0).getNannyId(),
//                        orderScheduleResList.get(i).getScheduleId(), orderScheduleResList.get(i).getOrderId(),
//                        jOrderContract.getShopId(), jOrderContract.getHouseId(), jOrderContract.getCustomerId(),
//                        162, orderScheduleResList.get(i).getCost(), 163, jOrderContract.getShopId(), serviceTime, serviceTimeLength,
//                        orderScheduleResList.get(i).getScheduleDate(), "",jAdmin.getAdminId());
//                jNannyStatmentList.add(jNannyStatment);


            }
//            jNannyStatmentMapper.insertBatch(jNannyStatmentList);

            //更新完工时间以及日程状态
            List<JOrderSchedule> jOrderScheduleList = new ArrayList<JOrderSchedule>();
            for (int i = 0; i < orderScheduleResList.size(); i++) {
                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                jOrderSchedule.setScheduleId(orderScheduleResList.get(i).getScheduleId());
                jOrderSchedule.setScheduleStatus(156);
                jOrderScheduleList.add(jOrderSchedule);
            }
//            jOrderScheduleMapper.updateBatchById(jOrderScheduleList);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 结算日程并生成客户对账单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkout")
    public Object checkout(@RequestBody Map<String, List<Integer>> list, ServletRequest request) {
        try {

            //获取管理员id
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            Integer adminId = jAdmin.getAdminId();

            //根据日程id列表查出日程信息
            List<Integer> integerList = list.get("list");
            List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getScheduleListByList(integerList);

            for (OrderScheduleRes orderScheduleRes : orderScheduleResList) {
                //判断支付状态和日程状态
                if (orderScheduleRes.getPayStatus() == 158) {
                    return new ModelRes(ModelRes.Status.FAILED, "有日程已结算 !", orderScheduleRes);
                }

                //存放并循环更新客户余额
                //获取客户初始余额
                JOrderContract jOrderContractBalance = jOrderContractMapper.selectById(Long.valueOf(orderScheduleRes.getOrderId()));
                JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderContractBalance.getCustomerId()));
                BigDecimal customerBalance = jCustomer.getCustomerBalance();


                //判断日程价格和服务师薪资是否需要实时计算
                //判断服务师工资是否为0，是则实时计算出服务师当前工资
                if (orderScheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                    orderScheduleRes.setCost(new BigDecimal(orderScheduleRes.getNannyCurrentPayment()).multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                }
                orderScheduleRes.setUnitPrice(orderScheduleRes.getScheduleCurrentPrice());
                //判断日程总价是否为0，是则实时计算出当前总价A
                if (orderScheduleRes.getTotalPrice().compareTo(new BigDecimal(0)) == 0) {
                    orderScheduleRes.setTotalPrice(orderScheduleRes.getScheduleCurrentPrice().multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                }

                //计算客户余额
                customerBalance = customerBalance.subtract(orderScheduleRes.getTotalPrice());
                if (customerBalance.compareTo(new BigDecimal(0)) < 0) {
                    return new ModelRes(ModelRes.Status.FAILED, "余额不足", orderScheduleRes);
                }

                //新增客户对账单
                JOrderContract jOrderContract = jOrderContractMapper.selectById(Long.valueOf(orderScheduleRes.getOrderId()));
                String serviceTime = orderScheduleRes.getStartTimeValue() + "-" + orderScheduleRes.getEndTimeValue();
                Double serviceTimeLength = Double.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f);


                JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jCustomer.getCustomerId()),
                        jOrderContract.getCustomerId(), jOrderContract.getGoodsId(), jOrderContract.getHouseId(),
                        jOrderContract.getOrderId(), 163, orderScheduleRes.getScheduleId(),
                        jOrderContract.getShopId(), serviceTime, serviceTimeLength,
                        orderScheduleRes.getScheduleDate(), new Date(), 39, orderScheduleRes.getTotalPrice(),
                        48, adminId, 49, 53, customerBalance,
                        "", "");
                jCustomerStatmentMapper.insert(jCustomerStatment);

                //修改客户账户余额
                jCustomer.setCustomerBalance(customerBalance);
                jCustomerMapper.updateById(jCustomer);

                //更新日程支付状态
                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                jOrderSchedule.setScheduleId(orderScheduleRes.getScheduleId());
                jOrderSchedule.setPayStatus(158);
                jOrderScheduleMapper.updateById(jOrderSchedule);
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
