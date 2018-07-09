import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.show.api.ShowapiRequest;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JCustomerHouseMapper;
import com.summ.model.JCustomerAlyStatment;
import com.summ.model.JOrderContract;
import com.summ.model.JShop;
import com.summ.model.others.ShowApiRes;
import com.summ.model.response.TimeAndWeekRes;
import com.summ.utils.*;
import com.sun.deploy.net.HttpUtils;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.bson.Document;
import org.omg.PortableInterceptor.INACTIVE;
import org.zeroturnaround.zip.NameMapper;
import org.zeroturnaround.zip.ZipUtil;
import sun.net.www.http.HttpClient;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by summ on 17/11/17.
 */
public class DocumentTest extends AutoMapperController {

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
////        Long test = Long.valueOf(140737488355328) ;
//        BigDecimal bigDecimal = new BigDecimal(200);
//        BigDecimal bigDecimal1 = new BigDecimal(200);
////        System.out.println("sss"+bigDecimal.subtract(bigDecimal1));
//        System.out.println("aaaaaa>>>" + bigDecimal.compareTo(bigDecimal1));
//
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(String.valueOf(System.currentTimeMillis()));
//        stringBuffer.append(48);
//        stringBuffer.append(152);
//        System.out.println("bbbbbbbbbb" + stringBuffer.toString());
//        System.out.println("cc" + String.valueOf(System.currentTimeMillis() + 48 + 152 + Math.random() * 10000));
//
//        List<Integer> mylist = Arrays.asList(1, 2, 3);
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < mylist.size() - 1; i++) {
//            for (int j = i + 1; j < mylist.size(); j++) {
//                list.add(mylist.get(i) + ":" + mylist.get(j));
//            }
//        }
//        List<TimeAndWeekRes> timeAndWeekResList = NannyWorkTimeUtil.value2TimeAndWeekRes(281474976710654L);
//        for (int i=0;i<timeAndWeekResList.size();i++){
//            System.out.println(timeAndWeekResList.get(i).toString());
//        }
//        System.out.println(StringUtil.changeIntegerLenght(6655,6));
//        System.out.println((int)(Math.random()*10000));

//        String host = "https://yunyidata.market.alicloudapi.com";
//        String path = "/bankAuthenticate4";
//        String method = "POST";
//        String appcode = "a6b220c9c22549cca37ca365caa26209";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        //根据API的要求，定义相对应的Content-Type
//        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//        Map<String, String> querys = new HashMap<String, String>();
//        Map<String, String> bodys = new HashMap<String, String>();
//        bodys.put("ReturnBankInfo", "YES");
//        bodys.put("cardNo", "62155811111111111");
//        bodys.put("idNo", "340421199922225555");
//        bodys.put("name", "张三");
//        bodys.put("phoneNo", "13522221111");

//        String string = "{\"showapi_res_code\":0,\"showapi_res_error\":\"\",\"showapi_res_body\":{\"ret_code\":0,\"belong\":{\"area\":\"江西省 - 南昌\",\"tel\":\"95533\",\"brand\":\"龙卡储蓄卡(银联卡)\",\"bankName\":\"建设银行\",\"cardType\":\"借记卡\",\"url\":\"www.ccb.com\",\"cardNum\":\"6227002022071179506\"},\"code\":5,\"msg\":\"认证不通过\"}}";
//        ShowApiRes showApiRes = JsonUtil.json2Bean(string,ShowApiRes.class);
//        System.out.println(showApiRes.getShowapi_res_body().getBelong().toString());

//        AliUtil.signFourPoint();

//        ShowapiRequest req = new ShowapiRequest(
//                "请求地址，例如http://ali-weather.showapi.com/ip-to-weather","你的AppCode");
//        byte b[] = req.addTextPara("para1_name","para1_value")
//                .addTextPara("para2_name","中文值，不用urlencode")
//                .getAsByte();
//        //打印返回头
//        Map map = req.getRes_headMap();
//        Iterator it = map.keySet().iterator();
//        while (it.hasNext()) {
//            Object k = it.next();
//            System.out.println(k + "          " + map.get(k));
//        }
//        //打印返回体
//        String	res = new String(b,"utf-8");
//        System.out.println(res);
//        //下面是使用阿里的fastjson包解析。如果不需要，请使用自己的解析包
//        JSONObject json=JSONObject.parseObject(res);
//        System.out.println(json.getJSONObject("showapi_res_body"));
//        String url = ""

//        System.out.println("返回结果： "+ DateUtil.parseStringToDate("2018-05-08 16:33:28",DateUtil.PATTERN_yyyy_MM_dd_HHmmss));
//        List<JCustomerAlyStatment> jCustomerAlyStatmentList = AlipayUtil.getTradeByDate("2018-05-10");
//        for (JCustomerAlyStatment jCustomerAlyStatment:jCustomerAlyStatmentList){
//            System.out.println(jCustomerAlyStatment.toString());
//        }
//        Date date = DateUtil.parseStringToDate("2018-06-29 00:00:00",DateUtil.PATTERN_yyyy_MM_dd_HHmmss);
//        Date date1 = DateUtil.parseStringToDate("2018-06-30 23:59:59",DateUtil.PATTERN_yyyy_MM_dd_HHmmss);
//        date1=DateUtil.parseStringToDate(DateUtil.parseDateToString(date1,DateUtil.PATTERN_yyyy_MM_dd),DateUtil.PATTERN_yyyy_MM_dd);
//        System.out.println(date1);
//        DateUtil.weekAndDay(6,date,date1);

        System.out.println(TencentMapUtil.getDistance(121.3329900000,31.2374300000,121.33215,31.238314));

    }
}
