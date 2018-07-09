package com.summ.model.request;

import java.util.Date;
import java.util.List;

public class InvoiceReq {
    /** 发票种类 */
    private List<Integer> invoiceType;

    /** 消费类型 */
    private List<Integer> expenseType;
    private Date startDate;
    private Date endDate;
    private Integer page;
    private Integer size;

    public List<Integer> getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(List<Integer> invoiceType) {
        this.invoiceType = invoiceType;
    }

    public List<Integer> getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(List<Integer> expenseType) {
        this.expenseType = expenseType;
    }

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
