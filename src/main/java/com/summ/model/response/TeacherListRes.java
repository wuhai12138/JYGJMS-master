package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 17/12/13.
 */
public class TeacherListRes {
    private Integer teacherId;
    String teacherName;
    private String teacherPhone;
    private Date createTime;
    private Integer teacherStatus;
    private String teacherStatusInfo;
    private Integer teacherAge;
    private Integer hot;
    private String hotInfo;
    private String remark;

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getHotInfo() {
        return hotInfo;
    }

    public void setHotInfo(String hotInfo) {
        this.hotInfo = hotInfo;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(Integer teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public String getTeacherStatusInfo() {
        return teacherStatusInfo;
    }

    public void setTeacherStatusInfo(String teacherStatusInfo) {
        this.teacherStatusInfo = teacherStatusInfo;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
