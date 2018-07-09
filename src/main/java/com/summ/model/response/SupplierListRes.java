package com.summ.model.response;

import java.util.Date;

public class SupplierListRes {
    private Integer supplierId;
    private Integer companyId;
    private String name;
    private String phone;
    private Integer supplierStatus;
    private String supplierStatusInfo;
    private String remark;
    private String companyName;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**  */
    private String address;


    /** 联系人 */
    private String contect;

    /**  */
    private String contacter;

    /**  */
    private Integer provinceId;

    /**  */
    private Integer cityId;

    /**  */
    private Integer areaId;
    private String areaIdInfo;

    /** 公司注册地址 */
    private String registerAddress;

    /** 统一社会信用代码 */
    private String creditCode;

    /** 经营范围 */
    private String businessScope;
    /**经营期限*/
    private Date businessStartDate;
    private Date businessEndDate;

    /** 法人 */
    private String legalPerson;

    /**  */
    private String idCard;

    /** 身份证有效期 */
    private Date idValidDate;

    /** 开户行 */
    private String depositBank;

    /** 银行账号 */
    private String bankAccount;

    /** 营业执照 图片 */
    private String businessLicense;

    /** 身份证正面照 */
    private String idCardBefore;

    /** 身份证反面照 */
    private String idCardAfter;

    /**  */
    private Date createTime;

    /**  */
    private Integer isDel;

    public Date getBusinessStartDate() {
        return businessStartDate;
    }

    public void setBusinessStartDate(Date businessStartDate) {
        this.businessStartDate = businessStartDate;
    }

    public Date getBusinessEndDate() {
        return businessEndDate;
    }

    public void setBusinessEndDate(Date businessEndDate) {
        this.businessEndDate = businessEndDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContect() {
        return contect;
    }

    public void setContect(String contect) {
        this.contect = contect;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
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

    public String getAreaIdInfo() {
        return areaIdInfo;
    }

    public void setAreaIdInfo(String areaIdInfo) {
        this.areaIdInfo = areaIdInfo;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getIdValidDate() {
        return idValidDate;
    }

    public void setIdValidDate(Date idValidDate) {
        this.idValidDate = idValidDate;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getIdCardBefore() {
        return idCardBefore;
    }

    public void setIdCardBefore(String idCardBefore) {
        this.idCardBefore = idCardBefore;
    }

    public String getIdCardAfter() {
        return idCardAfter;
    }

    public void setIdCardAfter(String idCardAfter) {
        this.idCardAfter = idCardAfter;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(Integer supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierStatusInfo() {
        return supplierStatusInfo;
    }

    public void setSupplierStatusInfo(String supplierStatusInfo) {
        this.supplierStatusInfo = supplierStatusInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
