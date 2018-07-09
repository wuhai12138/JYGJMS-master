package com.summ.model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-06-22
 */
@TableName("j_nanny_dian_ping")
public class JNannyDianPing implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 大众点评点评Id
     */
    @TableId(value = "dianPingId", type = IdType.AUTO)
    private Integer dianPingId;
    @TableField("nannyId")
    private Integer nannyId;
    private String phote;
    @TableField("createDate")
    private Date createDate;
    @TableField("isDel")
    @TableLogic
    private Integer isDel;


    public Integer getDianPingId() {
        return dianPingId;
    }

    public void setDianPingId(Integer dianPingId) {
        this.dianPingId = dianPingId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getPhote() {
        return phote;
    }

    public void setPhote(String phote) {
        this.phote = phote;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JNannyDianPing{" +
        "dianPingId=" + dianPingId +
        ", nannyId=" + nannyId +
        ", phote=" + phote +
        ", createDate=" + createDate +
        ", isDel=" + isDel +
        "}";
    }
}
