package com.summ.model.request;

import java.math.BigDecimal;
import java.util.List;

public class SchedulePriceReq {
    private List<Long> scheduleIdList;
    private BigDecimal totalPrice;
    private String remark;

    public List<Long> getScheduleIdList() {
        return scheduleIdList;
    }

    public void setScheduleIdList(List<Long> scheduleIdList) {
        this.scheduleIdList = scheduleIdList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
