package com.summ.model;

import java.io.Serializable;

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
@TableName("j_nanny_character")
public class JNannyCharacter implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer nannyChaId;

	/**  */
	private Integer characterId;

	/**  */
	private Integer nannyId;

	/**  */
	private Integer isDel = 16;


	public Integer getNannyChaId() {
		return this.nannyChaId;
	}

	public void setNannyChaId(Integer nannyChaId) {
		this.nannyChaId = nannyChaId;
	}

	public Integer getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
