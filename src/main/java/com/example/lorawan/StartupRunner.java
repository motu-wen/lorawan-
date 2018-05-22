package com.example.lorawan;

import com.example.lorawan.controller.WebSocketController;
import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.service.ClassRoomServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
    @Override
    public void run(String... args) throws Exception {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    webSocketController.resetWebSocketUrl();
                    classRoomServer.redisToStudentNumber();
                }
            }
        });
        t1.start();
    }
}
