package com.example.lorawan.controller;

import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class WebSocketController {

    public static String destUrl="ws://114.213.206.118:8080/ws/groups/infrared/json";
    @RequestMapping("/resetUrl")
    public void  resetWebSocketUrl(){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                SslContextFactory sslContextFactory=new SslContextFactory(true);
                WebSocketClient client=new WebSocketClient(sslContextFactory);
                client.setMaxIdleTimeout(0);
                SimpleWebsocketHandle socket=new SimpleWebsocketHandle();
                try{
                    client.start();
                    URI connectUri=new URI(destUrl);
                    ClientUpgradeRequest request=new ClientUpgradeRequest();
                    client.connect(socket,connectUri,request);
                    while (true){
                        Thread.sleep(1000*10);
                    }
                }catch (Throwable t){
                    t.printStackTrace();

                }finally {
                    try {
                        client.stop();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        t1.start();
    }
    }

