package com.yeehom.learn.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/seller/web-socket")
@Slf4j
public class SellerWebSocket {

    private Session session;

    private static CopyOnWriteArraySet<SellerWebSocket> sellerWebSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        sellerWebSocketSet.add(this);
        log.info("【WebSocket消息】有新的连接，总数：{}", sellerWebSocketSet.size());
    }

    @OnClose
    public void onClose() {
        sellerWebSocketSet.remove(this);
        log.info("【WebSocket消息】连接断开，总数：{}", sellerWebSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【WebSocket消息】收到客户端发来的消息：{}", message);
    }

    public void sendMessage(String message) {
        for (SellerWebSocket  sellerWebSocket: sellerWebSocketSet) {
            try {
                log.info("【WebSocket消息】广播消息，message={}", message);
                sellerWebSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
