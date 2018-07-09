package com.summ.utils.mongodb.log.impl;

import com.summ.aspect.AspectPosition;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.log.LogUtil;
import com.summ.utils.mongodb.model.ErrorLog;
import com.summ.utils.mongodb.model.MongoConfig;
import org.aspectj.lang.JoinPoint;

public class ErrorLogUtil implements LogUtil {

	private MongoConfig mongoConfig;

	public ErrorLogUtil(MongoConfig mongoConfig) {
		this.mongoConfig = mongoConfig;
	}

	public Object doLog(JoinPoint joinPoint, Object retVal, AspectPosition p, Exception e) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object doLog(JoinPoint joinPoint, Object retVal, AspectPosition p) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object doLog(JoinPoint joinPoint, AspectPosition p) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object doLog(JoinPoint joinPoint, AspectPosition p, Exception e) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();

		String argList = "";
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			argList += arg.toString() + "(" + arg.getClass().getName() + ");";
		}

		String errorMesg = e.getMessage();
		for (StackTraceElement item : e.getStackTrace()) {
			errorMesg += "\r\n" + item.toString();
		}

		ErrorLog log = new ErrorLog(className, methodName, argList, errorMesg);
		log(log);
		return log;
	}

	ErrorLog log;

	private void log(ErrorLog errorLog) {
		log = errorLog;

		Thread logThread = new Thread() {
			public void run() {
				MongoDBUtil mongoUtil = MongoDBUtil.getInstance(mongoConfig);
				try {
					mongoUtil.insert("error_log", log);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		pool.execute(logThread);
	}

}
