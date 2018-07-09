package com.summ.utils;

//import com.dh.jygj.app.Constants;

import com.sun.deploy.net.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class AliUtil {
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String Ali_APPID = "2017022205811963";
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKhqTPPiOPzMGDbA\n" +
            "VxhfHXO6No2ctkfe8wraj7QgGVm6VNWrhoam/XlT2wbpO1Gamb5EjsKbni66crht\n" +
            "JudxtauZ/otbBpdhzvyVtvrxS3q278pkACr44ZEO/IOzV2rQukDqpJjT94CWs/dL\n" +
            "nC/uCQLNsQ38mTgPmYyh/5x6cbTnAgMBAAECgYAAh4UCZz2incmdsnETpIjlKjqt\n" +
            "q0ZvZ2YALLuy5z7tJ0bI/ScJGVFGplrT5LvI/+Fn1bNVLUQMBY4CwmUS6SqXBDCI\n" +
            "ROK9qnuszctLunDBm4D81/9sWwtzTxLm6VlxcEVnofTn3gtxZjytjbj74Hok2fM4\n" +
            "SrSNAhkWxFHfkwCcYQJBANbquXbVzaMMjMZw9iKqmTlXZQIVP0nm4+XXqCHWZys0\n" +
            "MAqgspwfbmsVntX3iNah58RYN29iD3zhN3icJ1yX19kCQQDIm/Ls4JGLYmQKzNNt\n" +
            "GB9Noefi1BzPh+go4cTjFSlIg7M40mjD4rQfGGRjk8CdlynVEHW2a7VvzaB1pDXk\n" +
            "dLq/AkARf5sKKe5MUEFGBvyDZhCDsrHusXwtsDbuB9kQqDxOMDmZI+xm+cgrv97m\n" +
            "3PivQQ911RjuYLWSHWQ+1bf+uf5BAkBjLTYcBanuzuXGsRyVgqd3KeQKL/NOZlJQ\n" +
            "Y82CtqOCRhoErYE91oZWGSkIUbOYNXNo8oo/dWBttarufhBmsY7hAkB7W85Bkvva\n" +
            "cB+pMpxLeyMV9RJvlOYXyohfa+fGMTiOi8W1HT/bAwtODaD3j1ueDh15YMyJlIYz\n" +
            "eKk/FQm5mSlw";
    public static final int SDK_PAY_FLAG = 1;

    /**
     * 构造授权参数列表
     *
     * @param pid
     * @param app_id
     * @param target_id
     * @return
     */
    public static Map<String, String> buildAuthInfoMap(String pid, String app_id, String target_id, boolean rsa2) {
        Map<String, String> keyValues = new HashMap<String, String>();

        // 商户签约拿到的app_id，如：2013081700024223
        keyValues.put("app_id", app_id);

        // 商户签约拿到的pid，如：2088102123816631
        keyValues.put("pid", pid);

        // 服务接口名称， 固定值
        keyValues.put("apiname", "com.alipay.account.auth");

        // 商户类型标识， 固定值
        keyValues.put("app_name", "mc");

        // 业务类型， 固定值
        keyValues.put("biz_type", "openservice");

        // 产品码， 固定值
        keyValues.put("product_id", "APP_FAST_LOGIN");

        // 授权范围， 固定值
        keyValues.put("scope", "kuaijie");

        // 商户唯一标识，如：kkkkk091125
        keyValues.put("target_id", target_id);

        // 授权类型， 固定值
        keyValues.put("auth_type", "AUTHACCOUNT");

        // 签名类型
        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

        return keyValues;
    }

    /**
     * 构造支付订单参数列表
     *
     * @param app_id
     * @param timestamp    订单创建时间
     * @param total_amount 订单金额
     * @param subject      余额充值
     * @param body         填用户ID
     * @return
     */
    public static Map<String, String> buildOrderParamMap(String app_id, String timestamp, String total_amount, String subject, String body) {
        Map<String, String> keyValues = new HashMap<String, String>();

        keyValues.put("app_id", app_id);

        keyValues.put("method", "alipay.trade.app.pay");

        keyValues.put("charset", "utf-8");

        keyValues.put("sign_type", "RSA");

//        keyValues.put("sign", rsa2 ? "RSA2" : "RSA");

//        keyValues.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"我是测试数据\",\"out_trade_no\":\"" + getOutTradeNo() + "\"}");
        keyValues.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"" + total_amount + "\",\"subject\":\"" + subject + "\",\"body\":\"" + body + "\",\"out_trade_no\":\"" + getOutTradeNo() + "\"}");


        keyValues.put("timestamp", timestamp);

        keyValues.put("version", "1.0");

        keyValues.put("notify_url", "aliNotifyUrl");
//        keyValues.put("notify_url", "http://112.74.182.69:8081/jyguanjia/PayResult");

        return keyValues;
    }

    /**
     * 构造支付订单参数信息
     *
     * @param map 支付订单参数
     * @return
     */
    public static String buildOrderParam(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }

    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * 对支付参数信息进行签名
     *
     * @param map 待签名授权信息
     * @return
     */
    public static String getSign(Map<String, String> map, String rsaKey, boolean rsa2) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

        String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
        String encodedSign = "";

        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "sign=" + encodedSign;
    }

    /**
     * 要求外部订单号必须唯一。
     *
     * @return
     */
    public static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * 银行卡三要素验证
     *
     */
    public static String signFourPoint(String name,String cardNum,String idCard,String phoneNum){
        String host = "https://ali-bankcard4.showapi.com";
        String path = "/bank4";
        String method = "GET";
        String appCode = "cff51c48e7694a8f87da5bf92464a6ac";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("acct_name", name);
        querys.put("acct_pan", cardNum);
        querys.put("cert_id", idCard);
        querys.put("cert_type", "01");
        querys.put("needBelongArea", "true");
        querys.put("phone_num", phoneNum);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = com.summ.utils.HttpUtils.doGet(host, path, method, headers, querys);
            String string = EntityUtils.toString(response.getEntity());
            System.out.println("回执2》》》》》》》》》"+string);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

}
