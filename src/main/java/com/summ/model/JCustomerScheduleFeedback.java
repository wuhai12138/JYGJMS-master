package com.summ.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-06-12
 */
@TableName("j_customer_schedule_feedback")
public class JCustomerScheduleFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日程反馈记录
     */
    @TableId(value = "scheduleFeedbackId", type = IdType.AUTO)
    private Integer scheduleFeedbackId;
    @TableField("scheduleId")
    private Integer scheduleId;
    private String phote;
    @TableField("createDate")
    private Date createDate;
    @TableField("customerId")
    private Integer customerId;
    @TableField("nannyId")
    private Integer nannyId;
    @TableField("isDel")
    @TableLogic
    private Integer isDel;
    private String remark;


    public Integer getScheduleFeedbackId() {
        return scheduleFeedbackId;
    }

    public void setScheduleFeedbackId(Integer scheduleFeedbackId) {
        this.scheduleFeedbackId = scheduleFeedbackId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPhote() {
        return phote;
    }

    public void setPhote(String phote) {
        this.phote = phote;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JCustomerScheduleFeedback{" +
        "scheduleFeedbackId=" + scheduleFeedbackId +
        ", scheduleId=" + scheduleId +
        ", phote=" + phote +
        ", createDate=" + createDate +
        ", customerId=" + customerId +
        ", nannyId=" + nannyId +
        ", isDel=" + isDel +
        ", remark=" + remark +
        "}";
    }
}
