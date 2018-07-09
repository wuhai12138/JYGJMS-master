package com.summ.utils.websocket;


/**
 * Created by Mc on 2016/11/24.
 */
public class WebSocketMsg {


    /**  */
    private String suggestContent;
    private String adminType;

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    public String getSuggestContent() {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent;
    }
}
