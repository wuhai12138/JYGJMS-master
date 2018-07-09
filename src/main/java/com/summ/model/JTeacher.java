package com.summ.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("j_teacher")
public class JTeacher implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer teacherId;

	/** 头像 */
	private String teacherAvatar;

	/** 服务师姓名 */
	private String teacherName;

	/**  */
	private Integer teacherSex;

	/**  */
	private Integer teacherStatus;

	/**  */
	private Integer teacherType;

	/** 3星4星5星 */
	private Integer teacherLevel;

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
	@TableLogic
	private Integer isDel;

	/**离职时间*/
	private Date dimissionTime;

	public Date getDimissionTime() {
		return dimissionTime;
	}

	public void setDimissionTime(Date dimissionTime) {
		this.dimissionTime = dimissionTime;
	}

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherAvatar() {
		return this.teacherAvatar;
	}

	public void setTeacherAvatar(String teacherAvatar) {
		this.teacherAvatar = teacherAvatar;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getTeacherSex() {
		return this.teacherSex;
	}

	public void setTeacherSex(Integer teacherSex) {
		this.teacherSex = teacherSex;
	}

	public Integer getTeacherStatus() {
		return this.teacherStatus;
	}

	public void setTeacherStatus(Integer teacherStatus) {
		this.teacherStatus = teacherStatus;
	}

	public Integer getTeacherType() {
		return this.teacherType;
	}

	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}

	public Integer getTeacherLevel() {
		return this.teacherLevel;
	}

	public void setTeacherLevel(Integer teacherLevel) {
		this.teacherLevel = teacherLevel;
	}

	public Integer getPayment() {
		return this.payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public String getTeacherPhone() {
		return this.teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
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

	public String getTeacherAddress() {
		return this.teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherIdCard() {
		return this.teacherIdCard;
	}

	public void setTeacherIdCard(String teacherIdCard) {
		this.teacherIdCard = teacherIdCard;
	}

	public String getTeacherBirthday() {
		return this.teacherBirthday;
	}

	public void setTeacherBirthday(String teacherBirthday) {
		this.teacherBirthday = teacherBirthday;
	}

	public Integer getTeacherAge() {
		return this.teacherAge;
	}

	public void setTeacherAge(Integer teacherAge) {
		this.teacherAge = teacherAge;
	}

	public Integer getTeacherAnimalSign() {
		return this.teacherAnimalSign;
	}

	public void setTeacherAnimalSign(Integer teacherAnimalSign) {
		this.teacherAnimalSign = teacherAnimalSign;
	}

	public Integer getTeacherNation() {
		return this.teacherNation;
	}

	public void setTeacherNation(Integer teacherNation) {
		this.teacherNation = teacherNation;
	}

	public Integer getTeacherOrigin() {
		return this.teacherOrigin;
	}

	public void setTeacherOrigin(Integer teacherOrigin) {
		this.teacherOrigin = teacherOrigin;
	}

	public String getTeacherHeight() {
		return this.teacherHeight;
	}

	public void setTeacherHeight(String teacherHeight) {
		this.teacherHeight = teacherHeight;
	}

	public String getTeacherWeight() {
		return this.teacherWeight;
	}

	public void setTeacherWeight(String teacherWeight) {
		this.teacherWeight = teacherWeight;
	}

	public Integer getTeacherEducation() {
		return this.teacherEducation;
	}

	public void setTeacherEducation(Integer teacherEducation) {
		this.teacherEducation = teacherEducation;
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

	public String getTeacherAgencyFees() {
		return this.teacherAgencyFees;
	}

	public void setTeacherAgencyFees(String teacherAgencyFees) {
		this.teacherAgencyFees = teacherAgencyFees;
	}

	public String getTeacherCashPledge() {
		return this.teacherCashPledge;
	}

	public void setTeacherCashPledge(String teacherCashPledge) {
		this.teacherCashPledge = teacherCashPledge;
	}

	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
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

}
