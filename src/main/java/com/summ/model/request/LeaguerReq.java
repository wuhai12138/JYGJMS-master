package com.summ.model.request;

public class LeaguerReq {
    private String leaguerName;
    private String leaguerPhone;
    private Integer leaguerId;
    private Integer leaguerStatus;
    private Integer page;
    private Integer size;

    public String getLeaguerName() {
        return leaguerName;
    }

    public void setLeaguerName(String leaguerName) {
        this.leaguerName = leaguerName;
    }

    public String getLeaguerPhone() {
        return leaguerPhone;
    }

    public void setLeaguerPhone(String leaguerPhone) {
        this.leaguerPhone = leaguerPhone;
    }

    public Integer getLeaguerId() {
        return leaguerId;
    }

    public void setLeaguerId(Integer leaguerId) {
        this.leaguerId = leaguerId;
    }

    public Integer getLeaguerStatus() {
        return leaguerStatus;
    }

    public void setLeaguerStatus(Integer leaguerStatus) {
        this.leaguerStatus = leaguerStatus;
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
