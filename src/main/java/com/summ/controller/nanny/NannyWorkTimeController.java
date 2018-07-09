package com.summ.controller.nanny;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.NannyWortTimeByOrderReq;
import com.summ.model.request.ServiceWeeksTimeReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.NannyHotWorkTimeRes;
import com.summ.model.response.TimeAndWeekRes;
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
import java.util.*;

/**
 * 服务师工时信息
 * @author summ
 * @date 18/1/11
 */
@Controller
@RequestMapping("/nanny/worktime")
public class NannyWorkTimeController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/find")
    public Object getHotNannyByOrder(@RequestBody NannyWortTimeByOrderReq nannyWortTimeByOrderReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            nannyWortTimeByOrderReq.setAdminId(jAdmin.getAdminId());

            //获取常用服务师基本信息列表
            List<NannyHotWorkTimeRes> nannyHotWorkTimeResList = jNannyWorkTimeMapper.getHotNannyWorkTime(nannyWortTimeByOrderReq);
            //循环每个常用服务师
            for (NannyHotWorkTimeRes nannyHotWorkTimeRes : nannyHotWorkTimeResList) {
                //保存服务师工时
                List<TimeAndWeekRes> timeAndWeekResList = new ArrayList<TimeAndWeekRes>();

                List<TimeAndWeekRes> timeAndWeekResListMonday = NannyWorkTimeUtil.value2TimeAndWeekRes(nannyHotWorkTimeRes.getMonday());
                for (TimeAndWeekRes timeAndWeekRes : timeAndWeekResListMonday) {
                    timeAndWeekRes.setStart(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getStartId()));
                    timeAndWeekRes.setEnd(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getEndId()));
                    timeAndWeekRes.setWeek("周一");
                }
                timeAndWeekResList.addAll(timeAndWeekResListMonday);

                List<TimeAndWeekRes> timeAndWeekResListTuesday = NannyWorkTimeUtil.value2TimeAndWeekRes(nannyHotWorkTimeRes.getTuesday());
                for (TimeAndWeekRes timeAndWeekRes : timeAndWeekResListTuesday) {
                    timeAndWeekRes.setStart(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getStartId()));
                    timeAndWeekRes.setEnd(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getEndId()));
                    timeAndWeekRes.setWeek("周二");

                }
                timeAndWeekResList.addAll(timeAndWeekResListTuesday);

                List<TimeAndWeekRes> timeAndWeekResListWednesday = NannyWorkTimeUtil.value2TimeAndWeekRes(nannyHotWorkTimeRes.getWednesday());
                for (TimeAndWeekRes timeAndWeekRes : timeAndWeekResListWednesday) {
                    timeAndWeekRes.setStart(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getStartId()));
                    timeAndWeekRes.setEnd(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getEndId()));
                    timeAndWeekRes.setWeek("周三");
                }
                timeAndWeekResList.addAll(timeAndWeekResListWednesday);

                List<TimeAndWeekRes> timeAndWeekResListThursday = NannyWorkTimeUtil.value2TimeAndWeekRes(nannyHotWorkTimeRes.getThursday());
                for (TimeAndWeekRes timeAndWeekRes : timeAndWeekResListThursday) {
                    timeAndWeekRes.setStart(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getStartId()));
                    timeAndWeekRes.setEnd(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getEndId()));
                    timeAndWeekRes.setWeek("周四");
                }
                timeAndWeekResList.addAll(timeAndWeekResListThursday);

                List<TimeAndWeekRes> timeAndWeekResListFriday = NannyWorkTimeUtil.value2TimeAndWeekRes(nannyHotWorkTimeRes.getFriday());
                for (TimeAndWeekRes timeAndWeekRes : timeAndWeekResListFriday) {
                    timeAndWeekRes.setStart(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getStartId()));
                    timeAndWeekRes.setEnd(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getEndId()));
                    timeAndWeekRes.setWeek("周五");
                }
                timeAndWeekResList.addAll(timeAndWeekResListFriday);

                List<TimeAndWeekRes> timeAndWeekResListSaturday = NannyWorkTimeUtil.value2TimeAndWeekRes(nannyHotWorkTimeRes.getSaturday());
                for (TimeAndWeekRes timeAndWeekRes : timeAndWeekResListSaturday) {
                    timeAndWeekRes.setStart(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getStartId()));
                    timeAndWeekRes.setEnd(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getEndId()));
                    timeAndWeekRes.setWeek("周六");
                }
                timeAndWeekResList.addAll(timeAndWeekResListSaturday);

                List<TimeAndWeekRes> timeAndWeekResListSunday = NannyWorkTimeUtil.value2TimeAndWeekRes(nannyHotWorkTimeRes.getSunday());
                for (TimeAndWeekRes timeAndWeekRes : timeAndWeekResListSunday) {
                    timeAndWeekRes.setStart(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getStartId()));
                    timeAndWeekRes.setEnd(NannyWorkTimeUtil.id2Time(timeAndWeekRes.getEndId()));
                    timeAndWeekRes.setWeek("周天");
                }
                timeAndWeekResList.addAll(timeAndWeekResListSunday);

                nannyHotWorkTimeRes.setNannyWorkTime(timeAndWeekResList);


                //保存服务师日程已使用工时
                nannyWortTimeByOrderReq.setNannyId(nannyHotWorkTimeRes.getNannyId());
                List<TimeAndWeekRes> scheduleTimeAndWeekResList = jNannyWorkTimeMapper.getHotNannyScheduleTime(nannyWortTimeByOrderReq);
                nannyHotWorkTimeRes.setNannyScheduleTime(scheduleTimeAndWeekResList);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(nannyHotWorkTimeResList));
        } catch (Exception e) {
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }

    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insertWorktimeByNanny(@RequestBody Map map) {
        try {

            String stringWeekList = (String) map.get("weekList");
            List<Map> weekList = JsonUtil.json2List(stringWeekList, Map.class);
            String stringTimeList = (String) map.get("timeList");
            List<Map> timeList = JsonUtil.json2List(stringTimeList, Map.class);
            Integer nannyId = (Integer) map.get("nannyId");

            Map hMap = new HashMap();
            hMap.put("nannyId",nannyId);
            List<JNannyWorkTime> jNannyWorkTimeList = jNannyWorkTimeMapper.selectByMap(hMap);
            if (jNannyWorkTimeList.size()>0){
                /**手动清空老工时*/
                jNannyWorkTimeMapper.deleteById(jNannyWorkTimeList.get(0));
//                return new ModelRes(ModelRes.Status.FAILED, "该服务师已有工时，如需添加请先清空！");
            }
            Long workTime = 0L;
            for (int i = 0; i < timeList.size(); i++) {
                workTime += NannyWorkTimeUtil.getTimeListValue((Integer) timeList.get(i).get("startId"), (Integer) timeList.get(i).get("endId"));
            }
            JNannyWorkTime jNannyWorkTime = new JNannyWorkTime();
            jNannyWorkTime.setNannyId(nannyId);
            for (int i = 0; i < weekList.size(); i++) {
                if ("true".equals(weekList.get(i).get("active").toString())) {
                    if ("周一".equals(weekList.get(i).get("value").toString())) {
                        jNannyWorkTime.setMonday(workTime);
                    }
                    if ("周二".equals(weekList.get(i).get("value").toString())) {
                        jNannyWorkTime.setTuesday(workTime);
                    }
                    if ("周三".equals(weekList.get(i).get("value").toString())) {
                        jNannyWorkTime.setWednesday(workTime);
                    }
                    if ("周四".equals(weekList.get(i).get("value").toString())) {
                        jNannyWorkTime.setThursday(workTime);
                    }
                    if ("周五".equals(weekList.get(i).get("value").toString())) {
                        jNannyWorkTime.setFriday(workTime);
                    }
                    if ("周六".equals(weekList.get(i).get("value").toString())) {
                        jNannyWorkTime.setSaturday(workTime);
                    }
                    if ("周天".equals(weekList.get(i).get("value").toString())) {
                        jNannyWorkTime.setSunday(workTime);
                    }
                }
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyWorkTimeMapper.insert(jNannyWorkTime));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody Map map) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyWorkTimeMapper.deleteByMap(map));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
