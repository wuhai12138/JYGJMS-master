package com.summ.model.response;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;
import java.util.List;

/**
 * Created by summ on 18/1/28.
 */
public class CustomerFeedbackRes {

    private Integer messageId;

    /**  */
    private String content;

    /** 客户 */
    private Integer customerId;

    private Integer orderId;
    private Integer scheduleId;

    /**  */
    private Date createDate;

    /** 状态 */
    private Integer messageStatus;

    /** 消息类型 */
    private Integer messageType;

    /** 记录人id */
    private Integer noteAdmin;

    /**  */
    private Integer isDel;

    private String customerName;
    private String customerPhone;
    private String messageStatusInfo;
    private String department;
    List<CustomerFeedbackDepartmentRes> customerFeedbackDepartmentResList;
    private List<CustomerFeedbackFollowRes> customerFeedbackFollowResList;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageStatusInfo() {
        return messageStatusInfo;
    }

    public void setMessageStatusInfo(String messageStatusInfo) {
        this.messageStatusInfo = messageStatusInfo;
    }

    public List<CustomerFeedbackDepartmentRes> getCustomerFeedbackDepartmentResList() {
        return customerFeedbackDepartmentResList;
    }

    public void setCustomerFeedbackDepartmentResList(List<CustomerFeedbackDepartmentRes> customerFeedbackDepartmentResList) {
        this.customerFeedbackDepartmentResList = customerFeedbackDepartmentResList;
    }

    public List<CustomerFeedbackFollowRes> getCustomerFeedbackFollowResList() {
        return customerFeedbackFollowResList;
    }

    public void setCustomerFeedbackFollowResList(List<CustomerFeedbackFollowRes> customerFeedbackFollowResList) {
        this.customerFeedbackFollowResList = customerFeedbackFollowResList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Integer getNoteAdmin() {
        return noteAdmin;
    }

    public void setNoteAdmin(Integer noteAdmin) {
        this.noteAdmin = noteAdmin;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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
}
