package com.summ.utils.mongodb.log.impl;

import com.summ.aspect.AspectPosition;
import com.summ.model.JAdmin;
import com.summ.utils.JsonUtil;
import com.summ.utils.StringUtil;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.log.LogUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import com.summ.utils.mongodb.model.OperateLog;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author johnson
 */
public class OperateLogUtil implements LogUtil {

    private MongoConfig mongoConfig;

    OperateLog log;

    private Properties operateMapper;

    /**
     * log map
     */
    Map logMap = new HashMap();


    /**
     * debug mode (set in spring-mvc.xml)
     */
    private boolean DEBUG_MODE = false;

    public OperateLogUtil(MongoConfig mongoConfig, boolean DEBUG_MODE, Properties operateMapper) {
        this.mongoConfig = mongoConfig;
        this.DEBUG_MODE = DEBUG_MODE;
        this.operateMapper = operateMapper;
    }

    private static Map<String, String> actionType = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;

        {
            put(".*add.*", "添加");
            put(".*insert.*", "添加");

            put(".*delete.*", "删除");
            put(".*clear.*", "删除");

            put(".*update.*", "更新");
            put(".*edit.*", "更新");
            put(".*change.*", "更新");
            put(".*set.*", "更新");

            put(".*query.*", "查询");
            put(".*get.*", "查询");
        }
    };

    public Object doLog(JoinPoint joinPoint, Object retVal, AspectPosition p, Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
//        String target = getTarge(className);

//        String paramNames = joinPoint.getSignature()
        Object[] args = joinPoint.getArgs();
        String argList = JsonUtil.toJson(args[0]);

//        for (Object arg : args) {
////            argList += arg.toString() + "(" + arg.getClass().getName() + ");";
//            argList = JsonUtil.toJson(arg);
//        }

        logMap.putAll(JsonUtil.json2Map(argList));
        System.out.println(">>>>>>>>>>>>>>>>>>>" + logMap);

        JAdmin admin = (JAdmin) request.getAttribute("admin");

        String action = getActionType(methodName);
        OperateLog log = null;
        switch (p) {
            case Aop_Before:
                log = new OperateLog(admin, className, methodName, argList, action, "开始", "");
                log(log);
                break;
            case Aop_Return:
                if (retVal != null) {
                    log = new OperateLog(admin, className, methodName, argList, action, "返回结果", retVal.toString());
                } else {
                    log = new OperateLog(admin, className, methodName, argList, action, "返回结果", "");
                }
                log(log);
                break;
            case Aop_After:
                log = new OperateLog(admin, className, methodName, argList, action, "结束", "");
                logMap.put("dateTime", log.getDateTime());
                logMap.put("adminId", String.valueOf(log.getAdminId()));
                logMap.put("className", log.getClassName());
                logMap.put("methodName", log.getMethodName());
                logMap.put("argList", log.getArgList());
                logMap.put("action", log.getAction());
                logMap.put("status", log.getStatus());
                logMap.put("retrunDat", log.getRetrunDat());
                System.out.println(">>>>>>>>>>>log>>>>>>>>" + log);
                System.out.println(">>>>>>>>>>logMap>>>>>>>>>" + logMap);
                log(logMap);
                break;
            case Aop_Throw:
                log = new OperateLog(admin, className, methodName, argList, action, "异常", "");
                log(log);
                break;
            default:
        }
        return log;
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

    /**
     * get action type by mehtodName
     *
     * @param methodName
     * @return
     */
    private String getActionType(String methodName) {
        Iterator<Entry<String, String>> it = actionType.entrySet().iterator();
        while (it.hasNext()) {
            methodName.toLowerCase();
            Entry<String, String> item = it.next();
            if (methodName.matches(item.getKey())) {
                return item.getValue();
            }
        }
        return "其他";
    }

    /**
     * get target name by className
     *
     * @param className
     * @return
     */
    private String getTarge(String className) {
        Iterator<Entry<Object, Object>> it = operateMapper.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
            String key = (String) entry.getKey();
            if (className.toLowerCase().matches(".*" + key.toLowerCase() + ".*")) {
                return (String) entry.getValue();
            }
        }
        return className;
    }

    /**
     * write into MongoDB
     *
     * @param logDat
     */
    private void log(OperateLog logDat) {
        log = logDat;
        Thread operateThread = new Thread() {
            public void run() {
                MongoDBUtil mongoUtil = null;
                try {
                    mongoUtil = MongoDBUtil.getInstance(mongoConfig);
                    // mongoUtil.setup();
                    mongoUtil.insert("operate_log", log);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // if (mongoUtil != null)
                    // mongoUtil.destory();
                }
            }
        };
        pool.execute(operateThread);
    }

    private void log(final Map map) {
        System.out.println("");
        Thread operateThread = new Thread() {
            public void run() {
                MongoDBUtil mongoUtil = null;
                try {
                    mongoUtil = MongoDBUtil.getInstance(mongoConfig);
                    // mongoUtil.setup();
                    mongoUtil.insert(map,"operate_log");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // if (mongoUtil != null)
                    // mongoUtil.destory();
                }
            }
        };
        pool.execute(operateThread);
    }
}
