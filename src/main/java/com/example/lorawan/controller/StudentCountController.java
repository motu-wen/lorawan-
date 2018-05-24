package com.example.lorawan.controller;

import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.doamin.ResponseEntity;
import com.example.lorawan.service.serviceImpl.RoomServerImpl;
import com.example.lorawan.until.JSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * studentcount
 *
 * @author {Administrator}
 * @data 2018/5/18 11:01
 **/
@RestController
@ResponseBody
public class StudentCountController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RoomServerImpl roomServer;
    @RequestMapping(value = "/getStudentCount",method = RequestMethod.POST)
    @ResponseBody
    public ClassRoom getStudentCount(@RequestBody ClassRoom classRoom){
        ClassRoom classRoom1= JSonUtil.toObject(stringRedisTemplate.opsForValue().get("classroom"),ClassRoom.class);

        if((classRoom.getBuild()+classRoom.getFloor()+classRoom.getRoom())==(classRoom1.getBuild()+classRoom1.getFloor()+classRoom1.getRoom())){
            System.out.println(classRoom.getBuild()+classRoom.getFloor()+classRoom.getRoom());
            System.out.println(classRoom1.getBuild()+classRoom1.getFloor()+classRoom1.getRoom());
            return classRoom1;
        }else {
            return classRoom;
        }
    }

    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveClassRoom(ClassRoom classRoom){
        roomServer.saveClassRoom(classRoom);
        return ResponseEntity.success();
    }
}
