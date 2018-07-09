package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 18/1/29.
 */
public class CustomerFeedbackFollowRes {
    private Integer followId;

    /**  */
    private Integer feedbackId;

    /**  */
    private Date createDate;

    /**  */
    private String remark;

    /**  */
    private Integer adminId;

    private Integer followStatus;

    /**  */
    private Integer isDel;

    private String adminName;
    private String followStatusInfo;

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getFollowStatusInfo() {
        return followStatusInfo;
    }

    public void setFollowStatusInfo(String followStatusInfo) {
        this.followStatusInfo = followStatusInfo;
    }
}
