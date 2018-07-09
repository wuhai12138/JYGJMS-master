package com.summ.utils;


import org.apache.log4j.Logger;

/**
 * Created by M.c on 2015/12/26.
 */
public class LogUtil {

    public static Logger getLogger() {
        return Logger.getLogger(new Throwable().getStackTrace()[2].getClass());
    }

}
