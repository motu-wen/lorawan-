package com.example.lorawan.controller;

import com.example.lorawan.doamin.ResponseEntity;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebSocketController {
    @Autowired
    private  StringRedisTemplate template;
    public static String destUrl;
    @RequestMapping("/resetUrl")
    public void  resetWebSocketUrl(){
        destUrl=template.opsForValue().get("lorawanAddress");
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                SslContextFactory sslContextFactory=new SslContextFactory(true);
                WebSocketClient client=new WebSocketClient(sslContextFactory);
                client.setMaxIdleTimeout(0);
                SimpleWebsocketHandler socket=new SimpleWebsocketHandler();
                try{
                    client.start();
                    URI connectUri=new URI(destUrl);
                    ClientUpgradeRequest request=new ClientUpgradeRequest();
                    client.connect(socket,connectUri,request);
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

    @RequestMapping(value = "/setLoRa",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity setLoRa(@RequestParam("loraUrl")String loraUrl,
                                  @RequestParam("LoraIpPort")String LoraIpPort) throws Exception{
        ResponseEntity response=ResponseEntity.fail();
        try{
            LoraIpPort=LoraIpPort.replace("\"","");
            template.opsForValue().set("lorawanIpAndPort",LoraIpPort);
            template.opsForValue().set("lorawanAddress",loraUrl);
            response=ResponseEntity.success();
        }catch (Exception e){
            response.setMessage(e.getMessage());
        }
        return response;

    }


    @RequestMapping("/getLora")
    public ResponseEntity getLora(){
        ResponseEntity response=ResponseEntity.fail();
        try{
            String loraIpAndPort=template.opsForValue().get("lorawanIpAndPort");
            String lorawanAddress=template.opsForValue().get("lorawanAddress");
            response=ResponseEntity.success();
            Map<String,String>map=new HashMap<String,String>();
            map.put("lorawanIpAndPort",loraIpAndPort);
            map.put("lorawanAddress",lorawanAddress);
            response.add("lora",map);

        }catch (Exception e){
            response.setMessage(e.getMessage());
        }
        return response;
    }
    }

