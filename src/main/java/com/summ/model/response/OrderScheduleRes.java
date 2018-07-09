package com.summ.model.response;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 18/1/15.
 */
public class OrderScheduleRes {
    /**  */
    @TableId(type = IdType.AUTO)
    private Integer scheduleId;

    private Integer customerId;
    /**  */
    private Integer orderId;

    /** 对账单 */
    private Integer statmentId;

    private Date scheduleDate;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 总价 */
    private BigDecimal totalPrice;

    /** 成本（取决于服务师） */
    private BigDecimal cost;

    /** 日程状态 */
    private Integer scheduleStatus;

    private Double clockLatitude;
    private Double clockLongitude;
    private String clockDistance;

    private Integer scheduleType;

    /** 支付状态 */
    private Integer payStatus;

    /** 开始时间Id */
    private Integer startTime;

    /** 结束时间Id */
    private Integer endTime;

    private String startTimeValue;

    private String endTimeValue;

    /** 时间值 */
    private Long timeValue;

    /** 签到人id */
    private Integer clockId;

    /** 签到时间 */
    private Date clockTime;

    /** 完工操作人id */
    private Integer completedId;

    /** 完工时间 */
    private Date completedTime;

    /** 暂停时间 */
    private Date suspendTime;

    /** 取消操作人id */
    private Integer cancelId;

    /** 取消时间  */
    private Date cancelTime;

    private String weekday;

    /**  */
    private String remark;

    private String scheduleStatusInfo;
    private String scheduleTypeInfo;
    private String payStatusInfo;
    private Integer nannyId;
    private Integer supplierId;
    private String nannyName;
    private String nannyPhone;
    private Integer nannyLevel;

    /**日程当前单价*/
    private BigDecimal scheduleCurrentPrice;
    /**服务师当前小时薪资*/
    private String nannyCurrentPayment;

    @Override
    public String toString() {
        return "OrderScheduleRes{" +
                "scheduleId=" + scheduleId +
                ", orderId=" + orderId +
                ", statmentId=" + statmentId +
                ", scheduleDate=" + scheduleDate +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", cost=" + cost +
                ", scheduleStatus=" + scheduleStatus +
                ", payStatus=" + payStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startTimeValue='" + startTimeValue + '\'' +
                ", endTimeValue='" + endTimeValue + '\'' +
                ", timeValue=" + timeValue +
                ", clockTime=" + clockTime +
                ", completedTime=" + completedTime +
                ", suspendTime=" + suspendTime +
                ", cancelTime=" + cancelTime +
                ", weekday='" + weekday + '\'' +
                ", scheduleStatusInfo='" + scheduleStatusInfo + '\'' +
                ", payStatusInfo='" + payStatusInfo + '\'' +
                ", nannyId=" + nannyId +
                ", nannyName='" + nannyName + '\'' +
                ", nannyPhone='" + nannyPhone + '\'' +
                ", scheduleCurrentPrice=" + scheduleCurrentPrice +
                ", nannyCurrentPayment='" + nannyCurrentPayment + '\'' +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getNannyLevel() {
        return nannyLevel;
    }

    public void setNannyLevel(Integer nannyLevel) {
        this.nannyLevel = nannyLevel;
    }

    public String getClockDistance() {
        return clockDistance;
    }

    public void setClockDistance(String clockDistance) {
        this.clockDistance = clockDistance;
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

    public String getScheduleTypeInfo() {
        return scheduleTypeInfo;
    }

    public void setScheduleTypeInfo(String scheduleTypeInfo) {
        this.scheduleTypeInfo = scheduleTypeInfo;
    }

    public Integer getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(Integer scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Integer getClockId() {
        return clockId;
    }

    public void setClockId(Integer clockId) {
        this.clockId = clockId;
    }

    public Integer getCompletedId() {
        return completedId;
    }

    public void setCompletedId(Integer completedId) {
        this.completedId = completedId;
    }

    public Integer getCancelId() {
        return cancelId;
    }

    public void setCancelId(Integer cancelId) {
        this.cancelId = cancelId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getScheduleCurrentPrice() {
        return scheduleCurrentPrice;
    }

    public void setScheduleCurrentPrice(BigDecimal scheduleCurrentPrice) {
        this.scheduleCurrentPrice = scheduleCurrentPrice;
    }

    public String getNannyCurrentPayment() {
        return nannyCurrentPayment;
    }

    public void setNannyCurrentPayment(String nannyCurrentPayment) {
        this.nannyCurrentPayment = nannyCurrentPayment;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatmentId() {
        return statmentId;
    }

    public void setStatmentId(Integer statmentId) {
        this.statmentId = statmentId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getStartTimeValue() {
        return startTimeValue;
    }

    public void setStartTimeValue(String startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public String getEndTimeValue() {
        return endTimeValue;
    }

    public void setEndTimeValue(String endTimeValue) {
        this.endTimeValue = endTimeValue;
    }

    public Long getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(Long timeValue) {
        this.timeValue = timeValue;
    }

    public Date getClockTime() {
        return clockTime;
    }

    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }

    public Date getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public Date getSuspendTime() {
        return suspendTime;
    }

    public void setSuspendTime(Date suspendTime) {
        this.suspendTime = suspendTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getScheduleStatusInfo() {
        return scheduleStatusInfo;
    }

    public void setScheduleStatusInfo(String scheduleStatusInfo) {
        this.scheduleStatusInfo = scheduleStatusInfo;
    }

    public String getPayStatusInfo() {
        return payStatusInfo;
    }

    public void setPayStatusInfo(String payStatusInfo) {
        this.payStatusInfo = payStatusInfo;
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
}
