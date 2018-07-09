package com.summ.model.request;

import java.math.BigDecimal;
import java.util.Date;

public class NannyInfoInsertReq {
    /** 头像 */
    private String nannyAvatar;

    /** 服务师姓名 */
    private String nannyName;

    /**  */
    private Integer nannySex;

    /**  */
    private Integer nannyStatus = 55;

    /**  */
    private Integer nannyType;

    /** 3星4星5星 */
    private Integer nannyLevel = 84;

    /**  */
    private String nannyPhone;

    /**  */
    private Integer provinceId;

    /**  */
    private Integer cityId;

    /**  */
    private Integer areaId;

    private Integer streetId;

    /**  */
    private String nannyAddress;

    /**  */
    private String nannyIdCard;

    /**  */
    private String nannyBirthday;

    /**  */
    private Integer nannyAge;

    /** 属相 默认为暂无 */
    private Integer nannyAnimalSign=126;

    /**  民族 默认为暂无 */
    private Integer nannyNation=125;

    private Integer nannyOrigin=36;

    private Integer nannyEducation=101;

    /**  */
    private String nannyHeight;

    /**  */
    private String nannyWeight;

    /** 开户行 */
    private String salaryBank;

    /** 工资卡 */
    private String salaryCard;

    /** 期望薪资 */
    private BigDecimal expectSalary;

    /** 中介费 */
    private BigDecimal nannyAgencyFees;

    /** 支付方式 */
    private int payment=127;

    /** 有效期 */
    private Date validDate;

    /**  */
    private String remark;

    /**  */
    private Date createTime;

    /**  */
    private Date modifyTime = new Date();

    /**  */
    private Integer isDel =16;

    /** 服务师是否常用168（常用）169（不常用） */
    private Integer hot=169;

    /** 服务师押金*/
    private Integer nannyCashPledge;

    private Integer shopId;

    public String getNannyAvatar() {
        return nannyAvatar;
    }

    public void setNannyAvatar(String nannyAvatar) {
        this.nannyAvatar = nannyAvatar;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public Integer getNannySex() {
        return nannySex;
    }

    public void setNannySex(Integer nannySex) {
        this.nannySex = nannySex;
    }

    public Integer getNannyStatus() {
        return nannyStatus;
    }

    public void setNannyStatus(Integer nannyStatus) {
        this.nannyStatus = nannyStatus;
    }

    public Integer getNannyType() {
        return nannyType;
    }

    public void setNannyType(Integer nannyType) {
        this.nannyType = nannyType;
    }

    public Integer getNannyLevel() {
        return nannyLevel;
    }

    public void setNannyLevel(Integer nannyLevel) {
        this.nannyLevel = nannyLevel;
    }

    public String getNannyPhone() {
        return nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
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

    public String getNannyAddress() {
        return nannyAddress;
    }

    public void setNannyAddress(String nannyAddress) {
        this.nannyAddress = nannyAddress;
    }

    public String getNannyIdCard() {
        return nannyIdCard;
    }

    public void setNannyIdCard(String nannyIdCard) {
        this.nannyIdCard = nannyIdCard;
    }

    public String getNannyBirthday() {
        return nannyBirthday;
    }

    public void setNannyBirthday(String nannyBirthday) {
        this.nannyBirthday = nannyBirthday;
    }

    public Integer getNannyAge() {
        return nannyAge;
    }

    public void setNannyAge(Integer nannyAge) {
        this.nannyAge = nannyAge;
    }

    public Integer getNannyAnimalSign() {
        return nannyAnimalSign;
    }

    public void setNannyAnimalSign(Integer nannyAnimalSign) {
        this.nannyAnimalSign = nannyAnimalSign;
    }

    public Integer getNannyNation() {
        return nannyNation;
    }

    public void setNannyNation(Integer nannyNation) {
        this.nannyNation = nannyNation;
    }

    public Integer getNannyOrigin() {
        return nannyOrigin;
    }

    public void setNannyOrigin(Integer nannyOrigin) {
        this.nannyOrigin = nannyOrigin;
    }

    public Integer getNannyEducation() {
        return nannyEducation;
    }

    public void setNannyEducation(Integer nannyEducation) {
        this.nannyEducation = nannyEducation;
    }

    public String getNannyHeight() {
        return nannyHeight;
    }

    public void setNannyHeight(String nannyHeight) {
        this.nannyHeight = nannyHeight;
    }

    public String getNannyWeight() {
        return nannyWeight;
    }

    public void setNannyWeight(String nannyWeight) {
        this.nannyWeight = nannyWeight;
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

    public BigDecimal getNannyAgencyFees() {
        return nannyAgencyFees;
    }

    public void setNannyAgencyFees(BigDecimal nannyAgencyFees) {
        this.nannyAgencyFees = nannyAgencyFees;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getNannyCashPledge() {
        return nannyCashPledge;
    }

    public void setNannyCashPledge(Integer nannyCashPledge) {
        this.nannyCashPledge = nannyCashPledge;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
