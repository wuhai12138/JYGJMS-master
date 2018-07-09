package com.summ.utils.mongodb.log;

import com.summ.aspect.AspectPosition;
import org.aspectj.lang.JoinPoint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author johnson
 *
 */
public interface LogUtil {

	static ExecutorService pool = Executors.newCachedThreadPool();

	public Object doLog(JoinPoint joinPoint, Object retVal, AspectPosition p, Exception e);

	public Object doLog(JoinPoint joinPoint, Object retVal, AspectPosition p);

	public Object doLog(JoinPoint joinPoint, AspectPosition p);

	public Object doLog(JoinPoint joinPoint, AspectPosition p, Exception e);

}
