package com.summ.model.request;

/**
 * Created by summ on 17/12/27.
 */
public class BaseReq {

    private String sign;

    @Override
    public String toString() {
        return "BaseReq{" +
                "sign='" + sign + '\'' +
                '}';
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
