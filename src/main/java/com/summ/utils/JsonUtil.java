package com.summ.utils;


import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON转换工具类
 *
 * @author M.c
 * @date 2016-09-27
 * @toJson 将对象转换成json字符串
 * @getValue 在json字符串中，根据key值找到value
 * @json2Map 将json格式转换成map对象
 * @json2Bean 将json转换成bean对象
 * @json2List 将json格式转换成List对象
 * @Obj2Map obj 转为 map
 */
public class JsonUtil {

    private static final int JSON_INDENT = 4;

    /**
     * 将对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return "{}";
        }
        return JSON.toJSONString(obj);
    }

    /**
     * 在json字符串中，根据key值找到value
     *
     * @param json
     * @param key
     * @return
     */
    public static Object getValue(String json, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = json2Map(json);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

    /**
     * 将json格式转换成List对象
     *
     * @param json
     * @return
     */
    public static  <T> List<T> json2List(String json, Class<T> cls) {
        List<T> mList = JSON.parseArray(json,cls);

        return mList;
    }


    /**
     * 将json格式转换成map对象
     *
     * @param json
     * @return
     */
    public static <T>Map<String, T> json2Map(String json) {
        Map<String, T> objMap = null;
        objMap = (Map<String, T>) JSON.parse(json);
        return objMap;
    }

    /**
     * 将json转换成bean对象
     *
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        T obj = null;
        obj =JSON.parseObject(json, clazz);
        return obj;
    }


    /**
     * obj 转为 map
     *
     * @param obj 需要转的对象
     * @return
     */
    public static Map<String, Object> Obj2Map(Object obj) {
        if (obj != null) {
            return json2Map(toJson(obj));
        }
        return null;
    }

    /**
     * 将list转为key为list的map
     * @param o
     * @return
     */
    public static Object list2map(Object o){
        Map map = new HashMap();
        map.put("list",o);
        return map;
    }

    public static Object getObjectFromXML(String xml, Class tClass) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
    }

}
