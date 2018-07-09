package com.summ.model.response;

import java.util.List;

/**
 * Created by summ on 18/1/25.
 */
public class TeacherWorkTimeRes {
    private Integer teacherId;
    private String teacherName;
    private String teacherPhone;
    private List<TimeAndWeekRes> teacherWorkTime;
    private List<TimeAndWeekRes> teacherScheduleTime;

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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public List<TimeAndWeekRes> getTeacherWorkTime() {
        return teacherWorkTime;
    }

    public void setTeacherWorkTime(List<TimeAndWeekRes> teacherWorkTime) {
        this.teacherWorkTime = teacherWorkTime;
    }

    public List<TimeAndWeekRes> getTeacherScheduleTime() {
        return teacherScheduleTime;
    }

    public void setTeacherScheduleTime(List<TimeAndWeekRes> teacherScheduleTime) {
        this.teacherScheduleTime = teacherScheduleTime;
    }
}
