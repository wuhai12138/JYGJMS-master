
package com.summ.utils.websocket;
import com.summ.utils.JsonUtil;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mc on 2016/10/17.
 */
//@ServerEndpoint(value = "/websocket")
@ServerEndpoint(value = "/websocket/{adminType}")
public class WebsocketDemo {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static Map<String, WebsocketDemo> webSocketSet = new HashMap<String, WebsocketDemo>();

    private Session mSession;
    private String adminType;


    public String getAdminType() {
        return adminType;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("adminType") String adminType, Session session) {
        this.mSession = session;
        this.adminType = adminType;
        webSocketSet.put(session.getId(), this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！adminType = " + adminType + " \n当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        this.mSession = session;
        webSocketSet.remove(session.getId());  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        WebSocketMsg msg = JsonUtil.json2Bean(message, WebSocketMsg.class);
        try {
            for (Map.Entry<String, WebsocketDemo> entry : webSocketSet.entrySet()) {
                if (entry.getValue().getAdminType().equals(msg.getAdminType())) {
//                    LogUtil.i(this.shop_id + " 发送给 " + entry.getValue().getShop_id() + " : " + msg.getSuggestContent());
                    entry.getValue().mSession.getBasicRemote().sendText(msg.getSuggestContent());
                }else{
//                    LogUtil.i(entry.getValue().getShop_id()+" : " +msg.getShopId());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     *
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误" + error.getMessage());
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketDemo.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketDemo.onlineCount--;
    }


}

