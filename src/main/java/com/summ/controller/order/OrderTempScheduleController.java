package com.summ.controller.order;

import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JGoodsCostMapper;
import com.summ.mapper.JSupplierMapper;
import com.summ.model.*;
import com.summ.model.request.OrderScheduleReq;
import com.summ.model.request.OrderTempNannyReq;
import com.summ.model.request.ServiceWeeksTimeReq;
import com.summ.model.response.*;
import com.summ.utils.*;
import org.apache.commons.collections.map.HashedMap;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * @author summ
 */
@Controller
@RequestMapping("/order/temp/schedule")
public class OrderTempScheduleController extends AutoMapperController {

    /**
     * 若选自营服务，查找符合订单时间和服务师工时的自营服务师
     *
     * @param orderTempNannyReq
     * @return
     */
    @RequestMapping("/nanny")
    @ResponseBody
    public Object getNannyInfo(@RequestBody OrderTempNannyReq orderTempNannyReq) {
        try {
//            orderTempNannyReq.setTimeList(NannyWorkTimeUtil.id2Value(orderTempNannyReq.getStartId(),orderTempNannyReq.getEndId()));
//            Map map = JsonUtil.Obj2Map(orderTempNannyReq);
//            map.put("date",orderTempNannyReq.getDate());

            JOrderTemp jOrderTemp = jOrderTempMapper.selectById(orderTempNannyReq.getOrderId());

            ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
            serviceWeeksTimeReq.setShopId(jOrderTemp.getShopId());
            serviceWeeksTimeReq.setGoodsId(orderTempNannyReq.getGoodsId());
            serviceWeeksTimeReq.setName(orderTempNannyReq.getName());
            serviceWeeksTimeReq.setWeekday(DateUtil.dateAndday(orderTempNannyReq.getDate()));
            serviceWeeksTimeReq.setLongList(NannyWorkTimeUtil.id2Value(orderTempNannyReq.getStartId(), orderTempNannyReq.getEndId()));
//            serviceWeeksTimeReq.setScheduleLongList(NannyWorkTimeUtil.id2Value(orderTempNannyReq.getStartId()+1, orderTempNannyReq.getEndId()-1));
            serviceWeeksTimeReq.setStartId(orderTempNannyReq.getStartId());
            serviceWeeksTimeReq.setEndId(orderTempNannyReq.getEndId());
            List<String> weekList = new ArrayList<String>();
            serviceWeeksTimeReq.setWeekList(weekList);
            serviceWeeksTimeReq.setStartDate(orderTempNannyReq.getDate());
            serviceWeeksTimeReq.setEndDate(new Date(orderTempNannyReq.getDate().getTime() + (24 * 3600 * 1000-1)));

            /**换算开始结束时间*/
            Map map = JsonUtil.Obj2Map(serviceWeeksTimeReq);
            map.put("startDate",new Date((Long) map.get("startDate")));
            map.put("endDate",new Date((Long) map.get("endDate")));

            List<OrderTempNannyRes> list = jNannyInfoMapper.getOrderTempNannyList(map);
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", ResponseUtil.List2Map(list));
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
            String supplierName = (String) map.get("supplierName");
            List<OrderTempSupplierRes> list = jGoodsCostMapper.getSupplierList(orderId, supplierName);
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", ResponseUtil.List2Map(list));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }

    }

    /**
     * 若选带教，查找符合订单时间和带教工时的带教列表
     *
     * @param orderTempNannyReq
     * @return
     */
    @RequestMapping("/teacher")
    @ResponseBody
    public Object getTeacherInfo(@RequestBody OrderTempNannyReq orderTempNannyReq) {
        try {
            ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
            serviceWeeksTimeReq.setWeekday(DateUtil.dateAndday(orderTempNannyReq.getDate()));
            serviceWeeksTimeReq.setLongList(NannyWorkTimeUtil.id2Value(orderTempNannyReq.getStartId(), orderTempNannyReq.getEndId()));
            serviceWeeksTimeReq.setScheduleLongList(NannyWorkTimeUtil.id2Value(orderTempNannyReq.getStartId()+1, orderTempNannyReq.getEndId()-1));
            serviceWeeksTimeReq.setStartId(orderTempNannyReq.getStartId());
            serviceWeeksTimeReq.setEndId(orderTempNannyReq.getEndId());
            List<String> weekList = new ArrayList<String>();
            serviceWeeksTimeReq.setWeekList(weekList);
            serviceWeeksTimeReq.setStartDate(orderTempNannyReq.getDate());
            serviceWeeksTimeReq.setEndDate(new Date(orderTempNannyReq.getDate().getTime() + (24 * 3600 * 1000-1)));

            /**换算开始结束时间*/
            Map map = JsonUtil.Obj2Map(serviceWeeksTimeReq);
            map.put("startDate",new Date((Long) map.get("startDate")));
            map.put("endDate",new Date((Long) map.get("endDate")));

            List<TeacherRes> list = jTeacherMapper.getOrderTeacherList(map);

            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", ResponseUtil.List2Map(list));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }

    }

    /**
     * 生成日程
     *
     * @param map
     * @return
     */
    @RequestMapping("/generate")
    @ResponseBody
    public Object generate(@RequestBody Map map) {
        try {
            /**type区分是自营还是供应商*/
            Integer type = (Integer) map.get("type");
            Integer orderId = (Integer) map.get("orderId");
            Integer nannyId = (Integer) map.get("id");
            Integer startTime = (Integer) map.get("startTime");
            Integer endTime = (Integer) map.get("endTime");
            Integer teacherId = (Integer) map.get("teacherId");
            JOrderTemp jOrderTemp = jOrderTempMapper.selectById(Long.valueOf(orderId));

            /**当选择自营服务师或者带教时候进行工种和门店的判断*/
            if (type==1||type==-1){
                List<JNannyInfo> jNannyInfoList = jNannyJobLevelGoodsMapper.signNannyAndGoods(nannyId,jOrderTemp.getGoodsId());
                if (jNannyInfoList.size()==0){
                    return new ModelRes(ModelRes.Status.FAILED, "服务师工种不匹配 ! !", null);
                }

                List<JNannyShop> jNannyShopList = jNannyShopMapper.signNannyShopByShop(nannyId,jOrderTemp.getShopId());
                if (jNannyShopList.size()==0){
                    return new ModelRes(ModelRes.Status.FAILED, "服务师门店不匹配 ! !", null);
                }
            }

            JScheduleNanny jScheduleNanny = new JScheduleNanny();
            /**
             * 使用自营服务师
             */
            if (type == 1) {
                //验证订单时间和服务师时间是否冲突
                ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
                serviceWeeksTimeReq.setNannyId(nannyId);
                serviceWeeksTimeReq.setWeekday(DateUtil.dateAndday(jOrderTemp.getOrderDate()));
                serviceWeeksTimeReq.setLongList(NannyWorkTimeUtil.id2Value(startTime, endTime));
                serviceWeeksTimeReq.setScheduleLongList(NannyWorkTimeUtil.id2Value(startTime+1, endTime-1));
                serviceWeeksTimeReq.setStartId(startTime);
                serviceWeeksTimeReq.setEndId(endTime);
                List<String> weekList = new ArrayList<String>();
                serviceWeeksTimeReq.setWeekList(weekList);
                serviceWeeksTimeReq.setStartDate(jOrderTemp.getOrderDate());
                serviceWeeksTimeReq.setEndDate(new Date(jOrderTemp.getOrderDate().getTime() + (24 * 3600 * 1000)-1));

                /**换算开始结束时间*/
                Map map1 = JsonUtil.Obj2Map(serviceWeeksTimeReq);
                map1.put("startDate",new Date((Long) map1.get("startDate")));
                map1.put("endDate",new Date((Long) map1.get("endDate")));
                //判断该服务师的工时是否符合订单时间要求
                List<JNannyInfo> signNannyWorkTime = jNannyWorkTimeMapper.signNannyWorkTime(map1);
                if (signNannyWorkTime.size() < 1) {
                    return new ModelRes(ModelRes.Status.FAILED, "服务师工时不匹配 ! !", null);
                }
                //判断该服务师的日程是否符合订单时间要求
                List<JOrderSchedule> signNannySchedule = jOrderScheduleMapper.signNannySchedule(map1);
                if (signNannySchedule.size() > 0) {
                    return new ModelRes(ModelRes.Status.FAILED, "服务师日程占用 ! !", ResponseUtil.List2Map(signNannySchedule));
                }

                jScheduleNanny.setSupplierId(1);
                jScheduleNanny.setNannyId(nannyId);
            }
            if (type == -1) {
                /**此处验证带教工时*/
                //...
            }
            JOrderSchedule jOrderSchedule = new JOrderSchedule();
            if (jOrderTemp.getPayStatus()==158){
                jOrderSchedule.setPayStatus(158);
            }
            jOrderSchedule.setOrderId(jOrderTemp.getOrderId());
            jOrderSchedule.setCustomerId(jOrderTemp.getCustomerId());
            jOrderSchedule.setShopId(jOrderTemp.getShopId());
            jOrderSchedule.setHouseId(jOrderTemp.getHouseId());
            jOrderSchedule.setGoodsId(jOrderTemp.getGoodsId());
            jOrderSchedule.setOrderType(164);
            jOrderSchedule.setScheduleDate(jOrderTemp.getOrderDate());
            jOrderSchedule.setStartTime(startTime);
            jOrderSchedule.setEndTime(endTime);
            jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(startTime));
            jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(endTime));
            jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue(startTime, endTime));
            jOrderSchedule.setWeekday(DateUtil.dateAndday(jOrderTemp.getOrderDate()));
            jOrderScheduleMapper.insert(jOrderSchedule);

            /**生成日程服务师*/
            jScheduleNanny.setNannyId(nannyId);
            if (type == 1) {
                jScheduleNanny.setSupplierId(type);
            } else if (type == -1) {
                jScheduleNanny.setSupplierId(type);
            } else {
                jScheduleNanny.setSupplierId(nannyId);
            }
            jScheduleNanny.setScheduleId(jOrderSchedule.getScheduleId());
            jScheduleNannyMapper.insert(jScheduleNanny);

            jOrderTemp.setTeacherId(teacherId);
            jOrderTempMapper.updateById(jOrderTemp);
            return new ModelRes(ModelRes.Status.SUCCESS, "已生成日程!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderScheduleReq orderScheduleReq) {
        try {
            /**指定为临时订单表的orderId*/
            orderScheduleReq.setOrderType(164);

            JOrderTemp jOrderTemp = jOrderTempMapper.selectById(Long.valueOf(orderScheduleReq.getOrderId()));
            List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getTempScheduleList(orderScheduleReq.getOrderId());

            for (OrderScheduleRes orderScheduleRes : orderScheduleResList) {
                /**判断是不是需要实时计算成本 0则表明需要实时计算*/
                if (orderScheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                    /**判断日程是自营还是供应商提供 1为自营*/
                    if (orderScheduleRes.getSupplierId() == 1) {
                        JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(orderScheduleRes.getNannyId()));
                        JDictInfo jDictInfo = jDictInfoMapper.selectById(Long.valueOf(jNannyInfo.getNannyLevel()));
                        /**根据服务师星级小时工资与日程工作时长计算成本*/
                        orderScheduleRes.setCost(new BigDecimal(jDictInfo.getInfo()).multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                        orderScheduleRes.setNannyName(jNannyInfo.getNannyName());
                        orderScheduleRes.setNannyPhone(jNannyInfo.getNannyPhone());
                    } else if (orderScheduleRes.getSupplierId() == -1) {
                        /**此处验证带教工时*/
                        //...
                    } else {
                        JSupplier jSupplier = jSupplierMapper.selectById(Long.valueOf(orderScheduleRes.getSupplierId()));
                        JCompanyData jCompanyData = jCompanyDataMapper.selectById(Long.valueOf(jSupplier.getCompanyId()));
                        Map map = new HashMap();
                        map.put("goodsId", jOrderTemp.getGoodsId());
                        map.put("supplierId", orderScheduleRes.getSupplierId());
                        List<JGoodsCost> jGoodsCostList = jGoodsCostMapper.selectByMap(map);
                        /**根据供应商单位成本与订单产品的数量（平方米/个数）计算成本*/
                        orderScheduleRes.setCost(jGoodsCostList.get(0).getCost().multiply(new BigDecimal(jOrderTemp.getServiceAmount())).setScale(2));
                        orderScheduleRes.setNannyPhone(jSupplier.getPhone());
                        orderScheduleRes.setNannyName(jCompanyData.getCompanyName());
                    }
                }
            }
            Map map = ResponseUtil.List2Map(orderScheduleResList);
            map.put("count", jOrderScheduleMapper.getScheduleCount(orderScheduleReq));
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 暂停
     *
     * @param jOrderScheduleList
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
                jOrderSchedule.setScheduleStatus(154);
                jOrderSchedule.setScheduleId(list.get(i));
                jOrderSchedule.setSuspendTime(new Date());
                orderScheduleList.add(jOrderSchedule);
                jOrderScheduleMapper.updateById(jOrderSchedule);
            }
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 签到
     *
     * @param listMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/clock")
    public Object clock(@RequestBody Map<String, List<Integer>> listMap,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            List<Integer> list = listMap.get("list");
            List<JOrderSchedule> orderScheduleList = new ArrayList<JOrderSchedule>();

            List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getTempScheduleListByList(list);

            /**服务师对账单列表*/
            List<JNannyStatment> jNannyStatmentList = new ArrayList<JNannyStatment>();
            /**供应商对账单列表*/
            List<JSupplierStatment> jSupplierStatmentList = new ArrayList<JSupplierStatment>();
            /**带教对账单列表*/
            List<JTeacherStatment> jTeacherStatmentList = new ArrayList<JTeacherStatment>();


            for (OrderScheduleRes orderScheduleRes : orderScheduleResList) {
                if (orderScheduleRes.getScheduleStatus()!=152){
                    return new ModelRes(ModelRes.Status.FAILED, "有日程状态不是待完工",orderScheduleRes);
                }
                /**判断签到时间在不在服务时间点之后**/
                if (DateUtil.parseStringToDate(DateUtil.parseDateToString(orderScheduleRes.getScheduleDate(),DateUtil.PATTERN_yyyy_MM_dd)+orderScheduleRes.getEndTimeValue(),DateUtil.PATTERN_yyyy_MM_HHmm).after(new Date())) {
                    return new ModelRes(ModelRes.Status.FAILED, "有订单未到签到时间，无法签到 !", orderScheduleRes);
                }
                JOrderTemp jOrderTemp = jOrderTempMapper.selectById(Long.valueOf(orderScheduleRes.getOrderId()));

                Map map1 = new HashedMap();
                map1.put("scheduleId", orderScheduleRes.getScheduleId());
                List<JScheduleNanny> jScheduleNannyList = jScheduleNannyMapper.selectByMap(map1);
                if (jScheduleNannyList.get(0).getSupplierId() == 1) {
                    JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(jScheduleNannyList.get(0).getNannyId()));
                    if (jNannyInfo.getDataSignStatus() != 255) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师工作信息验证未通过！请先通过验证", jNannyInfo);
                    }
                    if (jNannyInfo.getNannyStatus() != 56) {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师未培训成功", jNannyInfo);
                    }
                }

                /** 服务师当前实时星级84、85、86 或者带教当前星级216*/
                Integer currentJobLevel=0;
                /** 服务师当前小时薪资或者带教当前小时薪资 */
                BigDecimal currentUnitPrice=new BigDecimal(0);
                /** 标记订单是否为自定义价格（即手动修改的价格,1表示手动修改） */
                Integer orderPriceType=1;
                /**判断是不是需要实时计算成本 0则表明需要实时计算*/
                if (orderScheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                    orderPriceType=0;
                    //自营服务师
                    if (orderScheduleRes.getSupplierId() == 1) {
                        JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(orderScheduleRes.getNannyId()));
                        JDictInfo jDictInfo = jDictInfoMapper.selectById(Long.valueOf(jNannyInfo.getNannyLevel()));
                        currentJobLevel=jNannyInfo.getNannyLevel();
                        currentUnitPrice=new BigDecimal(jDictInfo.getInfo());
                        /**根据服务师星级小时工资与日程工作时长计算成本*/
                        orderScheduleRes.setCost(new BigDecimal(jDictInfo.getInfo()).multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                    }
                    //带教
                    else if (orderScheduleRes.getSupplierId() == -1) {
                        JTeacher jTeacher = jTeacherMapper.selectById(Long.valueOf(orderScheduleRes.getNannyId()));
                        JDictInfo jDictInfo = jDictInfoMapper.selectById(Long.valueOf(jTeacher.getTeacherLevel()));
                        currentJobLevel=jTeacher.getTeacherLevel();
                        currentUnitPrice=new BigDecimal(jDictInfo.getInfo());
                        /**根据带教星级小时工资与日程工作时长计算成本*/
                        orderScheduleRes.setCost(new BigDecimal(jDictInfo.getInfo()).multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                    }
                    else {
                        Map map = new HashMap();
                        map.put("supplierId", orderScheduleRes.getSupplierId());
                        map.put("goodsId", jOrderTemp.getGoodsId());
                        List<JGoodsCost> jGoodsCostList = jGoodsCostMapper.selectByMap(map);
                        /**根据供应商单位成本与订单产品的数量（平方米/个数）计算成本*/
                        orderScheduleRes.setCost(jGoodsCostList.get(0).getCost().multiply(new BigDecimal(jOrderTemp.getServiceAmount())).setScale(2));
                    }
                }

                /**服务时间*/
                String serviceTime = orderScheduleRes.getStartTimeValue() + "-" + orderScheduleRes.getEndTimeValue();
                Double serviceTimeLength = Double.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f);
                /**判断日程是自营还是供应商提供 1为自营*/
                if (orderScheduleRes.getSupplierId() == 1) {
                    JNannyStatment jNannyStatment = new JNannyStatment(OrderUtil.generateStamentNumber(orderScheduleRes.getNannyId()),orderScheduleRes.getNannyId(),
                            orderScheduleRes.getScheduleId(), orderScheduleRes.getOrderId(),
                            jOrderTemp.getShopId(), jOrderTemp.getHouseId(), jOrderTemp.getCustomerId(),
                            orderScheduleRes.getScheduleType()==179?281:166, orderScheduleRes.getCost(), 164, jOrderTemp.getGoodsId(), serviceTime, serviceTimeLength,
                            orderScheduleRes.getScheduleDate(), "",jAdmin.getAdminId(),currentJobLevel,currentUnitPrice,orderPriceType);
                    jNannyStatmentMapper.insert(jNannyStatment);

                    /**给客户和服务师添加积分*/
                    JCustomer jCustomer = jCustomerMapper.selectById(jOrderTemp.getCustomerId());
                    JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(orderScheduleRes.getNannyId());
                    jCustomer.setCustomerCredit(jCustomer.getCustomerCredit()+1);
                    jNannyInfo.setNannyCredit(jNannyInfo.getNannyCredit()+1);
                    jCustomerMapper.updateById(jCustomer);
                    jNannyInfoMapper.updateById(jNannyInfo);

                    jNannyStatmentList.add(jNannyStatment);

                }
                else if (orderScheduleRes.getSupplierId() == -1){
                    JTeacherStatment jTeacherStatment = new JTeacherStatment(OrderUtil.generateStamentNumber(orderScheduleRes.getNannyId()),
                            orderScheduleRes.getNannyId(),
                            orderScheduleRes.getScheduleId(), orderScheduleRes.getOrderId(),
                            jOrderTemp.getShopId(), jOrderTemp.getHouseId(), jOrderTemp.getCustomerId(),
                            orderScheduleRes.getScheduleType()==179?281:166, orderScheduleRes.getCost(), 164, jOrderTemp.getGoodsId(), serviceTime, serviceTimeLength,
                            orderScheduleRes.getScheduleDate(), "",jAdmin.getAdminId(),currentJobLevel,currentUnitPrice,orderPriceType);
                    jTeacherStatmentMapper.insert(jTeacherStatment);
                    jTeacherStatmentList.add(jTeacherStatment);
                }
                else {
                    Map map = new HashMap();
                    map.put("supplierId", orderScheduleRes.getSupplierId());
                    map.put("goodsId", jOrderTemp.getGoodsId());
                    List<JGoodsCost> jGoodsCostList = jGoodsCostMapper.selectByMap(map);
                    JSupplierStatment jSupplierStatment = new JSupplierStatment(OrderUtil.generateStamentNumber(orderScheduleRes.getNannyId()),
                            orderScheduleRes.getSupplierId(), orderScheduleRes.getScheduleId(), orderScheduleRes.getOrderId(),
                            jOrderTemp.getShopId(), jOrderTemp.getHouseId(), jOrderTemp.getCustomerId(), 174, orderScheduleRes.getCost(),
                            jGoodsCostList.get(0).getCost().multiply(new BigDecimal(jOrderTemp.getServiceAmount())).setScale(2),
                            164, jOrderTemp.getGoodsId(), serviceTime, serviceTimeLength, orderScheduleRes.getScheduleDate(), new Date(), "");
                    jSupplierStatmentMapper.insert(jSupplierStatment);
                    jSupplierStatmentList.add(jSupplierStatment);
                }
            }

            for (int i = 0; i < list.size(); i++) {
                JOrderSchedule jOrderSchedule = new JOrderSchedule();
                jOrderSchedule.setScheduleStatus(153);
                jOrderSchedule.setScheduleId(list.get(i));
                jOrderSchedule.setClockTime(new Date());
                jOrderSchedule.setClockId(jAdmin.getAdminId());
                orderScheduleList.add(jOrderSchedule);
                jOrderScheduleMapper.updateById(jOrderSchedule);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 完工
     *
     * @param jOrderScheduleList
     * @return
     */
    @ResponseBody
    @RequestMapping("/complete")
    public Object complete(@RequestBody Map<String, List<Integer>> jOrderScheduleList,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            List<Integer> list = jOrderScheduleList.get("list");
            List<JOrderSchedule> orderScheduleList = new ArrayList<JOrderSchedule>();



            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 结算
     *
     * @param list
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkout")
    public Object checkout(@RequestBody Map<String, List<Integer>> list, ServletRequest request) {
        JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
        List<Integer> scheduleIdList = list.get("list");
        List<OrderScheduleRes> orderScheduleResList = jOrderScheduleMapper.getTempScheduleListByList(scheduleIdList);

        /**服务师对账单列表*/
        List<JNannyStatment> jNannyStatmentList = new ArrayList<JNannyStatment>();
        /**供应商对账单列表*/
        List<JSupplierStatment> jSupplierStatmentList = new ArrayList<JSupplierStatment>();
        /**带教对账单列表*/
        List<JTeacherStatment> jTeacherStatmentList = new ArrayList<JTeacherStatment>();


        for (OrderScheduleRes orderScheduleRes : orderScheduleResList) {
            JOrderTemp jOrderTemp = jOrderTempMapper.selectById(Long.valueOf(orderScheduleRes.getOrderId()));

            /** 服务师当前实时星级84、85、86 或者带教当前星级216*/
            Integer currentJobLevel=0;
            /** 服务师当前小时薪资或者带教当前小时薪资 */
            BigDecimal currentUnitPrice=new BigDecimal(0);
            /** 标记订单是否为自定义价格（即手动修改的价格,1表示手动修改） */
            Integer orderPriceType=1;

            /**判断是不是需要实时计算成本 0则表明需要实时计算*/
            if (orderScheduleRes.getCost().compareTo(new BigDecimal(0)) == 0) {
                orderPriceType=0;
                //自营服务师
                if (orderScheduleRes.getSupplierId() == 1) {
                    JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(orderScheduleRes.getNannyId()));
                    JDictInfo jDictInfo = jDictInfoMapper.selectById(Long.valueOf(jNannyInfo.getNannyLevel()));
                    currentUnitPrice=new BigDecimal(jDictInfo.getInfo());
                    currentJobLevel=jNannyInfo.getNannyLevel();
                    /**根据服务师星级小时工资与日程工作时长计算成本*/
                    orderScheduleRes.setCost(new BigDecimal(jDictInfo.getInfo()).multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                }
                //带教
                else if (orderScheduleRes.getSupplierId() == -1) {
                    JTeacher jTeacher = jTeacherMapper.selectById(Long.valueOf(orderScheduleRes.getNannyId()));
                    JDictInfo jDictInfo = jDictInfoMapper.selectById(Long.valueOf(jTeacher.getTeacherLevel()));
                    currentUnitPrice=new BigDecimal(jDictInfo.getInfo());
                    currentJobLevel=jTeacher.getTeacherLevel();
                    /**根据带教星级小时工资与日程工作时长计算成本*/
                    orderScheduleRes.setCost(new BigDecimal(jDictInfo.getInfo()).multiply(new BigDecimal(String.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f))).setScale(2));
                }
                else {
                    Map map = new HashMap();
                    map.put("goodsId", jOrderTemp.getGoodsId());
                    map.put("supplierId", orderScheduleRes.getSupplierId());
                    List<JGoodsCost> jGoodsCostList = jGoodsCostMapper.selectByMap(map);
                    /**根据供应商单位成本与订单产品的数量（平方米/个数）计算成本*/
                    orderScheduleRes.setCost(jGoodsCostList.get(0).getCost().multiply(new BigDecimal(jOrderTemp.getServiceAmount())).setScale(2));
                }
            }

            /**服务时间*/
            String serviceTime = orderScheduleRes.getStartTimeValue() + "-" + orderScheduleRes.getEndTimeValue();
            Double serviceTimeLength = Double.valueOf((orderScheduleRes.getEndTime() - orderScheduleRes.getStartTime()) / 2f);
            /**判断日程是自营还是供应商提供 1为自营*/
            if (orderScheduleRes.getSupplierId() == 1) {
                JNannyStatment jNannyStatment = new JNannyStatment(OrderUtil.generateStamentNumber(orderScheduleRes.getNannyId()),
                        orderScheduleRes.getNannyId(),
                        orderScheduleRes.getScheduleId(), orderScheduleRes.getOrderId(),
                        jOrderTemp.getShopId(), jOrderTemp.getHouseId(), jOrderTemp.getCustomerId(),
                        166, orderScheduleRes.getCost(), 164, jOrderTemp.getGoodsId(), serviceTime, serviceTimeLength,
                        orderScheduleRes.getScheduleDate(), "",jAdmin.getAdminId(),currentJobLevel,currentUnitPrice,orderPriceType);
                jNannyStatmentMapper.insert(jNannyStatment);
                jNannyStatmentList.add(jNannyStatment);

            }
            else if (orderScheduleRes.getSupplierId() == -1){
                JTeacherStatment jTeacherStatment = new JTeacherStatment(OrderUtil.generateStamentNumber(orderScheduleRes.getNannyId()),
                        orderScheduleRes.getNannyId(),
                        orderScheduleRes.getScheduleId(), orderScheduleRes.getOrderId(),
                        jOrderTemp.getShopId(), jOrderTemp.getHouseId(), jOrderTemp.getCustomerId(),
                        166, orderScheduleRes.getCost(), 164, jOrderTemp.getGoodsId(), serviceTime, serviceTimeLength,
                        orderScheduleRes.getScheduleDate(), "",jAdmin.getAdminId(),currentJobLevel,currentUnitPrice,orderPriceType);
                jTeacherStatmentMapper.insert(jTeacherStatment);
                jTeacherStatmentList.add(jTeacherStatment);
            }
            else {
                Map map = new HashMap();
                map.put("goodsId", jOrderTemp.getGoodsId());
                map.put("supplierId", orderScheduleRes.getSupplierId());
                List<JGoodsCost> jGoodsCostList = jGoodsCostMapper.selectByMap(map);
                JSupplierStatment jSupplierStatment = new JSupplierStatment(OrderUtil.generateStamentNumber(orderScheduleRes.getNannyId()),
                        orderScheduleRes.getSupplierId(), orderScheduleRes.getScheduleId(), orderScheduleRes.getOrderId(),
                        jOrderTemp.getShopId(), jOrderTemp.getHouseId(), jOrderTemp.getCustomerId(), 174, orderScheduleRes.getCost(),
                        jGoodsCostList.get(0).getCost().multiply(new BigDecimal(jOrderTemp.getServiceAmount())).setScale(2),
                        164, jOrderTemp.getGoodsId(), serviceTime, serviceTimeLength, orderScheduleRes.getScheduleDate(), new Date(), "");
                jSupplierStatmentMapper.insert(jSupplierStatment);
                jSupplierStatmentList.add(jSupplierStatment);
            }
        }

        return new ModelRes(ModelRes.Status.SUCCESS, "", null);
    }
}
