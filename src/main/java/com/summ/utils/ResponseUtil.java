package com.summ.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by summ on 17/11/30.
 */
public class ResponseUtil {

    /**
     * 讲数组封装进Map
     * @param list
     * @return
     */
    public static Map List2Map(List list){
        Map map = new HashMap();
        map.put("list",list);
        return  map;
    }

    public static Map List2Map(List list,Integer count){
        Map map = new HashMap();
        map.put("list",list);
        map.put("count",count);
        return  map;
    }

    /**
     * 判断对象里参数是否有null
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {

        boolean flag = false;
        for(Field f : obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.get(obj) == null){
                flag = true;
                return flag;
            }
        }
        return flag;
    }

}
