package com.summ.utils.websocket;

import com.summ.utils.JsonUtil;

import java.net.URISyntaxException;

/**
 * Created by admin on 2016/12/29.
 */
public class SendUtil {
    public static void sendMessage(WebSocketMsg webSocketMsg){
        WebSocketUtil mWebSocketClient = null;
        try {
            mWebSocketClient = new WebSocketUtil(JsonUtil.toJson(webSocketMsg));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mWebSocketClient.connect();
    }
}
