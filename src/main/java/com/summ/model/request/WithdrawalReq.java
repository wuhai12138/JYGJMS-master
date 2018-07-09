package com.summ.model.request;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 
 *
 */
public class WithdrawalReq implements Serializable {
	private Integer withdrawalId;
	private Integer applyId;
	private String applyName;
	private String applyPhone;
	private Integer checkId;

	private List<Integer> applyType;
	private Date startDate;
	private Date endDate;
	private List<Integer> checkStatus;
	private Integer page;
	private Integer size;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getApplyType() {
		return applyType;
	}

	public void setApplyType(List<Integer> applyType) {
		this.applyType = applyType;
	}

	public List<Integer> getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(List<Integer> checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getWithdrawalId() {
		return withdrawalId;
	}

	public void setWithdrawalId(Integer withdrawalId) {
		this.withdrawalId = withdrawalId;
	}


	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getApplyPhone() {
		return applyPhone;
	}

	public void setApplyPhone(String applyPhone) {
		this.applyPhone = applyPhone;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
