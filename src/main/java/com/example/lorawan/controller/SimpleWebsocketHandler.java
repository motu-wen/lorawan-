package com.example.lorawan.controller;

import com.example.lorawan.service.WebSocketServer;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@WebSocket(maxTextMessageSize = 1024*1024)
@Component
public class SimpleWebsocketHandler {
    @Autowired
    private WebSocketServer server;
    private static SimpleWebsocketHandler handle;
    private final CountDownLatch closeLatch;
    private final CountDownLatch connectLatch;
    private Session session;
    @PostConstruct
    public void init(){
        handle=this;
        handle.server=this.server;
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
    public SimpleWebsocketHandler() {
        this.closeLatch=new CountDownLatch(1);
        this.connectLatch=new CountDownLatch(1);
    }
    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException{
        return this.closeLatch.await(duration,unit);
    }
    public boolean awaitConnect(int duration,TimeUnit unit) throws InterruptedException{
        return this.connectLatch.await(duration,unit);

    }
    @OnWebSocketClose
    public void onClose(int statusCode,String reason){
        InetSocketAddress address=this.session.getRemoteAddress();
        String hostString =address.getHostString();
        int port=address.getPort();
        this.session=null;
        this.closeLatch.countDown();

    }
    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        InetSocketAddress address = session.getRemoteAddress();
        String hostName = address.getHostName();
        int port = address.getPort();
        String hasAddress = hostName + ":" + port;
        this.session=session;
        this.connectLatch.countDown();
    }
    @OnWebSocketMessage
    public void onMessage(String msg) {
        try {
            handle.server.callback(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @OnWebSocketError
    public void onError(Throwable throwable) {
        try {
        } catch (Exception e) {
            session.close();

        } catch (Throwable throwable1) {
            throwable.printStackTrace();
        }
    }
}
