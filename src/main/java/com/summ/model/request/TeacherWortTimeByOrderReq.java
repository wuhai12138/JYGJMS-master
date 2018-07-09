package com.summ.model.request;

import java.util.Date;

/**
 * Created by summ on 18/1/24.
 */
public class TeacherWortTimeByOrderReq {
    /**操作类型1表示工时，2表示配单*/
    private Integer operationType;
    private Integer teacherId;
    private Date startDate;
    private Date endDate;
    /**订单类型，包年订单165则需判断服务师中介费*/
    private Integer orderType;

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
