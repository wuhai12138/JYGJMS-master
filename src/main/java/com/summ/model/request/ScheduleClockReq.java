package com.summ.model.request;

import java.util.List;

public class ScheduleClockReq {
    private List<Integer> list;
    private Double clockLatitude;
    private Double clockLongitude;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Double getClockLatitude() {
        return clockLatitude;
    }

    public void setClockLatitude(Double clockLatitude) {
        this.clockLatitude = clockLatitude;
    }

    public Double getClockLongitude() {
        return clockLongitude;
    }

    public void setClockLongitude(Double clockLongitude) {
        this.clockLongitude = clockLongitude;
    }
}
