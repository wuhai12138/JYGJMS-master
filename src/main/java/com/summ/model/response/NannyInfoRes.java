package com.summ.model.response;
import com.google.gson.annotations.SerializedName;
import com.summ.model.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by summ on 17/12/13.
 */
public class NannyInfoRes {
    /**  */
    private Integer nannyId;

    /**
     * 头像
     */
    private String nannyAvatar;

    /**
     * 服务师姓名
     */
    private String nannyName;

    /**  */
    private Integer nannySex;
    private String nannySexInfo;

    /**  */
    private Integer nannyStatus;
    private String nannyStatusInfo;

    /**  */
    private Integer nannyType;
    private String nannyTypeInfo;

    /**
     * 3星4星5星
     */
    private Integer nannyLevel;
    private String nannyLevelInfo;

    /**  */
    private String nannyPhone;

    /**  */
    private Integer provinceId;
    private String provinceIdInfo;

    /**  */
    private Integer cityId;
    private String cityIdInfo;

    /**  */
    private Integer areaId;
    private String areaIdInfo;

    private Integer streetId;
    private String streetIdInfo;

    /**  */
    private String nannyAddress;

    /**  */
    private String nannyIdCard;
    /**
     * 有效期
     */
    private Date idValidDate;

    /**  */
    private String nannyBirthday;

    /**  */
    private Integer nannyAge;
    /**工作年限*/
    private Integer nannyExperence;
    /**
     * 属相
     */
    private Integer nannyAnimalSign;
    private String nannyAnimalSignInfo;

    /**  */
    private Integer nannyNation;
    private String nannyNationInfo;

    private Integer nannyOrigin;
    private String nannyOriginInfo;

    private Integer nannyEducation;
    private String nannyEducationInfo;

    /**  */
    private String nannyHeight;

    /**  */
    private String nannyWeight;

    /**
     * 开户行
     */
    private String salaryBank;

    /**
     * 工资卡
     */
    private String salaryCard;

    /**
     * 期望薪资
     */
    private BigDecimal expectSalary;

    /**
     * 中介费
     */
    private BigDecimal nannyAgencyFees;

    /** 有效期 */
    private Date agencyValidDate;

    private Integer agencyPayStatus;
    private String agencyPayStatusInfo;

    /**
     * 支付方式
     */
    private int payment;
    private String paymentInfo;


    /**  */
    private String remark;

    private Integer hot;
    private String hotInfo;

    /**  */
    private Date createTime;

    /**  */
    private Date modifyTime;

    /**离职时间*/
    private Date dimissionTime;

    private Integer dataSignStatus;

    /**  */
    private Integer isDel;
    private String isDelInfo;

//    private List<NannyTrainRes> nannyTrainRes;
    private List<NannyJobLevelRes> nannyJobLevelRes;
    private List<NannyReligionRes> nannyReligionRes;
    private List<NannyLanguageRes> nannyLanguageRes;
    private List<NannySkillRes> nannySkillRes;
    private List<NannyCharacterRes> nannyCharacterRes;
    private List<NannyCertificateRes> nannyCertificateRes;
    private List<NannyShopRes> nannyShopRes;

    public Integer getNannyExperence() {
        return nannyExperence;
    }

    public void setNannyExperence(Integer nannyExperence) {
        this.nannyExperence = nannyExperence;
    }

    public Integer getDataSignStatus() {
        return dataSignStatus;
    }

    public void setDataSignStatus(Integer dataSignStatus) {
        this.dataSignStatus = dataSignStatus;
    }

    public String getAgencyPayStatusInfo() {
        return agencyPayStatusInfo;
    }

    public void setAgencyPayStatusInfo(String agencyPayStatusInfo) {
        this.agencyPayStatusInfo = agencyPayStatusInfo;
    }

    public BigDecimal getNannyAgencyFees() {
        return nannyAgencyFees;
    }

    public Date getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(Date dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    public String getHotInfo() {
        return hotInfo;
    }

    public void setHotInfo(String hotInfo) {
        this.hotInfo = hotInfo;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public List<NannyShopRes> getNannyShopRes() {
        return nannyShopRes;
    }

    public void setNannyShopRes(List<NannyShopRes> nannyShopRes) {
        this.nannyShopRes = nannyShopRes;
    }

    public String getNannyAnimalSignInfo() {
        return nannyAnimalSignInfo;
    }

    public void setNannyAnimalSignInfo(String nannyAnimalSignInfo) {
        this.nannyAnimalSignInfo = nannyAnimalSignInfo;
    }

    public String getNannyNationInfo() {
        return nannyNationInfo;
    }

    public void setNannyNationInfo(String nannyNationInfo) {
        this.nannyNationInfo = nannyNationInfo;
    }

    public String getNannyOriginInfo() {
        return nannyOriginInfo;
    }

    public void setNannyOriginInfo(String nannyOriginInfo) {
        this.nannyOriginInfo = nannyOriginInfo;
    }

    public Integer getNannyEducation() {
        return nannyEducation;
    }

    public void setNannyEducation(Integer nannyEducation) {
        this.nannyEducation = nannyEducation;
    }

    public String getNannyEducationInfo() {
        return nannyEducationInfo;
    }

    public void setNannyEducationInfo(String nannyEducationInfo) {
        this.nannyEducationInfo = nannyEducationInfo;
    }

    public List<NannyReligionRes> getNannyReligionRes() {
        return nannyReligionRes;
    }

    public void setNannyReligionRes(List<NannyReligionRes> nannyReligionRes) {
        this.nannyReligionRes = nannyReligionRes;
    }

    public List<NannyLanguageRes> getNannyLanguageRes() {
        return nannyLanguageRes;
    }

    public void setNannyLanguageRes(List<NannyLanguageRes> nannyLanguageRes) {
        this.nannyLanguageRes = nannyLanguageRes;
    }

    public List<NannySkillRes> getNannySkillRes() {
        return nannySkillRes;
    }

    public void setNannySkillRes(List<NannySkillRes> nannySkillRes) {
        this.nannySkillRes = nannySkillRes;
    }

    public List<NannyCharacterRes> getNannyCharacterRes() {
        return nannyCharacterRes;
    }

    public void setNannyCharacterRes(List<NannyCharacterRes> nannyCharacterRes) {
        this.nannyCharacterRes = nannyCharacterRes;
    }

    public List<NannyCertificateRes> getNannyCertificateRes() {
        return nannyCertificateRes;
    }

    public void setNannyCertificateRes(List<NannyCertificateRes> nannyCertificateRes) {
        this.nannyCertificateRes = nannyCertificateRes;
    }

    public List<NannyJobLevelRes> getNannyJobLevelRes() {
        return nannyJobLevelRes;
    }

    public void setNannyJobLevelRes(List<NannyJobLevelRes> nannyJobLevelRes) {
        this.nannyJobLevelRes = nannyJobLevelRes;
    }

    public NannyInfoRes() {
    }

    public String getNannySexInfo() {
        return nannySexInfo;
    }

    public void setNannySexInfo(String nannySexInfo) {
        this.nannySexInfo = nannySexInfo;
    }

    public String getNannyStatusInfo() {
        return nannyStatusInfo;
    }

    public void setNannyStatusInfo(String nannyStatusInfo) {
        this.nannyStatusInfo = nannyStatusInfo;
    }

    public String getNannyTypeInfo() {
        return nannyTypeInfo;
    }

    public void setNannyTypeInfo(String nannyTypeInfo) {
        this.nannyTypeInfo = nannyTypeInfo;
    }

    public String getProvinceIdInfo() {
        return provinceIdInfo;
    }

    public void setProvinceIdInfo(String provinceIdInfo) {
        this.provinceIdInfo = provinceIdInfo;
    }

    public String getCityIdInfo() {
        return cityIdInfo;
    }

    public void setCityIdInfo(String cityIdInfo) {
        this.cityIdInfo = cityIdInfo;
    }

    public String getAreaIdInfo() {
        return areaIdInfo;
    }

    public void setAreaIdInfo(String areaIdInfo) {
        this.areaIdInfo = areaIdInfo;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public String getStreetIdInfo() {
        return streetIdInfo;
    }

    public void setStreetIdInfo(String streetIdInfo) {
        this.streetIdInfo = streetIdInfo;
    }

    public String getIsDelInfo() {
        return isDelInfo;
    }

    public void setIsDelInfo(String isDelInfo) {
        this.isDelInfo = isDelInfo;
    }

    public void setNannyAgencyFees(BigDecimal nannyAgencyFees) {
        this.nannyAgencyFees = nannyAgencyFees;
    }

    public Integer getNannyId() {
        return this.nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getNannyAvatar() {
        return this.nannyAvatar;
    }

    public void setNannyAvatar(String nannyAvatar) {
        this.nannyAvatar = nannyAvatar;
    }

    public String getNannyName() {
        return this.nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public Integer getNannySex() {
        return this.nannySex;
    }

    public void setNannySex(Integer nannySex) {
        this.nannySex = nannySex;
    }

    public Integer getNannyStatus() {
        return this.nannyStatus;
    }

    public void setNannyStatus(Integer nannyStatus) {
        this.nannyStatus = nannyStatus;
    }

    public Integer getNannyType() {
        return this.nannyType;
    }

    public void setNannyType(Integer nannyType) {
        this.nannyType = nannyType;
    }

    public Integer getNannyLevel() {
        return this.nannyLevel;
    }

    public void setNannyLevel(Integer nannyLevel) {
        this.nannyLevel = nannyLevel;
    }

    public String getNannyPhone() {
        return this.nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
    }

    public Integer getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getNannyAddress() {
        return this.nannyAddress;
    }

    public void setNannyAddress(String nannyAddress) {
        this.nannyAddress = nannyAddress;
    }

    public String getNannyIdCard() {
        return this.nannyIdCard;
    }

    public void setNannyIdCard(String nannyIdCard) {
        this.nannyIdCard = nannyIdCard;
    }

    public String getNannyBirthday() {
        return this.nannyBirthday;
    }

    public void setNannyBirthday(String nannyBirthday) {
        this.nannyBirthday = nannyBirthday;
    }

    public Integer getNannyAge() {
        return this.nannyAge;
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

    public String getNannyHeight() {
        return this.nannyHeight;
    }

    public void setNannyHeight(String nannyHeight) {
        this.nannyHeight = nannyHeight;
    }

    public String getNannyWeight() {
        return this.nannyWeight;
    }

    public void setNannyWeight(String nannyWeight) {
        this.nannyWeight = nannyWeight;
    }

    public String getSalaryBank() {
        return this.salaryBank;
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
        return this.expectSalary;
    }

    public void setExpectSalary(BigDecimal expectSalary) {
        this.expectSalary = expectSalary;
    }

    public int getPayment() {
        return this.payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Date getIdValidDate() {
        return idValidDate;
    }

    public void setIdValidDate(Date idValidDate) {
        this.idValidDate = idValidDate;
    }

    public Date getAgencyValidDate() {
        return agencyValidDate;
    }

    public void setAgencyValidDate(Date agencyValidDate) {
        this.agencyValidDate = agencyValidDate;
    }

    public Integer getAgencyPayStatus() {
        return agencyPayStatus;
    }

    public void setAgencyPayStatus(Integer agencyPayStatus) {
        this.agencyPayStatus = agencyPayStatus;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsDel() {
        return this.isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getNannyLevelInfo() {
        return nannyLevelInfo;
    }

    public void setNannyLevelInfo(String nannyLevelInfo) {
        this.nannyLevelInfo = nannyLevelInfo;
    }
}