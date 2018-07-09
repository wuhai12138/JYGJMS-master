package com.summ.utils;

import com.summ.Consts;
import com.summ.model.others.BaiDuApiObj;
import com.summ.model.response.BaiDuMapRes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tan on 2016/10/10.
 */
public class BaiduAPIUtil {
    public static Map<String, Double> getLngAndLat(String address) {
        Map<String, Double> map = new HashMap<String, Double>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
                + "&output=json&ak="+ Consts.ak;
        String json = loadJSON(url);

        BaiDuMapRes baiDuMapRes = JsonUtil.json2Bean(json,BaiDuMapRes.class);
        if (baiDuMapRes.getStatus()!=1&&baiDuMapRes.getResult().getLocation()!=null){
            map.put("lng", baiDuMapRes.getResult().getLocation().getLng());
            map.put("lat", baiDuMapRes.getResult().getLocation().getLat());
            return map;
        }else{
            return null;
        }
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
        }
        catch (MalformedURLException e) {}
        catch (IOException e) {}
        return json.toString();
    }
    public static double getDistance(double lng1,double lat1,double lng2,double lat2) throws IOException {
        //        上海市吴中路1081号 经度：121.39355---纬度：31.182585
//        巴国布衣吴中路店 经度：121.42995---纬度：31.217407
//        海底捞吴中路店 经度：121.38513---纬度：31.180773
//        上海东方明珠 经度：121.50647---纬度：31.245342

//        double pk = 180 / Math.PI;
//        double a1 = 121.39355 / pk;
//        double a2 = 31.182585 / pk;
//        double b1 = 121.386555 / pk;
//        double b2 = 31.181361 / pk;



//        double pk = 180 / Math.PI;
//        double a1 = lng1 / pk;
//        double a2 = lat2 / pk;
//        double b1 = lng2 / pk;
//        double b2 = lat2 / pk;
//        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
//        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
//        double t3 = Math.sin(a1) * Math.sin(b1);
//        double tt = Math.acos(t1 + t2 + t3);

        String url="http://api.map.baidu.com/routematrix/v2/walking?ak=dknEDuTMsP5r7w87vVZMlLVa64Nv6oWG&origins="+lat1+","+lng1+"&destinations="+lat2+","+lng2+"&output=json";
        BaiDuApiObj baiDuApiObj = JsonUtil.json2Bean(RequestUtil.doGet(url),BaiDuApiObj.class);
        BigDecimal b1 = new BigDecimal(baiDuApiObj.getResult().get(0).getDistance().getValue());
        System.out.println("两个地点距离:"+6366 * 100 + "米");
        return b1.divide(new BigDecimal(1000),2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {

//        System.out.println(BaiduAPIUtil.isInclude(121.39355,31.217407,121.38513,31.180773));

    }

    /**
     * 默认地球半径
     */
    private static double EARTH_RADIUS = 6371;

    /**
     * 计算经纬度点对应正方形4个点的坐标
     *
     * @param longitude
     * @param latitude
     * @param distance
     * @return
     */
    public static Map<String, double[]> returnLLSquarePoint(double longitude,
                                                               double latitude, double distance) {
        Map<String, double[]> squareMap = new HashMap<String, double[]>();
        // 计算经度弧度,从弧度转换为角度
        double dLongitude = 2 * (Math.asin(Math.sin(distance
                / (2 * EARTH_RADIUS))
                / Math.cos(Math.toRadians(latitude))));
        dLongitude = Math.toDegrees(dLongitude);
        // 计算纬度角度
        double dLatitude = distance / EARTH_RADIUS;
        dLatitude = Math.toDegrees(dLatitude);
        // 正方形
        double[] leftTopPoint = { latitude + dLatitude, longitude - dLongitude };
        double[] rightTopPoint = { latitude + dLatitude, longitude + dLongitude };
        double[] leftBottomPoint = { latitude - dLatitude,
                longitude - dLongitude };
        double[] rightBottomPoint = { latitude - dLatitude,
                longitude + dLongitude };
        squareMap.put("leftTopPoint", leftTopPoint);
        squareMap.put("rightTopPoint", rightTopPoint);
        squareMap.put("leftBottomPoint", leftBottomPoint);
        squareMap.put("rightBottomPoint", rightBottomPoint);
        return squareMap;
    }

}
