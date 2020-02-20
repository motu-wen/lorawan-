package com.example.lorawan;

import com.example.lorawan.controller.WebSocketController;
import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.service.ClassRoomServer;
import com.example.lorawan.until.JSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 1
 *
 * @author {Administrator}
 * @data 2018/5/20 16:08
 **/
@Component
public class StartupRunner implements CommandLineRunner {
    @Autowired
    private ClassRoomServer classRoomServer;
    @Autowired
    private WebSocketController webSocketController;
@Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void run(String... args) throws Exception {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                webSocketController.resetWebSocketUrl();
                while (true){
                    ClassRoom classRoom= JSonUtil.toObject(stringRedisTemplate.opsForValue().get("1-1-1"),ClassRoom.class);
                    classRoomServer.count("1-1-1",classRoom);
                }
            }
        });
        t1.start();
    }
}
