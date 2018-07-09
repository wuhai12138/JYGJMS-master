package com.summ.model.response;

/**
 * Created by summ on 18/1/25.
 */
public class TimeAndWeekRes {
    private String week;
    private String start;
    private String end;
    private Integer startId;
    private Integer endId;
    private String describe = "可上工";

    @Override
    public String toString() {
        return "TimeAndWeekRes{" +
                "week='" + week + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", startId=" + startId +
                ", endId=" + endId +
                ", describe='" + describe + '\'' +
                '}';
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
