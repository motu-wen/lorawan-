package com.example.lorawan;

import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.service.serviceImpl.RoomServerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 1
 *
 * @author {Administrator}
 * @data 2018/5/24 9:39
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRoomServer {
    @Autowired
    private RoomServerImpl roomServer;
    @Test
    public void test(){
        ClassRoom classRoom=new ClassRoom(1,5,3);
        roomServer.saveClassRoom(classRoom);
    }
}
