package com.summ.model.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by summ on 17/12/13.
 */
public class TeacherRes {
    private Integer teacherId;

    /** 头像 */
    private String teacherAvatar;

    /** 服务师姓名 */
    private String teacherName;

    /**  */
    private Integer teacherSex;
    private String teacherSexInfo;

    /**  */
    private Integer teacherStatus;
    private String teacherStatusInfo;

    /**  */
    private Integer teacherType;

    /** 3星4星5星 */
    private Integer teacherLevel;
    private String teacherLevelInfo;

    /** 支付方式 */
    private Integer payment;

    /**  */
    private String teacherPhone;

    /**  */
    private Integer provinceId;

    /**  */
    private Integer cityId;

    /**  */
    private Integer areaId;

    /**  */
    private Integer streetId;

    /**  */
    private String teacherAddress;

    /**  */
    private String teacherIdCard;

    /**  */
    private String teacherBirthday;

    /**  */
    private Integer teacherAge;

    /** 属相 */
    private Integer teacherAnimalSign;

    /**  */
    private Integer teacherNation;

    /** 籍贯 */
    private Integer teacherOrigin;

    /**  */
    private String teacherHeight;

    /**  */
    private String teacherWeight;

    /**  */
    private Integer teacherEducation;

    /** 开户行 */
    private String salaryBank;

    /** 工资卡 */
    private String salaryCard;

    /** 期望薪资 */
    private BigDecimal expectSalary;

    /** 中介费 */
    private String teacherAgencyFees;

    /** 押金 */
    private String teacherCashPledge;

    /** 有效期 */
    private Date validDate;

    /** 服务师是否常用168（常用）169（不常用） */
    private Integer hot;

    /**  */
    private String remark;

    /**  */
    private Date createTime;

    /**  */
    private Date modifyTime;

    /**  */
    private Integer isDel;

    public String getTeacherStatusInfo() {
        return teacherStatusInfo;
    }

    public void setTeacherStatusInfo(String teacherStatusInfo) {
        this.teacherStatusInfo = teacherStatusInfo;
    }

    public String getTeacherLevelInfo() {
        return teacherLevelInfo;
    }

    public void setTeacherLevelInfo(String teacherLevelInfo) {
        this.teacherLevelInfo = teacherLevelInfo;
    }

    private List<TeacherShopRes> shopResList;

    public String getTeacherAvatar() {
        return teacherAvatar;
    }

    public void setTeacherAvatar(String teacherAvatar) {
        this.teacherAvatar = teacherAvatar;
    }

    public Integer getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(Integer teacherSex) {
        this.teacherSex = teacherSex;
    }

    public String getTeacherSexInfo() {
        return teacherSexInfo;
    }

    public void setTeacherSexInfo(String teacherSexInfo) {
        this.teacherSexInfo = teacherSexInfo;
    }

    public Integer getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(Integer teacherType) {
        this.teacherType = teacherType;
    }

    public Integer getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(Integer teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherIdCard() {
        return teacherIdCard;
    }

    public void setTeacherIdCard(String teacherIdCard) {
        this.teacherIdCard = teacherIdCard;
    }

    public String getTeacherBirthday() {
        return teacherBirthday;
    }

    public void setTeacherBirthday(String teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }

    public Integer getTeacherAnimalSign() {
        return teacherAnimalSign;
    }

    public void setTeacherAnimalSign(Integer teacherAnimalSign) {
        this.teacherAnimalSign = teacherAnimalSign;
    }

    public Integer getTeacherNation() {
        return teacherNation;
    }

    public void setTeacherNation(Integer teacherNation) {
        this.teacherNation = teacherNation;
    }

    public Integer getTeacherOrigin() {
        return teacherOrigin;
    }

    public void setTeacherOrigin(Integer teacherOrigin) {
        this.teacherOrigin = teacherOrigin;
    }

    public String getTeacherHeight() {
        return teacherHeight;
    }

    public void setTeacherHeight(String teacherHeight) {
        this.teacherHeight = teacherHeight;
    }

    public String getTeacherWeight() {
        return teacherWeight;
    }

    public void setTeacherWeight(String teacherWeight) {
        this.teacherWeight = teacherWeight;
    }

    public Integer getTeacherEducation() {
        return teacherEducation;
    }

    public void setTeacherEducation(Integer teacherEducation) {
        this.teacherEducation = teacherEducation;
    }

    public String getSalaryBank() {
        return salaryBank;
    }

    public void setSalaryBank(String salaryBank) {
        this.salaryBank = salaryBank;
    }

    public String getSalaryCard() {
        return salaryCard;
    }

    public void setSalaryCard(String salaryCard) {
        this.salaryCard = salaryCard;
    }

    public BigDecimal getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(BigDecimal expectSalary) {
        this.expectSalary = expectSalary;
    }

    public String getTeacherAgencyFees() {
        return teacherAgencyFees;
    }

    public void setTeacherAgencyFees(String teacherAgencyFees) {
        this.teacherAgencyFees = teacherAgencyFees;
    }

    public String getTeacherCashPledge() {
        return teacherCashPledge;
    }

    public void setTeacherCashPledge(String teacherCashPledge) {
        this.teacherCashPledge = teacherCashPledge;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public List<TeacherShopRes> getShopResList() {
        return shopResList;
    }

    public void setShopResList(List<TeacherShopRes> shopResList) {
        this.shopResList = shopResList;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
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
