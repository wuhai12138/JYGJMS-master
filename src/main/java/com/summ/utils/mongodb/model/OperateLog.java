package com.summ.utils.mongodb.model;


import com.summ.model.JAdmin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author johnson
 */
public class OperateLog {
    String dateTime;
    int adminId;
    /**
     * admin's real name
     */
    String adminName;
    String className;
    String methodName;
    String argList;
    /**
     * action:[add,delete,update,query...]
     */
    String action;
    /**
     * before/after/return action
     */
    String status;
    String retrunDat;

    /**
     * structure with all fields
     *
     * @param admin
     * @param className
     * @param methodName
     * @param argList
     * @param action
     * @param retrunDat
     */
	public OperateLog(JAdmin admin, String className, String methodName, String argList,
                      String action, String status, String retrunDat) {
		super();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		this.dateTime = df.format(new Date());
		if (admin != null) {
			this.adminId = admin.getAdminId();
			this.adminName = admin.getAdminName();
		} else {
			this.adminId = -1;
			this.adminName = "";
		}
		this.className = className;
		this.methodName = methodName;
		this.argList = argList;
		this.action = action;
		this.status = status;
		this.retrunDat = retrunDat;
	}

    /**
     * structured without returnDat
     *
     * @param admin
     * @param basicModel
     * @param className
     * @param methodName
     * @param argList
     * @param action
     */
    public OperateLog(String admin, String basicModel, String className, String methodName, String argList,
                      String action, String target, String status) {
        super();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        this.dateTime = df.format(new Date());
        this.adminId = -1;
        this.adminName = "";
        this.className = className;
        this.methodName = methodName;
        this.argList = argList;
        this.action = action;
        this.status = status;
    }

    public OperateLog() {
        super();
    }

    @Override
    public String toString() {
        return dateTime + " " + adminName + action  + " 状态:" + status + " (用户ID:" + adminId + ", 类名=" + className + ", 方法名=" + methodName + ", 参数列表="
                + argList + ", 返回值=" + retrunDat + ")";
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getArgList() {
        return argList;
    }

    public void setArgList(String argList) {
        this.argList = argList;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRetrunDat() {
        return retrunDat;
    }

    public void setRetrunDat(String retrunDat) {
        this.retrunDat = retrunDat;
    }

}
