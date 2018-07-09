package com.summ.aspect;

import com.summ.Consts;
import com.summ.utils.mongodb.log.LogFactory;
import com.summ.utils.mongodb.log.impl.OperateLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * aspectj+mongodb 记录接口操作日志
 * @author johnson
 */

@Aspect
public class ControllerAspect {

    private LogFactory logFactory;
    private OperateLogUtil operateLogUtil;

    public LogFactory getLogFactory() {
        return logFactory;
    }

    public void setLogFactory(LogFactory logFactory) {
        this.logFactory = logFactory;
    }

    @Pointcut("execution(* com.summ.controller.*.*Controller*.*(..))")
    private void aopController() {
    }

    /**
     * after controller
     *
     * @param joinPoint
     */
    @After("aopController()")
    public void afterController(JoinPoint joinPoint) {
        System.out.println("after controller》》》》》》》》》》》");
//        System.out.println(logFactory.getInstance("operate_log").doLog(joinPoint, AspectPosition.Aop_After));
//        if (Consts.DEBUG_MODE) {
//            System.out.println("[debug model] "
//                    + logFactory.getInstance("debug_log", "controller").doLog(joinPoint, AspectPosition.Aop_After));
//        }
    }

    /**
     * before controller
     *
     * @param joinPoint
     */
    @Before("aopController()")
    public void beforeController(JoinPoint joinPoint) {
//        System.out.println("before controller》》》》》》》》》》》" + joinPoint.toString());
//		System.out.println(logFactory.getInstance("operate_log").doLog(joinPoint, AspectPosition.Aop_Before));
//		if (Consts.DEBUG_MODE)
//			System.out.println("[debug model] "
//					+ logFactory.getInstance("debug_log", "controller").doLog(joinPoint, AspectPosition.Aop_Before));
    }

    /**
     * after controller returning
     *
     * @param joinPoint
     * @param retVal
     */
    @AfterReturning(pointcut = "aopController()", returning = "retVal")
    public void returnController(JoinPoint joinPoint, Object retVal) {
//        System.out.println("after returning》》》》》》》》》》》");
//		System.out.println(logFactory.getInstance("operate_log").doLog(joinPoint, retVal, AspectPosition.Aop_Return));
//		if (Consts.DEBUG_MODE)
//			System.out.println("[debug model] " + logFactory.getInstance("debug_log", "controller").doLog(joinPoint,
//					retVal, AspectPosition.Aop_Return));
    }


    /**
     * after controller throwing exceptions
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "aopController()", throwing = "e")
    public void ControllerThrow(JoinPoint joinPoint, Exception e) {
//		System.out.println(logFactory.getInstance("operate_log").doLog(joinPoint, AspectPosition.Aop_Throw));
//		if (Consts.DEBUG_MODE)
//			System.out.println("[debug model] "
//					+ logFactory.getInstance("debug_log", "controller").doLog(joinPoint, AspectPosition.Aop_Throw, e));
//		System.out.println(logFactory.getInstance("error_log").doLog(joinPoint, AspectPosition.Aop_Throw, e));
    }

}
