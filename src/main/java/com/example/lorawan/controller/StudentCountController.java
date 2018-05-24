package com.example.lorawan.controller;

import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.doamin.ResponseEntity;
import com.example.lorawan.service.serviceImpl.RoomServerImpl;
import com.example.lorawan.until.JSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value = "/getbuildall",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getBuildAll(){
        List<String> buildall=stringRedisTemplate.opsForList().range("build",0,-1);
        return ResponseEntity.success().add("list",buildall);
    }
    @RequestMapping(value = "/getfloorall",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getFloorAll(@RequestBody String build){
        List<String> floorall=stringRedisTemplate.opsForList().range(build+"build",0,-1);
        return ResponseEntity.success().add("list",floorall);
    }
    @RequestMapping(value = "/getroomdall",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getRoomAll(@RequestBody String floor){
        List<String> roomall=stringRedisTemplate.opsForList().range(floor+"floor",0,-1);
        return ResponseEntity.success().add("list",roomall);
    }
    @RequestMapping(value = "/getStudentCount",method = RequestMethod.POST)
    @ResponseBody
    public ClassRoom getStudentCount(@RequestBody ClassRoom classRoom){
        String key=classRoom.getBuild()+"-"+classRoom.getFloor()+"-"+classRoom;
        ClassRoom classRoom1=JSonUtil.toObject(stringRedisTemplate.opsForValue().get(key),ClassRoom.class);
        return classRoom1;
    }

    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveClassRoom( ClassRoom classRoom){
        roomServer.saveClassRoom(classRoom);
        return ResponseEntity.success();
    }

}
