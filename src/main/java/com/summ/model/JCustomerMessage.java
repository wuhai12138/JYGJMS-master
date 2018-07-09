package com.summ.model;

import java.io.Serializable;
import java.util.Date;

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
@TableName("j_customer_message")
public class JCustomerMessage implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 客户反馈表 */
	@TableId(type = IdType.AUTO)
	private Integer messageId;

	/**  */
	private String content;

	/** 客户 */
	private Integer customerId;

	/**  */
	private Integer orderId;

	/**  */
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
	@TableLogic
	private Integer isDel;

	public JCustomerMessage() {
	}

	public JCustomerMessage(String content, Integer customerId, Integer orderId, Integer scheduleId, Date createDate, Integer messageStatus, Integer messageType, Integer noteAdmin, Integer isDel) {
		this.content = content;
		this.customerId = customerId;
		this.orderId = orderId;
		this.scheduleId = scheduleId;
		this.createDate = createDate;
		this.messageStatus = messageStatus;
		this.messageType = messageType;
		this.noteAdmin = noteAdmin;
		this.isDel = isDel;
	}

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getMessageStatus() {
		return this.messageStatus;
	}

	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}

	public Integer getMessageType() {
		return this.messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getNoteAdmin() {
		return this.noteAdmin;
	}

	public void setNoteAdmin(Integer noteAdmin) {
		this.noteAdmin = noteAdmin;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
