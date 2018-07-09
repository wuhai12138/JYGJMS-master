package com.summ.utils.mongodb.log.impl;

import com.summ.aspect.AspectPosition;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.log.LogUtil;
import com.summ.utils.mongodb.model.DebugLog;
import com.summ.utils.mongodb.model.MongoConfig;
import org.aspectj.lang.JoinPoint;

public class DebugLogUtil implements LogUtil {

	private MongoConfig mongoConfig;
	private String layer;

	public DebugLogUtil(MongoConfig mongoConfig, String layer) {
		this.mongoConfig = mongoConfig;
		this.layer = layer;
	}

	public Object doLog(JoinPoint joinPoint, Object retVal, AspectPosition p, Exception e) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();

		String argList = "";
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			argList += arg.toString() + "(" + arg.getClass().getName() + ");";
		}

		DebugLog debugLog = null;
		return debugLog;
	}

	public Object doLog(JoinPoint joinPoint, Object retVal, AspectPosition p) {
		return doLog(joinPoint, retVal, p, null);
	}

	public Object doLog(JoinPoint joinPoint, AspectPosition p) {
		return doLog(joinPoint, "", p, null);
	}

	public Object doLog(JoinPoint joinPoint, AspectPosition p, Exception e) {
		return doLog(joinPoint, "", p, e);
	}

	DebugLog log;

	/**
	 * write into mongoDB server
	 * 
	 * @param debugLog
	 */
	private void log(DebugLog debugLog) {
		log = debugLog;
		Thread logThread = new Thread() {
			public void run() {
				MongoDBUtil mongoUtil = null;
				try {
					mongoUtil = MongoDBUtil.getInstance(mongoConfig);
					// mongoUtil.setup();
					mongoUtil.insert("debug_log", log);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// if (mongoUtil != null)
					// mongoUtil.destory();
				}
			}
		};
		pool.execute(logThread);
	}

}
