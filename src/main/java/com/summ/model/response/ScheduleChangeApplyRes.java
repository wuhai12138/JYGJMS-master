package com.summ.model.response;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-06-15
 */
public class ScheduleChangeApplyRes implements Serializable {


    /**
     * 日程变更申请表
     */
    private Integer applyId;
    @TableField("scheduleId")
    private Integer scheduleId;
    @TableField("nannyId")
    private Integer nannyId;
    @TableField("applyStatus")
    private Integer applyStatus;
    @TableField("applyType")
    private Integer applyType;
    @TableField("scheduleDate")
    private Date scheduleDate;
    @TableField("startTime")
    private Integer startTime;
    @TableField("endTime")
    private Integer endTime;
    @TableField("customerReason")
    private String customerReason;
    @TableField("nannyReason")
    private String nannyReason;
    @TableField("createDate")
    private Date createDate=new Date();
    @TableField("modifyDate")
    private Date modifyDate;
    @TableField("isDel")
    private Integer isDel;
    private Integer customerId;

    private String applyStatusInfo;
    private String applyTypeInfo;

    private String customerName;
    private String customerPhone;
    private String service;
    private String orStartTimeValue;
    private String orEndTimeValue;
    private String nannyName;
    private String nannyPhone;


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

    public String getStartTimeValue() {
        return startTimeValue;
    }

    public void setStartTimeValue(String startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    private String startTimeValue;

    public String getOrStartTimeValue() {
        return orStartTimeValue;
    }

    public void setOrStartTimeValue(String orStartTimeValue) {
        this.orStartTimeValue = orStartTimeValue;
    }

    public String getOrEndTimeValue() {
        return orEndTimeValue;
    }

    public void setOrEndTimeValue(String orEndTimeValue) {
        this.orEndTimeValue = orEndTimeValue;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    private String houseAddress;
    private Date orScheduleDate;
    private Integer orStartTime;
    private Integer orEndTime;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public Date getOrScheduleDate() {
        return orScheduleDate;
    }

    public void setOrScheduleDate(Date orScheduleDate) {
        this.orScheduleDate = orScheduleDate;
    }

    public Integer getOrStartTime() {
        return orStartTime;
    }

    public void setOrStartTime(Integer orStartTime) {
        this.orStartTime = orStartTime;
    }

    public Integer getOrEndTime() {
        return orEndTime;
    }

    public void setOrEndTime(Integer orEndTime) {
        this.orEndTime = orEndTime;
    }

    public String getApplyStatusInfo() {
        return applyStatusInfo;
    }

    public void setApplyStatusInfo(String applyStatusInfo) {
        this.applyStatusInfo = applyStatusInfo;
    }

    public String getApplyTypeInfo() {
        return applyTypeInfo;
    }

    public void setApplyTypeInfo(String applyTypeInfo) {
        this.applyTypeInfo = applyTypeInfo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
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

    public String getCustomerReason() {
        return customerReason;
    }

    public void setCustomerReason(String customerReason) {
        this.customerReason = customerReason;
    }

    public String getNannyReason() {
        return nannyReason;
    }

    public void setNannyReason(String nannyReason) {
        this.nannyReason = nannyReason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JScheduleChangeApply{" +
        "applyId=" + applyId +
        ", scheduleId=" + scheduleId +
        ", nannyId=" + nannyId +
        ", applyStatus=" + applyStatus +
        ", applyType=" + applyType +
        ", scheduleDate=" + scheduleDate +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", customerReason=" + customerReason +
        ", nannyReason=" + nannyReason +
        ", createDate=" + createDate +
        ", modifyDate=" + modifyDate +
        ", isDel=" + isDel +
        "}";
    }
}
