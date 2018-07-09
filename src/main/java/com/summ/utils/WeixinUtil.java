package com.summ.utils;

import com.summ.Consts;
import com.summ.model.JCustomerWeiXinStatment;
import com.summ.model.others.PayCallBackObj;
import com.summ.model.others.WeiXinSign;
import com.summ.model.response.ModelRes;
import com.thoughtworks.xstream.XStream;
import okhttp3.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Mc on 2016/9/18.
 */

public class WeixinUtil {


    public static final String Base_URL = "http://112.74.182.69:8082/customer/";
    public static String APP_ID = "wx1024cb31dfbae7cc";
    public static String MCH_ID = "1488511392";//商户号
    public static String PACKAGE_VALUE = "Sign=WXPay";//packageValue
    public static String API_KEY = "beaaa56c4ad04744391d322ff2884db8";
    public static final String wxNotifyUrl = Base_URL + "WeiXinPayResult";//微信回调接口
    public static final String prepayId = "https://api.mch.weixin.qq.com/pay/unifiedorder";//微信获取prepayid
    public static final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String ORDER_QUERY_URL="https://api.mch.weixin.qq.com/pay/downloadbill";


    /**
     * 微信面对面支付生成二维码
     * @param rechargeMoney
     * @param callBackUrl
     * @return
     */
    public static Object weiXinQrcodePay(String outTradeNo, String rechargeMoney, String callBackUrl, PayCallBackObj payCallBackObj, String body) {
        final String nonceStr = (System.currentTimeMillis() / 1000) + "";
        String responseStr = null;
        Map<String, String> paramMap = new HashMap<String, String>();

        //appid：每个公众号都有一个appid
        paramMap.put("appid", APP_ID);
        //商户号：开通微信支付后分配
        paramMap.put("mch_id", MCH_ID);
        //随机数
        paramMap.put("nonce_str", nonceStr);
        //商品描述
        paramMap.put("body", body);
        //附加数据
        paramMap.put("attach", payCallBackObj.toString());
        //商户订单号
        paramMap.put("out_trade_no", outTradeNo);
        //金额必须为整数  单位为分
        paramMap.put("total_fee", StringUtil.DoubleToAmountString(Double.valueOf(rechargeMoney) * 100, -1));
        //本机的Ip
        paramMap.put("spbill_create_ip", "192.168.1.31");
        //支付成功后，回调地址
        paramMap.put("notify_url", callBackUrl);
        //交易类型
        paramMap.put("trade_type", "NATIVE");

        System.out.println("paramMap>>>>>>>>>>>>>>" + paramMap);

        //调用签名函数生成签名
        String result = WeixinUtil.getSign(paramMap);

        //根据微信签名规则，生成签名。随机参数可以在商户后台管理系统中进行设置。
        paramMap.put("sign", result);
        //把参数转换成XML数据格式
        StringBuffer stringBuffer = new StringBuffer("<xml>");
        WeixinUtil.mapToXML(paramMap, stringBuffer);
        stringBuffer.append("</xml>");
        System.out.println("微信提交xml: \n" + stringBuffer.toString());

        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.parse("utf-8"), stringBuffer.toString());
        okhttp3.Request okRequest = new okhttp3.Request.Builder().addHeader("Content-Type", "text/xml").url(URL).post(requestBody).build();


        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(okRequest).execute();
            responseStr = response.body().string();

            WeiXinSign weiXinSign = (WeiXinSign) JsonUtil.getObjectFromXML(responseStr, WeiXinSign.class);


            Map<String, String> signMap = new HashMap<String, String>();
            signMap.put("appid", weiXinSign.getAppid());
            signMap.put("partnerid", MCH_ID);
            signMap.put("prepayid", weiXinSign.getPrepay_id());
            signMap.put("package", PACKAGE_VALUE);
            signMap.put("noncestr", weiXinSign.getNonce_str());
            signMap.put("timestamp", nonceStr);
            signMap.put("trade_type", weiXinSign.getTrade_type());
            signMap.put("code_url", weiXinSign.getCode_url());
            String sign = WeixinUtil.getSign(signMap);

            weiXinSign.setPackage_value(PACKAGE_VALUE);
            weiXinSign.setTimestamp(nonceStr);
            weiXinSign.setSign(sign);
            System.out.println("weiXinSign>>>>>>>>>>>>>>>" + weiXinSign.toString());
            return new ModelRes(ModelRes.Status.SUCCESS, "获取签名订单信息成功", weiXinSign.getCode_url());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("支付失败信息>>>>>>>>>>>>>>>" + responseStr);
            return new ModelRes(ModelRes.Status.ERROR, "获取签名订单信息失败", null);
        }
    }

    /**
     * 要求外部订单号必须唯一。
     * Z
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


    public static String getSign(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            resultStringBuilder.append(arrayToSort[i]);
        }
        System.out.println("resultStringBuilder》》》》》》" + resultStringBuilder);
        String result = resultStringBuilder.toString();
        result += "key=" + API_KEY;
        System.out.println("result>>>>>>>>>>>>>>>" + result);
        result = MD5.MD5Encode(result, "UTF-8").toUpperCase();
        System.out.println("MD5result>>>>>>>>>>>>>>>" + result);
        return result;
    }

    public static Object getObjectFromXML(String xml, Class tClass) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
    }

    /**
     * Map集合转换xml文档
     */
    public static String mapToXml(HashMap<String, String> paramMap) {
        String xml = "";
        try {
            XStream xstream = new XStream();
            xml = xstream.toXML(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return xml;
    }

    public static void mapToXML(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value) {
                value = "";
            }
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML(hm, sb);
                }
                sb.append("</" + key + ">\n");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + ">" + value + "</" + key + ">");
                }

            }

        }
    }


    /**
     * 获取微信支付链接
     *
     * @param price 最终支付价格
     */
    public static String getPrepayId(String price, int customerId, String nonceStr, String shopName) {
        String responseStr = null;
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", APP_ID); //appid：每个公众号都有一个appid
        paramMap.put("mch_id", MCH_ID); //商户号：开通微信支付后分配
        paramMap.put("nonce_str", nonceStr);//随机数
        paramMap.put("body", "家有管家-余额充值"); //商品描述
        paramMap.put("attach", customerId + "-" + shopName); //附加数据
        paramMap.put("out_trade_no", getOutTradeNo() + "");//商户订单号
        paramMap.put("total_fee", price); //金额必须为整数  单位为分
        paramMap.put("spbill_create_ip", "192.168.1.31"); //本机的Ip
        paramMap.put("notify_url", wxNotifyUrl); //支付成功后，回调地址
        paramMap.put("trade_type", "APP"); //交易类型
        paramMap.put("sign", WeixinUtil.getSign(paramMap));//根据微信签名规则，生成签名。随机参数可以在商户后台管理系统中进行设置。
        StringBuffer sb = new StringBuffer("<xml>");//把参数转换成XML数据格式
        WeixinUtil.mapToXML(paramMap, sb);
        sb.append("</xml>");
        System.out.println("微信提交xml: \n" + sb.toString());

        RequestBody requestBody = RequestBody.create(MediaType.parse("utf-8"), sb.toString());
        Request request = new Request.Builder().addHeader("Content-Type", "text/xml").url(prepayId).post(requestBody).build();


        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            responseStr = response.body().string();
            System.out.println("weixin return： " + responseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return responseStr;

    }

    /**
     * 微信获取订单信息
     * @param bill_date
     * @return
     */
    public static Map<String,List<JCustomerWeiXinStatment>>  weiXinOrderQuery(String bill_date) {
        Map<String,List<JCustomerWeiXinStatment>> resMap = new HashMap();
        final String nonceStr = (System.currentTimeMillis() / 1000) + "";
        String responseStr = null;
        Map<String, String> paramMap = new HashMap<String, String>();

        //appid：每个公众号都有一个appid
        paramMap.put("appid", APP_ID);
        //商户号：开通微信支付后分配
        paramMap.put("mch_id", MCH_ID);
        //随机数
        paramMap.put("nonce_str", nonceStr);
        //对账单日期
        paramMap.put("bill_date", bill_date);
        //账单类型
        paramMap.put("bill_type", "ALL");

        System.out.println("paramMap>>>>>>>>>>>>>>" + paramMap);

        //调用签名函数生成签名
        String result = WeixinUtil.getSign(paramMap);

        //根据微信签名规则，生成签名。随机参数可以在商户后台管理系统中进行设置。
        paramMap.put("sign", result);
        //把参数转换成XML数据格式
        StringBuffer stringBuffer = new StringBuffer("<xml>");
        WeixinUtil.mapToXML(paramMap, stringBuffer);
        stringBuffer.append("</xml>");
        System.out.println("微信提交xml: \n" + stringBuffer.toString());

        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.parse("utf-8"), stringBuffer.toString());
        okhttp3.Request okRequest = new okhttp3.Request.Builder().addHeader("Content-Type", "text/xml").url(ORDER_QUERY_URL).post(requestBody).build();


        OkHttpClient okHttpClient = new OkHttpClient();
        List<JCustomerWeiXinStatment> jCustomerWeiXinStatmentList = new ArrayList<>();
        List<JCustomerWeiXinStatment> jCustomerWeiXinStatmentListForInsert = new ArrayList<>();
        try {
            Response response = okHttpClient.newCall(okRequest).execute();
            //获取订单信息
            responseStr = response.body().string();
            System.out.println("responseStr: \n" + responseStr);
            if(!responseStr.contains("`")){
                return resMap;
            }
            //返回结果示例： ﻿交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,企业红包金额,微信退款单号,商户退款单号,退款金额,企业红包退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率
            //`2018-05-07 10:54:45,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000113201805074559498485,`152566112349603496538,`oOmPo0-gjIhFsT3WRFvL5l_iOtok,`NATIVE,`REFUND,`CFT,`CNY,`0.00,`0.00,`50000506642018050704485755976,`484316432018050745594984851525661661,`0.02,`0.00,`ORIGINAL,`SUCCESS,`家有官家--扫码充值,`3496--32,`0.00000,`0.60%
            //`2018-05-07 10:45:37,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000113201805074559498485,`152566112349603496538,`oOmPo0-gjIhFsT3WRFvL5l_iOtok,`NATIVE,`SUCCESS,`CFT,`CNY,`0.02,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`3496--32,`0.00000,`0.60%
            //`2018-05-07 10:50:09,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000120201805074525206991,`1525661389973034968085,`oOmPo0-gjIhFsT3WRFvL5l_iOtok,`NATIVE,`SUCCESS,`CFT,`CNY,`0.03,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`3496--32,`0.00000,`0.60%
            //`2018-05-07 17:08:34,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000121201805074782619515,`050717082815022,`oOmPo01XvctZOVPBB18wO0yrjVO4,`APP,`SUCCESS,`CFT,`CNY,`1000.00,`0.00,`0,`0,`0.00,`0.00,`,`,`余额充值,`2414-何嘉-13122828856-长寿路店,`6.00000,`0.60%
            //`2018-05-07 09:15:36,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000124201805074008280074,`152565572473204622142,`oOmPo0xSwKp0ThSMG5tyut3xW008,`NATIVE,`SUCCESS,`CFT,`CNY,`400.00,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`4622--161,`2.40000,`0.60%
            //`2018-05-07 09:27:25,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000126201805074357341528,`1525656429469008589548,`oOmPo0zBBc6qttMNAKUv0muubK9Y,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`858--34,`0.00000,`0.60%
            //`2018-05-07 10:41:09,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000129201805074414966070,`1525660847944034968827,`oOmPo0-gjIhFsT3WRFvL5l_iOtok,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`3496--32,`0.00000,`0.60%
            //`2018-05-07 09:28:27,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000129201805074505137945,`1525656496782046222532,`oOmPo0xSwKp0ThSMG5tyut3xW008,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`4622--161,`0.00000,`0.60%
            //`2018-05-07 09:21:46,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000130201805074448612378,`1525656090484008234429,`oOmPo0zBBc6qttMNAKUv0muubK9Y,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`823--34,`0.00000,`0.60%
            //`2018-05-07 10:38:47,`wx1024cb31dfbae7cc,`1488511392,`0,`,`4200000132201805074503706681,`1525660710228010479040,`oOmPo0yIRnnXO4TqobyqiTSDKHfQ,`NATIVE,`SUCCESS,`CFT,`CNY,`1000.00,`0.00,`0,`0,`0.00,`0.00,`,`,`家有官家--扫码充值,`1047--144,`6.00000,`0.60%
            //总交易单数,总交易额,总退款金额,总企业红包退款金额,手续费总金额
            //`10,`2400.09,`0.02,`0.00,`14.40000

            //解析订单数据
            //把第一行表头去掉
            String tradeMsg = responseStr.substring(responseStr.indexOf("`"));
            System.out.println("tradeMsg: \n" + tradeMsg);
            //去掉汇总数据，并且去掉"`"这个符号
            String tradeInfo = tradeMsg.substring(0, tradeMsg.indexOf("总")).replace("`", "").replace("\r\n","");
            System.out.println("tradeInfo: \n" + tradeInfo);
            //用spilt方法拿出每一天数据放进数组里。之后再用spilt方法把数据放进二维数组里
            String[] tradeArray = tradeInfo.split("%");
            for (String tradeDetailInfo : tradeArray) {
                String[] tradeDetailArray = tradeDetailInfo.split(",");
                System.out.println("tradeDetailArray"+tradeDetailArray[0]);
                JCustomerWeiXinStatment jCustomerWeiXinStatment = new JCustomerWeiXinStatment(DateUtil.parseStringToDate(tradeDetailArray[0], DateUtil.PATTERN_yyyy_MM_dd_HHmmss),tradeDetailArray[1]
                        ,tradeDetailArray[2],tradeDetailArray[3],tradeDetailArray[4],tradeDetailArray[5],tradeDetailArray[6]
                        ,tradeDetailArray[7],tradeDetailArray[8],tradeDetailArray[9],tradeDetailArray[10],tradeDetailArray[11]
                        ,new BigDecimal(tradeDetailArray[12]),tradeDetailArray[13],tradeDetailArray[14],tradeDetailArray[15],tradeDetailArray[16]
                        ,new BigDecimal(tradeDetailArray[17]),tradeDetailArray[18],tradeDetailArray[19],tradeDetailArray[20],tradeDetailArray[21]
                        ,new BigDecimal(tradeDetailArray[22]),tradeDetailArray[23],"未对账");
                jCustomerWeiXinStatmentList.add(jCustomerWeiXinStatment);

                JCustomerWeiXinStatment jCustomerWeiXinStatment1 = new JCustomerWeiXinStatment(DateUtil.parseStringToDate(tradeDetailArray[0], DateUtil.PATTERN_yyyy_MM_dd_HHmmss),tradeDetailArray[1]
                        ,tradeDetailArray[2],tradeDetailArray[3],tradeDetailArray[4],tradeDetailArray[5],tradeDetailArray[6]
                        ,tradeDetailArray[7],tradeDetailArray[8],tradeDetailArray[9],tradeDetailArray[10],tradeDetailArray[11]
                        ,new BigDecimal(tradeDetailArray[12]),tradeDetailArray[13],tradeDetailArray[14],tradeDetailArray[15],tradeDetailArray[16]
                        ,new BigDecimal(tradeDetailArray[17]),tradeDetailArray[18],tradeDetailArray[19],tradeDetailArray[20],tradeDetailArray[21]
                        ,new BigDecimal(tradeDetailArray[22]),tradeDetailArray[23]);
                jCustomerWeiXinStatmentListForInsert.add(jCustomerWeiXinStatment1);

                resMap.put("jCustomerWeiXinStatmentList",jCustomerWeiXinStatmentList);
                resMap.put("jCustomerWeiXinStatmentListForInsert",jCustomerWeiXinStatmentListForInsert);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("支付失败信息>>>>>>>>>>>>>>>" + responseStr);
        } finally {
            return resMap;
        }
    }

}
