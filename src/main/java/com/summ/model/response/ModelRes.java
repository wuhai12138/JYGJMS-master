package com.summ.model.response;

import com.summ.model.JDictInfo;
import com.summ.view.ConvertList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by M.c on 2016/6/9.
 */
public class ModelRes {

    private String info = "请求成功!";
    private int status = Status.SUCCESS;
    private Object data;

    public ModelRes() {
		this.data = new Object();

    }

    public ModelRes(Object data) {
        if(data instanceof List){
            this.data = new ConvertList((List)data);
        }else{
            this.data = data;
        }
    }



    public ModelRes(int status, String info) {
        this.info = info;
        this.status = status;
        data=new Object();
    }

    public ModelRes(int status, String info, Object date) {
        this.info = info;
        this.status = status;
        this.data = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public interface Status{
        int SUCCESS = 200;
        int FAILED = 400;
        int ERROR = 500;
        int BUILT = 201;
    }

}
