package com.summ.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class ScheduleRes {
    private Integer scheduleId;

    /**  */
    private Integer orderId;

    private Integer orderType;

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
    private String clockName;

    /** 签到时间 */
    private Date clockTime;

    private Double clockLatitude;
    private Double clockLongitude;

    /** 完工操作人id */
    private Integer completedId;

    /** 完工时间 */
    private Date completedTime;

    /** 暂停时间 */
    private Date suspendTime;

    /** 取消操作人id */
    private Integer cancelId;
    private String cancelName;

    /** 取消时间  */
    private Date cancelTime;

    private String weekday;

    private Integer goodsId;
    /** 服务数量（平方米，个等等） */
    private Integer serviceAmount;

    private String scheduleStatusInfo;
    private String scheduleTypeInfo;
    private String payStatusInfo;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private String houseAddress;
    private Integer nannyId;
    private Integer supplierId;
    private String nannyName;
    private String nannyPhone;
    private Integer shopId;
    private String shopName;
    private String service;

    /** 评价 */
    private String assess;
    /** 评星 */
    private Integer staring;
    /**评价日期*/
    private Date commentDate;

    /**日程当前单价*/
    private BigDecimal scheduleCurrentPrice;
    /**服务师当前小时薪资*/
    private String nannyCurrentPayment;

    public String getAssess() {
        return assess;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }

    public Integer getStaring() {
        return staring;
    }

    public void setStaring(Integer staring) {
        this.staring = staring;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
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

    public Integer getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(Integer scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleTypeInfo() {
        return scheduleTypeInfo;
    }

    public void setScheduleTypeInfo(String scheduleTypeInfo) {
        this.scheduleTypeInfo = scheduleTypeInfo;
    }

    public String getClockName() {
        return clockName;
    }

    public void setClockName(String clockName) {
        this.clockName = clockName;
    }

    public String getCancelName() {
        return cancelName;
    }

    public void setCancelName(String cancelName) {
        this.cancelName = cancelName;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Integer serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
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
}
