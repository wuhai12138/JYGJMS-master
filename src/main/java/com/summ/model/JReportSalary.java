package com.summ.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 *
 *
 */
@TableName("j_report_salary")
public class JReportSalary implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**  */
    @TableId(type = IdType.AUTO)
    private Integer reportId;

    /**
     * 工资月份
     */
    private Date salaryDate;

    /**  */
    private Integer shopId;

    /**  */
    private String shopName;

    /**
     * 工资人数
     */
    private Integer nannyAmount;

    /**
     * 三星工时
     */
    private String workTimeLevelThree;

    /**  */
    private String workTimeLevelFour;

    /**  */
    private String workTimeLevelFive;

    /**
     * 小时薪工资
     */
    private BigDecimal hourlySalary;

    /**
     * 奖励
     */
    private BigDecimal rewards;

    /**
     * 订单退款
     */
    private BigDecimal orderRefund;

    /**
     * 实际发放工资
     */
    private BigDecimal realSalary;

    /**
     * 个人所得税
     */
    private BigDecimal IndividualIncomeTax;

    /**
     * 财务审核
     */
    private Integer financialCheck=185;
    private String financialCheckRemark;

    /**
     * 财务审核人id
     */
    private Integer financialCheckId;

    /**
     * 财务审核时间
     */
    private Date financialCheckDate;

    /**
     * 门店工资名目确认
     */
    private Integer shopSalaryCheck=136;
    private String shopSalaryCheckRemark;

    /**
     * 门店确认人id
     */
    private Integer shopSalaryCheckId;

    /**
     * 门店确认时间
     */
    private Date shopSalaryCheckDate;

    /**
     * 门店工资发放确认
     */
    private Integer shopPayoffCheck=136;
    private String shopPayoffCheckRemark;

    /**  */
    private Integer shopPayoffCheckId;

    /**  */
    private Date shopPayoffCheckDate;

    /**
     * 发放状态
     */
    private Integer payoffStatus=187;

    /**  */
    private String remark;

    /**  */
    private Integer isDel = 16;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFinancialCheckRemark() {
        return financialCheckRemark;
    }

    public void setFinancialCheckRemark(String financialCheckRemark) {
        this.financialCheckRemark = financialCheckRemark;
    }

    public String getShopSalaryCheckRemark() {
        return shopSalaryCheckRemark;
    }

    public void setShopSalaryCheckRemark(String shopSalaryCheckRemark) {
        this.shopSalaryCheckRemark = shopSalaryCheckRemark;
    }

    public String getShopPayoffCheckRemark() {
        return shopPayoffCheckRemark;
    }

    public void setShopPayoffCheckRemark(String shopPayoffCheckRemark) {
        this.shopPayoffCheckRemark = shopPayoffCheckRemark;
    }

    public Integer getReportId() {
        return this.reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getSalaryDate() {
        return this.salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getNannyAmount() {
        return this.nannyAmount;
    }

    public void setNannyAmount(Integer nannyAmount) {
        this.nannyAmount = nannyAmount;
    }

    public String getWorkTimeLevelThree() {
        return this.workTimeLevelThree;
    }

    public void setWorkTimeLevelThree(String workTimeLevelThree) {
        this.workTimeLevelThree = workTimeLevelThree;
    }

    public String getWorkTimeLevelFour() {
        return workTimeLevelFour;
    }

    public void setWorkTimeLevelFour(String workTimeLevelFour) {
        this.workTimeLevelFour = workTimeLevelFour;
    }

    public String getWorkTimeLevelFive() {
        return workTimeLevelFive;
    }

    public void setWorkTimeLevelFive(String workTimeLevelFive) {
        this.workTimeLevelFive = workTimeLevelFive;
    }

    public BigDecimal getHourlySalary() {
        return this.hourlySalary;
    }

    public void setHourlySalary(BigDecimal hourlySalary) {
        this.hourlySalary = hourlySalary;
    }


    public BigDecimal getRealSalary() {
        return this.realSalary;
    }

    public void setRealSalary(BigDecimal realSalary) {
        this.realSalary = realSalary;
    }

    public BigDecimal getRewards() {
        return rewards;
    }

    public BigDecimal getOrderRefund() {
        return orderRefund;
    }

    public void setOrderRefund(BigDecimal orderRefund) {
        this.orderRefund = orderRefund;
    }

    public BigDecimal getIndividualIncomeTax() {
        return IndividualIncomeTax;
    }

    public void setIndividualIncomeTax(BigDecimal individualIncomeTax) {
        IndividualIncomeTax = individualIncomeTax;
    }

    public void setRewards(BigDecimal rewards) {
        this.rewards = rewards;
    }

    public Integer getFinancialCheck() {
        return this.financialCheck;
    }

    public void setFinancialCheck(Integer financialCheck) {
        this.financialCheck = financialCheck;
    }

    public Integer getFinancialCheckId() {
        return this.financialCheckId;
    }

    public void setFinancialCheckId(Integer financialCheckId) {
        this.financialCheckId = financialCheckId;
    }

    public Date getFinancialCheckDate() {
        return this.financialCheckDate;
    }

    public void setFinancialCheckDate(Date financialCheckDate) {
        this.financialCheckDate = financialCheckDate;
    }

    public Integer getShopSalaryCheck() {
        return shopSalaryCheck;
    }

    public void setShopSalaryCheck(Integer shopSalaryCheck) {
        this.shopSalaryCheck = shopSalaryCheck;
    }

    public Integer getShopSalaryCheckId() {
        return this.shopSalaryCheckId;
    }

    public void setShopSalaryCheckId(Integer shopSalaryCheckId) {
        this.shopSalaryCheckId = shopSalaryCheckId;
    }

    public Date getShopSalaryCheckDate() {
        return this.shopSalaryCheckDate;
    }

    public void setShopSalaryCheckDate(Date shopSalaryCheckDate) {
        this.shopSalaryCheckDate = shopSalaryCheckDate;
    }

    public Integer getShopPayoffCheck() {
        return this.shopPayoffCheck;
    }

    public void setShopPayoffCheck(Integer shopPayoffCheck) {
        this.shopPayoffCheck = shopPayoffCheck;
    }

    public Integer getShopPayoffCheckId() {
        return this.shopPayoffCheckId;
    }

    public void setShopPayoffCheckId(Integer shopPayoffCheckId) {
        this.shopPayoffCheckId = shopPayoffCheckId;
    }

    public Date getShopPayoffCheckDate() {
        return this.shopPayoffCheckDate;
    }

    public void setShopPayoffCheckDate(Date shopPayoffCheckDate) {
        this.shopPayoffCheckDate = shopPayoffCheckDate;
    }

    public Integer getPayoffStatus() {
        return this.payoffStatus;
    }

    public void setPayoffStatus(Integer payoffStatus) {
        this.payoffStatus = payoffStatus;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDel() {
        return this.isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}
