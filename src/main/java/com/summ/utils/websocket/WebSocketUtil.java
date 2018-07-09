package com.summ.utils.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by admin on 2016/12/29.
 */
public class WebSocketUtil extends WebSocketClient {

    private String defaultText;

        public WebSocketUtil(String defaultText) throws URISyntaxException {
//            super(new URI("ws://112.74.182.69:8081/jyguanjia/websocket/1"), new Draft_17());
            super(new URI("ws://192.168.1.78:8080/JYGJMS/websocket/4"), new Draft_17());
            this.defaultText = defaultText;

        }

        @Override
        public void onOpen(ServerHandshake handshake) {
//            LogUtil.i("WebSocket已连接");
            System.out.println("WebSocket已连接");
            send(defaultText);
            close();
        }

        @Override
        public void onMessage(String s) {
//            LogUtil.i("收到消息: " + s);
            System.out.println("收到消息: " + s);
        }

        @Override
        public void onClose(int i, String s, boolean b) {
//            LogUtil.i("WebSocket已关闭 :" + s);
            System.out.println("WebSocket已关闭: " + s);
        }

        @Override
        public void onError(Exception e) {
//            LogUtil.e("WebSocket异常: " + e.getMessage());
            System.out.println("WebSocket异常: " + e.getMessage());
        }
    }
