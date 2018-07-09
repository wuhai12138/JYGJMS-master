package com.summ.controller.customer;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JCustomer;
import com.summ.model.JCustomerStatment;
import com.summ.model.JShop;
import com.summ.model.others.GetWXResult;
import com.summ.model.others.WeiXinSign;
import com.summ.model.response.ALIPayResult;
import com.summ.model.response.CustomerRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.*;
import com.sun.deploy.nativesandbox.comm.Request;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 2.0系统的客户充值接口(目前在3.0项目中)
 * Created by summ on 18/1/18.
 */
@Controller
public class CustomerRechargeController extends AutoMapperController {


    @ResponseBody
    @RequestMapping("/customer/charge")
    public Object recharge2(@RequestBody Map map, ServletRequest request) {
        String rechargeMoney = (String) map.get("money");
        Integer customerId = (Integer) map.get("customerId");
        Integer chargeWay = (Integer) map.get("chargeWay");
        Integer adminId = (Integer) map.get("adminId");
        String remark = (String) map.get("remark");

        //支付宝支付
        if (chargeWay == Consts.zhifubao) {
            /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
             *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
             */
            Configs.init("zfbinfo.properties");

            /** 使用Configs提供的默认参数
             *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
             */
            AlipayUtil.tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

            // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
            AlipayUtil.tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

            /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
            /**monitorService = new AlipayMonitorServiceImpl.ClientBuilder().setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK").setFormat("json").build();*/

            AlipayF2FPrecreateResult result = AlipayUtil.test_trade_precreate2("家有管家--扫码充值", OrderUtil.generateStamentNumber(customerId), rechargeMoney, customerId, remark, adminId.toString(), "", Consts.testServerUrl + "/aliPay/callback");

            switch (result.getTradeStatus()) {
                case SUCCESS:
                    System.out.println("支付宝预下单成功: ");

                    AlipayTradePrecreateResponse response = result.getResponse();

                    return new ModelRes(ModelRes.Status.SUCCESS, "success !", response.getQrCode());

                case FAILED:
                    System.out.println("支付宝预下单失败!!!");
                    return new ModelRes(ModelRes.Status.FAILED, "支付宝预下单失败 !", null);

                case UNKNOWN:
                    System.out.println("系统异常，预下单状态未知!!!");
                    return new ModelRes(ModelRes.Status.FAILED, " 系统异常，预下单状态未知!", null);

                default:
                    System.out.println("不支持的交易状态，交易返回异常!!");
                    return new ModelRes(ModelRes.Status.FAILED, "不支持的交易状态，交易返回异常 !", null);
            }

        }else if (chargeWay == Consts.weixin){
            //微信充值
            final String nonceStr = (System.currentTimeMillis() / 1000) + "";
            String responseStr = null;
            Map<String, String> paramMap = new HashMap<String, String>();

            //appid：每个公众号都有一个appid
            paramMap.put("appid", WeixinUtil.APP_ID);
            //商户号：开通微信支付后分配
            paramMap.put("mch_id",WeixinUtil.MCH_ID);
            //随机数
            paramMap.put("nonce_str", nonceStr);
            //商品描述
            paramMap.put("body", "家有官家--扫码充值");
            //附加数据
            paramMap.put("attach", customerId+"-"+remark+"-"+adminId);
            //商户订单号
            paramMap.put("out_trade_no", OrderUtil.generateStamentNumber(customerId));
            //金额必须为整数  单位为分
            paramMap.put("total_fee", StringUtil.DoubleToAmountString(Double.valueOf(rechargeMoney)*100,-1));
            //本机的Ip
            paramMap.put("spbill_create_ip", "192.168.1.31");
            //支付成功后，回调地址
            paramMap.put("notify_url", Consts.testServerUrl + "/WeiXin/callback");
            //交易类型
            paramMap.put("trade_type", "NATIVE");

            System.out.println("paramMap>>>>>>>>>>>>>>"+paramMap);

            //调用签名函数生成签名
            String result = sign(paramMap);

            //根据微信签名规则，生成签名。随机参数可以在商户后台管理系统中进行设置。
            paramMap.put("sign", result);
            //把参数转换成XML数据格式
            StringBuffer stringBuffer = new StringBuffer("<xml>");
            WeixinUtil.mapToXML(paramMap, stringBuffer);
            stringBuffer.append("</xml>");
            System.out.println("微信提交xml: \n" + stringBuffer.toString());

            okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.parse("utf-8"), stringBuffer.toString());
            okhttp3.Request okRequest = new okhttp3.Request.Builder().addHeader("Content-Type", "text/xml").url(WeixinUtil.URL).post(requestBody).build();


            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                Response response = okHttpClient.newCall(okRequest).execute();
                responseStr = response.body().string();

                WeiXinSign weiXinSign = (WeiXinSign) JsonUtil.getObjectFromXML(responseStr,WeiXinSign.class);


                Map<String,String> signMap = new HashMap<String, String>();
                signMap.put("appid",weiXinSign.getAppid());
                signMap.put("partnerid",WeixinUtil.MCH_ID);
                signMap.put("prepayid",weiXinSign.getPrepay_id());
                signMap.put("package",WeixinUtil.PACKAGE_VALUE);
                signMap.put("noncestr",weiXinSign.getNonce_str());
                signMap.put("timestamp",nonceStr);
                signMap.put("trade_type",weiXinSign.getTrade_type());
                signMap.put("code_url",weiXinSign.getCode_url());
                String sign = sign(signMap);

                weiXinSign.setPackage_value(WeixinUtil.PACKAGE_VALUE);
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
        else {
            return new ModelRes(ModelRes.Status.FAILED, "不支持当前支付方式 !", null);
        }

    }

    /**
     * 支付宝充值回调
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/aliPay/callback", method = RequestMethod.POST)
    public String AliPayResult(HttpServletRequest request) throws Exception {
        Map requestParams = request.getParameterMap();
        System.out.println("Aly Callback data Map:" + JsonUtil.toJson(requestParams));

        ALIPayResult aliPayResult = JsonUtil.json2Bean(JsonUtil.toJson(requestParams), ALIPayResult.class);

        if (request.getParameter("trade_status") != null && request.getParameter("trade_status").equals("TRADE_SUCCESS")) {

            List<String> ss = Arrays.asList(aliPayResult.getBody().get(0).split("-"));
            Map map = new HashMap();
            String customer_id = ss.get(0);
            String admin_id = ss.get(2);
            String charge_backup = ss.get(1);
            String charge_money = aliPayResult.getBuyer_pay_amount().get(0);
            String charge_way = String.valueOf(1);
            String charge_no = aliPayResult.getTrade_no().get(0);
            String charge_channel = String.valueOf(1);
            map.put("admin_id",admin_id);
            map.put("customer_id",customer_id);
            map.put("charge_backup",charge_backup);
            map.put("charge_money",charge_money);
            map.put("charge_way",charge_way);
            map.put("charge_no",charge_no);
            map.put("charge_channel",charge_channel);


            String url = "http://www.jyguanjia.com:8080/jyguanjia/recharge?" +
                    "admin_id="+admin_id+"&customer_id="+customer_id+"&charge_backup="+charge_backup+"&charge_money="+charge_money+"&charge_no="+charge_no+"&charge_way="+charge_way+"&charge_channel="+charge_channel;
//            String url = "http://www.jyguanjia.com:8081/jyguanjia/recharge?"+"admin_id="+admin_id+"&customer_id="+customer_id+"&charge_channel=1";
            System.out.println("请求2.0接口链接为："+ url);
//            RequestUtil.doPost(url,map.toString());
            RequestUtil.doGet(url);

            return "PayResult";
        } else {
            return "error";
        }
    }

    /**
     * 微信充值回调
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/WeiXin/callback", method = RequestMethod.POST)
    public String WeiXinResult(HttpServletRequest request) throws Exception {
        String inputLine;
        StringBuffer notifyXml = new StringBuffer();
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notifyXml.append(inputLine);
                System.out.println("");
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("notifyXml:" + notifyXml.toString());
        GetWXResult get = (GetWXResult) JsonUtil.getObjectFromXML(notifyXml.toString(), GetWXResult.class);
        System.out.println("notifyObj:" + get.toString());
        if (get.getResult_code().equals("SUCCESS")) {

            List<String> ss = Arrays.asList(get.getAttach().split("-"));
            String customer_id = ss.get(0);
            String admin_id = ss.get(2);
            String charge_backup = ss.get(1);
            String charge_money = String.valueOf(Double.valueOf(get.getTotal_fee()) / 100);
            String charge_way = String.valueOf(2);
            String charge_no = get.getTransaction_id();
            String charge_channel = String.valueOf(1);

            /**判断此次支付是否已生成过对账单*/
            String res = RequestUtil.doGet("http://www.jyguanjia.com:8080/jyguanjia/checkChargeNo?"+"customer_id"+customer_id+"checkChargeNo"+charge_no);
            System.out.println("判断此次支付是否已生成过对账单:"+res.toString());
            ModelRes obj = JsonUtil.json2Bean(res,ModelRes.class);
            if (obj.getStatus()==200){

                /**新增对账单*/
                Map map = new HashMap();
                map.put("admin_id",admin_id);
                map.put("customer_id",customer_id);
                map.put("charge_backup",charge_backup);
                map.put("charge_money",charge_money);
                map.put("charge_way",charge_way);
                map.put("charge_no",charge_no);
                map.put("charge_channel",charge_channel);

                String url = "http://www.jyguanjia.com:8080/jyguanjia/recharge?" +
                        "admin_id="+admin_id+"&customer_id="+customer_id+"&charge_backup="+charge_backup+"&charge_money="+charge_money+"&charge_no="+charge_no+"&charge_way="+charge_way+"&charge_channel="+charge_channel;

                HttpUtil.getInstance().setUrl(url).setCallBack(new HttpUtil.HttpListener()).sender();
//                RequestUtil.doGet(url);
            }
            return "PayResult";

        }
        return "error";
    }

    public String sign(Map<String, String> paramMap){
        //生成签名
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
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
        System.out.println("resultStringBuilder》》》》》》》》》" + resultStringBuilder);
        String result = resultStringBuilder.toString();
        result += "key=" + WeixinUtil.API_KEY;
        System.out.println("result>>>>>>>>>>>>>>>" + result);
        result = MD5.MD5Encode(result,"UTF-8").toUpperCase();
        System.out.println("MD5result>>>>>>>>>>>>>>>" + result);
        return result;
    }



}
