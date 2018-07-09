package com.summ.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.csvreader.CsvReader;
import com.summ.Consts;
import com.summ.model.JCustomerAlyStatment;
import com.summ.model.others.AliPayDownloadObj;
import com.summ.model.others.PayCallBackObj;
import okhttp3.OkHttpClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.zeroturnaround.zip.NameMapper;
import org.zeroturnaround.zip.ZipUtil;

import javax.security.auth.Subject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class AlipayUtil {
    /**
     * 支付宝当面付2.0服务
     */
    public static AlipayTradeService tradeService;

    /**
     * 支付宝当面付2.0服务（集成了交易保障接口逻辑）
     */
    public static AlipayTradeService tradeWithHBService;

    /**
     * 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
     */
    public static AlipayMonitorService monitorService;

    /**
     * 当面付2.0生成支付二维码
     *
     * @param rechargeMoney
     * @return
     */
    public static AlipayF2FPrecreateResult test_trade_precreate(String subject, String outTradeNo, String rechargeMoney, PayCallBackObj payCallBackObj, String callbackUrl) {

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        //String outTradeNo

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        //String subject = "家有官家--扫码充值";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = rechargeMoney;

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "购买商品共" + rechargeMoney + "元";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "1";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        long money = (long) (StringUtil.parseDouble(rechargeMoney) * 100);
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "家有官家--扫码充值", money, 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setTimeoutExpress(timeoutExpress)
                .setStoreId(storeId)
                .setNotifyUrl(callbackUrl)//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList)
                .setBody(payCallBackObj.toString());

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        return result;
    }

    public static AlipayF2FPrecreateResult test_trade_precreate2(String subject, String outTradeNo, String rechargeMoney, Integer customerId, String remark, String adminId, String shopName, String callbackUrl) {

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        //String outTradeNo

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        //String subject = "家有官家--扫码充值";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = rechargeMoney;

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "购买商品共" + rechargeMoney + "元";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "1";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        long money = (long) (StringUtil.parseDouble(rechargeMoney) * 100);
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "家有官家--扫码充值", money, 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setTimeoutExpress(timeoutExpress)
                .setStoreId(storeId)
                .setNotifyUrl(callbackUrl)//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList)
                .setBody(customerId + "-" + remark + "-" + adminId);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        return result;
    }

    public static Map<String,List<JCustomerAlyStatment>> getTradeByDate(String date) {
        Map<String,List<JCustomerAlyStatment>> map = new HashMap<>();

        Properties properties = getProperties();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", properties.getProperty("appid"),
                properties.getProperty("private_key"), "json", "utf-8", properties.getProperty("alipay_public_key"), "RSA2");
        //创建API对应的request类
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        //date格式2016-04-05
        request.setBizContent("{" +
                "    \"bill_type\":\"trade\"," +
                "    \"bill_date\":\"" + date + "\"}"); //设置业务参数
        //通过alipayClient调用API，获得对应的response类
        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.print("获取支付宝对账单下载链接:"+response.getBody());
            AliPayDownloadObj aliPayDownloadObj = JsonUtil.json2Bean(response.getBody(),AliPayDownloadObj.class);
            //根据response中的结果继续业务逻辑处理
            //将接口返回的对账单下载地址传入urlStr
            /**下载对账单并存储压缩文件*/
            String urlStr = aliPayDownloadObj.getAlipay_data_dataservice_bill_downloadurl_query_response().getBill_download_url();
            //将接口返回的对账单下载地址传入urlStr
            /**指定希望保存的文件路径*/
            String filePath = Consts.localPath+date+".zip";
            /**清空文件夹*/
            deleteDir(Consts.localPath);
            URL url = null;
            HttpURLConnection httpUrlConnection = null;
            InputStream fis = null;
            FileOutputStream fos = null;
            try {
                url = new URL(urlStr);
                httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setConnectTimeout(5 * 1000);
                httpUrlConnection.setDoInput(true);
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setUseCaches(false);
                httpUrlConnection.setRequestMethod("GET");
                httpUrlConnection.setRequestProperty("Charsert", "UTF-8");
                httpUrlConnection.connect();
                fis = httpUrlConnection.getInputStream();
                byte[] temp = new byte[1024];
                int b;
                fos = new FileOutputStream(new File(filePath));
                while ((b = fis.read(temp)) != -1) {
                    fos.write(temp, 0, b);
                    fos.flush();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(fis!=null) {
                        fis.close();
                    }
                    if(fos!=null) {
                        fos.close();
                    }
                    if(httpUrlConnection!=null) {
                        httpUrlConnection.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /**解压*/
            File file = new File(filePath);
            ZipUtil.unpack((file), new File(Consts.localPath+date+"/"), new NameMapper() {

                @Override
                public String map(String s) {
                    System.out.println(s);
                    if (!s.contains("汇总")) {
                        return s;
                    }else{
                        return null;
                    }
                }
            }, Charset.forName("gbk"));

            map = parseExcel(Consts.localPath+date+"/");

        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return map;
        }
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private static Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/zfbinfo.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Map<String,List<JCustomerAlyStatment>> parseExcel(String path) throws Exception {

        Map<String,List<JCustomerAlyStatment>> resMap = new HashMap<>();
        File file =new File(path);



        List<JCustomerAlyStatment> ls = new ArrayList<>();
        CsvReader reader = new CsvReader(file.listFiles()[0].getAbsolutePath(), ',', Charset.forName("GBK"));
        // 跳过表头 如果需要表头的话，这句可以忽略
        reader.readHeaders();
        // 逐行读入除表头的数据
        ArrayList<String[]> csvFileList = new ArrayList<>();
        while (reader.readRecord()) {
            csvFileList.add(reader.getValues());
        }
        reader.close();

        System.out.println("excel data>>>>>" +JsonUtil.toJson(csvFileList));
        List<JCustomerAlyStatment> jCustomerAlyStatmentList = new ArrayList<>();
        List<JCustomerAlyStatment> jCustomerAlyStatmentListForInsert = new ArrayList<>();
        // 遍历读取的CSV文件
        for (int row = 4; row < csvFileList.size(); row++) {
            // 取得第row行第0列的数据
            if (csvFileList.get(row).length > 12) {
                for (Integer i =0;i< csvFileList.get(row).length;i++){
                    System.out.println("------------>" +csvFileList.get(row)[i]);
                }
                JCustomerAlyStatment jCustomerAlyStatment = new JCustomerAlyStatment(csvFileList.get(row)[0],csvFileList.get(row)[1],csvFileList.get(row)[2],csvFileList.get(row)[3],DateUtil.parseStringToDate(csvFileList.get(row)[4],DateUtil.PATTERN_yyyy_MM_dd_HHmmss),
                        DateUtil.parseStringToDate(csvFileList.get(row)[5],DateUtil.PATTERN_yyyy_MM_dd_HHmmss),csvFileList.get(row)[6],StringUtil.parseInt(csvFileList.get(row)[7]),csvFileList.get(row)[8],csvFileList.get(row)[9],
                        csvFileList.get(row)[10],new BigDecimal(csvFileList.get(row)[11]),new BigDecimal(csvFileList.get(row)[12]),new BigDecimal(csvFileList.get(row)[13]),new BigDecimal(csvFileList.get(row)[14]),
                        new BigDecimal(csvFileList.get(row)[15]),new BigDecimal(csvFileList.get(row)[16]),new BigDecimal(csvFileList.get(row)[17]),csvFileList.get(row)[18],new BigDecimal(csvFileList.get(row)[19]),
                        new BigDecimal(csvFileList.get(row)[20]),csvFileList.get(row)[21],new BigDecimal(csvFileList.get(row)[22]),new BigDecimal(csvFileList.get(row)[23]),csvFileList.get(row)[24]);
                jCustomerAlyStatmentListForInsert.add(jCustomerAlyStatment);
                JCustomerAlyStatment jCustomerAlyStatment1 = new JCustomerAlyStatment(csvFileList.get(row)[0],csvFileList.get(row)[1],csvFileList.get(row)[2],csvFileList.get(row)[3],DateUtil.parseStringToDate(csvFileList.get(row)[4],DateUtil.PATTERN_yyyy_MM_dd_HHmmss),
                        DateUtil.parseStringToDate(csvFileList.get(row)[5],DateUtil.PATTERN_yyyy_MM_dd_HHmmss),csvFileList.get(row)[6],StringUtil.parseInt(csvFileList.get(row)[7]),csvFileList.get(row)[8],csvFileList.get(row)[9],
                        csvFileList.get(row)[10],new BigDecimal(csvFileList.get(row)[11]),new BigDecimal(csvFileList.get(row)[12]),new BigDecimal(csvFileList.get(row)[13]),new BigDecimal(csvFileList.get(row)[14]),
                        new BigDecimal(csvFileList.get(row)[15]),new BigDecimal(csvFileList.get(row)[16]),new BigDecimal(csvFileList.get(row)[17]),csvFileList.get(row)[18],new BigDecimal(csvFileList.get(row)[19]),
                        new BigDecimal(csvFileList.get(row)[20]),csvFileList.get(row)[21],new BigDecimal(csvFileList.get(row)[22]),new BigDecimal(csvFileList.get(row)[23]),csvFileList.get(row)[24],"未对账");
                jCustomerAlyStatmentList.add(jCustomerAlyStatment1);
            }

        }
        resMap.put("jCustomerAlyStatmentList",jCustomerAlyStatmentList);
        resMap.put("jCustomerAlyStatmentListForInsert",jCustomerAlyStatmentListForInsert);

        return resMap;
    }

    public static boolean deleteDir(String path){
        File file = new File(path);
        if(!file.exists()){//判断是否待删除目录是否存在
            System.err.println("The dir are not exists!");
            return false;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for(String name : content){
            File temp = new File(path, name);
            if(temp.isDirectory()){//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            }else{
                if(!temp.delete()){//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }
        return true;
    }
}
