package com.summ.model.response;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 
 *
 */
public class WithdrawalRes implements Serializable {

	/** 提现表 */
	private Integer withdrawalId;
	/**  */
	private Integer provinceId;
	private String province;
	/**  */
	private Integer cityId;
	private String city;
	/**  */
	private Integer areaId;
	private String area;
	/**  */
	private Integer streetId;
	private String street;
	/**  */
	private String withdrawalCard;
	/**  */
	private String withdrawalIdAddress;
	/** 申请人类别 */
	private Integer applyType;
	private String applyTypeInfo;

	/** 申请人编号 */
	private Integer applyId;
	/**  */
	private String applyName;
	/**  */
	private String applyPhone;
	private BigDecimal money;
	/**  */
	private Date applyDate;
	/**  */
	private Integer checkId;
	private String checkName;
	/**  */
	private Date checkDate;
	/**  */
	private Integer checkStatus;
	private String checkStatusInfo;

	private Integer payId;
	private String payName;
	private Date payDate;

	/**  */
	private String remark;
	/**  */
	private Integer isDel;

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

    public String getApplyTypeInfo() {
        return applyTypeInfo;
    }

    public void setApplyTypeInfo(String applyTypeInfo) {
        this.applyTypeInfo = applyTypeInfo;
    }

    public String getCheckStatusInfo() {
		return checkStatusInfo;
	}

	public void setCheckStatusInfo(String checkStatusInfo) {
		this.checkStatusInfo = checkStatusInfo;
	}

	public Integer getWithdrawalId() {
		return this.withdrawalId;
	}

	public void setWithdrawalId(Integer withdrawalId) {
		this.withdrawalId = withdrawalId;
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

	public Integer getStreetId() {
		return this.streetId;
	}

	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}

	public String getWithdrawalCard() {
		return this.withdrawalCard;
	}

	public void setWithdrawalCard(String withdrawalCard) {
		this.withdrawalCard = withdrawalCard;
	}

	public String getWithdrawalIdAddress() {
		return this.withdrawalIdAddress;
	}

	public void setWithdrawalIdAddress(String withdrawalIdAddress) {
		this.withdrawalIdAddress = withdrawalIdAddress;
	}

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getApplyName() {
		return this.applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getApplyPhone() {
		return this.applyPhone;
	}

	public void setApplyPhone(String applyPhone) {
		this.applyPhone = applyPhone;
	}

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
}
