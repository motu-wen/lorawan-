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
    @RequestMapping(value = "/getbuildall",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getBuildAll(){
        List<String> buildall=stringRedisTemplate.opsForList().range("buildall",0,-1);
        return ResponseEntity.success().add("list",buildall);
    }
    @RequestMapping(value = "/getfloorall",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getFloorAll(@RequestParam String build){
        List<String> floorall=stringRedisTemplate.opsForList().range(build+"build",0,-1);
        return ResponseEntity.success().add("list",floorall);
    }
    @RequestMapping(value = "/getroomdall",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getRoomAll(@RequestParam String floor){
        List<String> roomall=stringRedisTemplate.opsForList().range(floor+"floor",0,-1);
        return ResponseEntity.success().add("list",roomall);
    }
    @RequestMapping(value = "/getStudentCount",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getStudentCount(@RequestParam String key){
        ClassRoom classRoom1=JSonUtil.toObject(stringRedisTemplate.opsForValue().get(key),ClassRoom.class);
        Integer count=classRoom1.getCount();
        return ResponseEntity.success().add("list",count);
    }

    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveClassRoom(ClassRoom classRoom){
        roomServer.saveClassRoom(classRoom);
        return ResponseEntity.success();
    }
    @RequestMapping(value = "/getAllSen",method = RequestMethod.GET)
    public ResponseEntity getAllSen(){
        String list=stringRedisTemplate.opsForValue().get("hongwaiList");
        String list1=stringRedisTemplate.opsForValue().get("LockList");
        return ResponseEntity.success().add("infrared",list).add("lock",list1);
    }
}
