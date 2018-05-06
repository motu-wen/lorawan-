package com.example.lorawan.service.serviceImpl;

import com.example.lorawan.service.WebSocketServer;

import com.example.lorawan.until.JSonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
@Configuration
public class WebSocketImpl implements WebSocketServer {

    public String send() {
        return "getResponse";
    }


    public Object callback(String msg) throws Exception {
        String data = JSonUtil.getNodeJson(msg, "data");
        String devaddr = JSonUtil.getNodeJson(msg, "devaddr");
        data = data.substring(1, data.length()-1);
        devaddr = devaddr.substring(1, devaddr.length()-1);
        System.out.println(data+"======"+devaddr);
        return "callback ";
    }
}
