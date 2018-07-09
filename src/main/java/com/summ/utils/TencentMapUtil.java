package com.summ.utils;


import com.summ.Consts;
import com.summ.model.others.TencentMapDistanceRes;
import com.summ.model.others.TencentMapRes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class TencentMapUtil {

    public static Map getLngAndLat(String address) throws IOException {
        Map map = new HashMap();
        String url = "http://apis.map.qq.com/ws/geocoder/v1/?key=" + Consts.key + "&address=" + address;
        String json = loadJSON(url);
        TencentMapRes tencentMapRes = JsonUtil.json2Bean(RequestUtil.doGet(url), TencentMapRes.class);
        if (tencentMapRes.getStatus() == 0) {
            map.put("lng", tencentMapRes.getResult().getLocation().getLng());
            map.put("lat", tencentMapRes.getResult().getLocation().getLat());
            return map;
        } else {
            return null;
        }

    }

    public static String getDistance(double lng1,double lat1,double lng2,double lat2) throws IOException, InterruptedException {

        String url="http://apis.map.qq.com/ws/distance/v1/?mode=walking&from="+lat1+","+lng1+"&to="+lat2+","+lng2+"&key="+Consts.key;
        System.out.println("url>>>:"+url);
        TencentMapDistanceRes tencentMapDistanceRes = JsonUtil.json2Bean(RequestUtil.doGet(url),TencentMapDistanceRes.class);
        System.out.println("腾讯地图api返回:  "+JsonUtil.toJson(tencentMapDistanceRes));
        if (tencentMapDistanceRes.getStatus()==120){
            //毫秒
            Thread.sleep(1000);
            tencentMapDistanceRes = JsonUtil.json2Bean(RequestUtil.doGet(url),TencentMapDistanceRes.class);
            if (tencentMapDistanceRes.getStatus()==373 && tencentMapDistanceRes.getMessage().equals("起终点距离超长")){
                return "距离超过10公里";
            }
        }
        if (tencentMapDistanceRes.getStatus()==373 && tencentMapDistanceRes.getMessage().equals("起终点距离超长")){
            return "距离超过10公里";
        }
        System.out.println("tencentMapDistanceRes>>"+JsonUtil.toJson(tencentMapDistanceRes));
        BigDecimal b1 = new BigDecimal(tencentMapDistanceRes.getResult().getElements().get(0).getDistance());
        System.out.println("两个地点距离:"+b1 + "米");
        return b1.divide(new BigDecimal(1000),2,BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
}
