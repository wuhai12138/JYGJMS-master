package com.summ.controller.order;


import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JOrderSchedule;
import com.summ.model.JScheduleChangeApply;
import com.summ.model.request.ScheduleChangeApplyReq;
import com.summ.model.request.ServiceWeeksTimeReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.DateUtil;
import com.summ.utils.JsonUtil;
import com.summ.utils.NannyWorkTimeUtil;
import com.summ.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *"日程调单模块")
 *
 */
@RestController
@RequestMapping(value = "/order/schedule", method = RequestMethod.POST)
public class ScheduleChangeApplyController extends AutoMapperController {


    /**
     * 查找历史调单申请
     * @param
     * @return
     */
    @RequestMapping(value = "/change/find")
    public Object find(@RequestBody ScheduleChangeApplyReq scheduleChangeApplyReq) {
        try {
            scheduleChangeApplyReq.setPage((scheduleChangeApplyReq.getPage()-1)*scheduleChangeApplyReq.getSize());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", ResponseUtil.List2Map(jScheduleChangeApplyMapper.getChangeList(scheduleChangeApplyReq),jScheduleChangeApplyMapper.getChangeListCount(scheduleChangeApplyReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }

    /**
     * 拒绝调单申请
     * @param jScheduleChangeApply
     * @return
     */
    @RequestMapping(value = "/change/refuse")
    public Object refuse(@RequestBody JScheduleChangeApply jScheduleChangeApply) {
        try {
            jScheduleChangeApply.setApplyStatus(292);
            jScheduleChangeApply.setModifyDate(new Date());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jScheduleChangeApplyMapper.updateById(jScheduleChangeApply));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }

    /**
     * 验证调单申请
     * @param jScheduleChangeApply1
     * @return
     */
    @RequestMapping(value = "/change/sign")
    public Object sign(@RequestBody JScheduleChangeApply jScheduleChangeApply1) {
        try {
            JScheduleChangeApply jScheduleChangeApply = jScheduleChangeApplyMapper.selectById(jScheduleChangeApply1.getApplyId());
            //验证订单时间和服务师时间是否冲突
            ServiceWeeksTimeReq serviceWeeksTimeReq = new ServiceWeeksTimeReq();
            serviceWeeksTimeReq.setNannyId(jScheduleChangeApply.getNannyId());
            serviceWeeksTimeReq.setWeekday(DateUtil.dateAndday(jScheduleChangeApply.getScheduleDate()));
            serviceWeeksTimeReq.setStartId(jScheduleChangeApply.getStartTime());
            serviceWeeksTimeReq.setEndId(jScheduleChangeApply.getEndTime());
            List<String> weekList = new ArrayList<String>();
            serviceWeeksTimeReq.setWeekList(weekList);
            serviceWeeksTimeReq.setStartDate(jScheduleChangeApply.getScheduleDate());
            serviceWeeksTimeReq.setEndDate(new Date(jScheduleChangeApply.getScheduleDate().getTime() + (24 * 3600 * 1000) - 1));
            serviceWeeksTimeReq.setScheduleId(jScheduleChangeApply.getScheduleId());

            /**换算开始结束时间*/
            Map map1 = JsonUtil.Obj2Map(serviceWeeksTimeReq);
            map1.put("startDate", new Date((Long) map1.get("startDate")));
            map1.put("endDate", new Date((Long) map1.get("endDate")));

            //判断该服务师的日程是否符合订单时间要求
            List<JOrderSchedule> signNannySchedule = jOrderScheduleMapper.signNannySchedule(map1);
            if (signNannySchedule.size() > 0) {
                return new ModelRes(ModelRes.Status.FAILED, "服务师日程占用 ! !", ResponseUtil.List2Map(signNannySchedule));
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "服务师时间空闲", jScheduleChangeApply);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }

    /**
     * 调单
     * @param jScheduleChangeApply1
     * @return
     */
    @RequestMapping(value = "/change/confirm")
    public Object confirm(@RequestBody JScheduleChangeApply jScheduleChangeApply1) {
        try {
            JScheduleChangeApply jScheduleChangeApply = jScheduleChangeApplyMapper.selectById(jScheduleChangeApply1.getApplyId());

            jScheduleChangeApply.setApplyStatus(293);
            jScheduleChangeApply.setModifyDate(new Date());

            JOrderSchedule jOrderSchedule = new JOrderSchedule();
            jOrderSchedule.setScheduleId(jScheduleChangeApply.getScheduleId());
            switch (jScheduleChangeApply.getApplyType()) {
                /**更改订单时间*/
                case 294:
                    jOrderSchedule.setScheduleStatus(152);
                    jOrderSchedule.setScheduleDate(jScheduleChangeApply.getScheduleDate());
                    jOrderSchedule.setStartTime(jScheduleChangeApply.getStartTime());
                    jOrderSchedule.setEndTime(jScheduleChangeApply.getEndTime());
                    jOrderSchedule.setStartTimeValue(NannyWorkTimeUtil.id2Time(jScheduleChangeApply.getStartTime()));
                    jOrderSchedule.setEndTimeValue(NannyWorkTimeUtil.id2Time(jScheduleChangeApply.getEndTime()));
                    jOrderSchedule.setTimeValue(NannyWorkTimeUtil.getTimeListValue(jScheduleChangeApply.getStartTime(), jScheduleChangeApply.getEndTime()));
                    jOrderSchedule.setWeekday(DateUtil.dateAndday(jScheduleChangeApply.getScheduleDate()));

                    jScheduleChangeApplyMapper.updateById(jScheduleChangeApply);
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jScheduleChangeApply);
                default:
                    jOrderSchedule.setScheduleStatus(155);
                    jScheduleChangeApplyMapper.updateById(jScheduleChangeApply);
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jScheduleChangeApply);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "err", null);
        }
    }
}
