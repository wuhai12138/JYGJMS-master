package com.summ.model.response;

import java.util.List;

/**
 * Created by summ on 18/1/25.
 */
public class NannyHotWorkTimeRes {
    private Integer nannyId;
    private String nannyName;
    private String nannyPhone;
    private List<TimeAndWeekRes> nannyWorkTime;
    private List<TimeAndWeekRes> nannyScheduleTime;

    /**  */
    private Long monday;

    /**  */
    private Long tuesday;

    /**  */
    private Long wednesday;

    /**  */
    private Long thursday;

    /**  */
    private Long friday;

    /**  */
    private Long saturday;

    /**  */
    private Long sunday;


    public Long getMonday() {
        return monday;
    }

    public void setMonday(Long monday) {
        this.monday = monday;
    }

    public Long getTuesday() {
        return tuesday;
    }

    public void setTuesday(Long tuesday) {
        this.tuesday = tuesday;
    }

    public Long getWednesday() {
        return wednesday;
    }

    public void setWednesday(Long wednesday) {
        this.wednesday = wednesday;
    }

    public Long getThursday() {
        return thursday;
    }

    public void setThursday(Long thursday) {
        this.thursday = thursday;
    }

    public Long getFriday() {
        return friday;
    }

    public void setFriday(Long friday) {
        this.friday = friday;
    }

    public Long getSaturday() {
        return saturday;
    }

    public void setSaturday(Long saturday) {
        this.saturday = saturday;
    }

    public Long getSunday() {
        return sunday;
    }

    public void setSunday(Long sunday) {
        this.sunday = sunday;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public String getNannyPhone() {
        return nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
    }

    public List<TimeAndWeekRes> getNannyWorkTime() {
        return nannyWorkTime;
    }

    public void setNannyWorkTime(List<TimeAndWeekRes> nannyWorkTime) {
        this.nannyWorkTime = nannyWorkTime;
    }

    public List<TimeAndWeekRes> getNannyScheduleTime() {
        return nannyScheduleTime;
    }

    public void setNannyScheduleTime(List<TimeAndWeekRes> nannyScheduleTime) {
        this.nannyScheduleTime = nannyScheduleTime;
    }
}
