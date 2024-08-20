package com.efreight.base.config.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * socket服务
 *
 * @author ZhangYongWei
 * @since 2024/4/22
 */
@Getter
@ServerEndpoint("/ws/socket/{clientId}")
@Component
@Slf4j
public class WebSocketServer {
    
    /**
     * PING
     */
    private static final String PING = "ping";
    
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
    
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static final ConcurrentHashMap<String, WebSocketServer> WEB_SOCKET_MAP = new ConcurrentHashMap<>();
    
    private Session session;
    
    private String clientId;
    
    /**
     * 根据客户端ID获取socket
     *
     * @param clientId 客户端ID
     * @return WebSocketServer
     */
    public static WebSocketServer getWebSocketServer(String clientId) {
        return WEB_SOCKET_MAP.get(clientId);
    }
    
    /**
     * 根据客户端ID获取socket集合
     *
     * @param clientId 客户端ID
     * @return WebSocketServer
     */
    public static List<WebSocketServer> getWebSocketServerList(String clientId) {
        List<WebSocketServer> result = new ArrayList<>();
        if (!WEB_SOCKET_MAP.isEmpty()) {
            for (Entry<String, WebSocketServer> entry : WEB_SOCKET_MAP.entrySet()) {
                if (entry.getKey().contains(clientId)) {
                    result.add(entry.getValue());
                }
            }
        }
        return result;
    }
    
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String key = this.getClientId() + "_" + this.getSession().getId();
        synchronized (this) {
            if (WEB_SOCKET_MAP.containsKey(key)) {
                WEB_SOCKET_MAP.remove(key);
                //从set中删除
                subOnlineCount();
            }
            log.info("客户端退出:{},当前在线客户端为:{}", key, getOnlineCount());
        }
    }
    
    public void subOnlineCount() {
        ONLINE_COUNT.decrementAndGet();
    }
    
    public int getOnlineCount() {
        return ONLINE_COUNT.get();
    }
    
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        String key = this.getClientId() + "_" + this.getSession().getId();
        log.info("收到客户端消息:{},报文:{}", key, message);
        WebSocketServer ws = WEB_SOCKET_MAP.get(key);
        if (ws != null) {
            if (StringUtils.isNotBlank(message)) {
                //维持心跳
                if (PING.equals(message)) {
                    ws.sendMessage("pang", "SYSTEM");
                }
            } else {
                log.error("onMessage 空消息");
            }
        } else {
            this.onOpen(this.getClientId(), this.session);
            log.error("onMessage 客户端会话不存在 clientId:{}，重新链接", this.getClientId());
        }
    }
    
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message, String type) {
        try {
            JSONObject messageObj = new JSONObject();
            messageObj.put("code", 0);
            messageObj.put("data", message);
            messageObj.put("type", type);
            this.session.getBasicRemote().sendText(JSONObject.toJSONString(messageObj));
            log.debug("主动推送: {}", message);
        } catch (IOException e) {
            log.error("服务器主动推送失败", e);
        }
    }
    
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("clientId") String clientId, Session session) {
        synchronized (this) {
            String key = clientId + "_" + session.getId();
            log.info("clientId===={}", key);
            if (!WEB_SOCKET_MAP.containsKey(key)) {
                this.clientId = clientId;
                this.session = session;
                WEB_SOCKET_MAP.put(key, this);
                addOnlineCount();
                log.info("客户端连接:{},当前在线人数为:{}", key, getOnlineCount());
                sendMessage("Cargo Socket服务连接成功！", "SYSTEM");
            }
        }
    }
    
    public void addOnlineCount() {
        WebSocketServer.ONLINE_COUNT.incrementAndGet();
    }
    
    /**
     *
     */
    @OnError
    public void onError(Throwable error) {
        String key = this.getClientId() + "_" + this.getSession().getId();
        log.error("错误:{},原因:{}", key, error.getMessage(), error);
    }
}