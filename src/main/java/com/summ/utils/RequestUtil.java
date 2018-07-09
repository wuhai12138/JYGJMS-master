package com.summ.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.Request;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * httpClient
 *
 * @author summ
 */
public class RequestUtil {

    /**
     * do get request
     *
     * @param url
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String doGet(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpget);
        if (response.getStatusLine().getStatusCode() != 200) {
            return null;
        }
        String res = EntityUtils.toString(response.getEntity(), "UTF-8");

        if (httpClient != null) {
            httpClient.close();
        }
        return res;
    }

    /**
     * do post request (JSON)
     *
     * @param url
     * @param data
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doPost(String url, String data) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(data, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            return null;
        }
        String res = EntityUtils.toString(response.getEntity(), "UTF-8");

        if (httpClient != null) {
            httpClient.close();
        }
        return res;
    }

    /**
     * x-www-form-urlencoded
     * @param url
     * @param map
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doPost(String url, Map<String,String> map) throws ClientProtocolException, IOException {
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, "UTF-8");
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    public static String sign(ServletRequest requestWrapper) throws IOException {
        //验证请求参数加密的合法性
        InputStream is;
        String contentStr;
        is = requestWrapper.getInputStream();
        contentStr = IOUtils.toString(is, "UTF-8");
        if (!contentStr.equals("")) {
            Map map = JsonUtil.json2Map(contentStr);
            if (map.size() > 0) {
                List list = new ArrayList();
                for (Object key : map.keySet()) {
                    list.add(key);
                }
                String[] str = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    str[i] = (String) list.get(i);
                }
                System.out.println("before：" + Arrays.toString(str));
                Arrays.sort(str, String.CASE_INSENSITIVE_ORDER);
                System.out.println("after：" + Arrays.toString(str));
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < str.length - 1; i++) {
                    stringBuilder.append(str[i]);
                    stringBuilder.append("=");
                    stringBuilder.append(map.get(str[i]));
                    stringBuilder.append("&&");
                }
                stringBuilder.append(str[str.length - 1]);
                stringBuilder.append("=");
                stringBuilder.append(map.get(str[str.length - 1]));
                System.out.println("string Builder " + stringBuilder);
                System.out.println("map str md5 " + StringUtil.getMd5Value(stringBuilder.toString()));
                if (!StringUtil.getMd5Value(stringBuilder.toString()).equals(requestWrapper.getParameter("sign"))) {
                    System.out.println("请求不合法");
                    return "{\"code\":\"102\",\"msg\":\"请求不合法 !\"}";
                }
                return "";
            }return "";
        }return "";

    }
}
