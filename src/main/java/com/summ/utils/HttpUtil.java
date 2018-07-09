package com.summ.utils;


import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by M.c on 2016/1/6.
 */
public class HttpUtil {

    private static HttpUtil mInstance;
    private int timeOut = 10000;//单位毫秒
    private String name = "默认接口名称";
    private String url;
    private int httpMode = JSON;
    private Object obj = null;
    private OkHttpClient mOkHttpClient;
    private HttpCallBack callback;
    private String firstUrlChar = "?";
    public static int GET = 0;
    public static int POST = 1;
    public static int JSON = 2;
    public static int XML = 3;
    public String xml;


    private HttpUtil() {
        mOkHttpClient = new OkHttpClient();
    }

    public static HttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtil.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtil();
                }
            }
        } else {
            initAllParam();
        }

        return mInstance;
    }

    /**
     * 初始化所有参数
     */
    private static void initAllParam() {
        mInstance.timeOut = 10000;//单位毫秒
        mInstance.url = "";
        mInstance.name = "默认接口名称";
        mInstance.httpMode = JSON;
        mInstance.obj = null;
        mInstance.callback = null;


    }

    public HttpUtil setCallBack(HttpCallBack callback) {
        this.callback = callback;
        return this;
    }

    public HttpUtil setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public HttpUtil setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpUtil setName(String name) {
        this.name = name;
        return this;
    }

    public HttpUtil setXml(String xml) {
        this.xml = xml;
        return this;
    }


    public HttpUtil setHttpMode(int httpMode) {
        this.httpMode = httpMode;
        return this;
    }

    public HttpUtil setObj(Object obj) {
        this.obj = obj;
        return this;
    }


    /**
     * 执行请求
     */
    public void sender() {

        if (StringUtil.isBlank(url)) {
            LogUtil.getLogger().error("地址为空");
            return;
        }
        Request request = null;
        Map<String, Object> map = null;
        if (null != obj) {
            map = JsonUtil.json2Map(JsonUtil.toJson(obj));
        }

        switch (httpMode) {
            default:
            case 2://JSON
                LogUtil.getLogger().info("请求: " + url);
                LogUtil.getLogger().info("请求对象: " + JsonUtil.toJson(obj));
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(obj));
                request = new Request.Builder().url(url).post(body).build();
                break;
            case 0://GET
                StringBuilder urlBuilder = new StringBuilder(url);
                if (null != obj) {
                    urlBuilder.append(firstUrlChar);
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        urlBuilder.append(entry.getKey() + "=" + entry.getValue().toString() + "&");
                    }
                    urlBuilder.deleteCharAt(urlBuilder.length() - 1);
                }
                LogUtil.getLogger().info("请求: " + urlBuilder.toString());
                request = new Request.Builder().url(urlBuilder.toString()).build();

                break;
            case 1://POST
                LogUtil.getLogger().info("请求: " + url);
                if (null != obj) {
                    LogUtil.getLogger().info("请求对象: " + JsonUtil.toJson(obj));

                    FormBody.Builder builder = new FormBody.Builder();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        builder.add(entry.getKey(), entry.getValue().toString());
                    }
                    request = new Request.Builder().url(url).post(builder.build()).build();
                } else {
                    request = new Request.Builder().url(url).build();
                }

                break;

            case 3://XML
                RequestBody requestBody = RequestBody.create(MediaType.parse("utf-8"), xml);
                request = new Request.Builder().addHeader("Content-Type", "text/xml").url(url).post(requestBody).build();
                break;

        }

        callback.onBefore();
        if (null != callback) {
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.onFailure(e.getMessage());
                    callback.onAfter();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callback.onSuccess(response.body().string());
                    callback.onAfter();
                }
            });
        } else {
            try {
                mOkHttpClient.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            callback.onAfter();
        }


    }


    public interface HttpCallBack {

        void onSuccess(String response);

        void onFailure(String errorMsg);

        void onBefore();

        void onAfter();
    }

    public static class HttpListener implements HttpCallBack {
        @Override
        public void onSuccess(String response) {

        }

        @Override
        public void onFailure(String errorMsg) {
            LogUtil.getLogger().error(errorMsg);
        }

        @Override
        public void onBefore() {
            LogUtil.getLogger().info("HTTP请求开始");
        }

        @Override
        public void onAfter() {
            LogUtil.getLogger().info("HTTP请求结束");
        }
    }


}
