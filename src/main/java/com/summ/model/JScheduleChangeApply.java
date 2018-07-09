package com.summ.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-06-15
 */
@TableName("j_schedule_change_apply")
public class JScheduleChangeApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日程变更申请表
     */
    @TableId(value = "applyId", type = IdType.AUTO)
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
    private Date createDate;
    @TableField("modifyDate")
    private Date modifyDate;
    @TableField("isDel")
    private Integer isDel;
    private Integer customerId;


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
