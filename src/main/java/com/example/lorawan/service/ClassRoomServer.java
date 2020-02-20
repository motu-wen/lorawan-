package com.example.lorawan.service;

import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.until.JSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * 1
 *
 * @author {Administrator}
 * @data 2018/5/19 16:17
 */

@Service
public class ClassRoomServer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public void redisToStudentNumber() {

    }
    public void isBefore(String key1,String key2,ClassRoom classRoom,String key){
        String key1Time=JSonUtil.getNodeTextValue(stringRedisTemplate.opsForList().leftPop(key1),"time");
        int i=0;
        while(null==stringRedisTemplate.opsForList().index(key2,0)){
            if(i>10000)break;
            else i++;
        }
        String key2Time=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().leftPop(key2),"time");
        if(null!=key2Time){
            if(key1Time.compareTo(key2Time)<0){
                classRoom.setCount(classRoom.getCount()+1);
            }
            if(key1Time.compareTo(key2Time)>=0){
                classRoom.setCount(classRoom.getCount()-1);
            }
        }
        stringRedisTemplate.opsForValue().set(key,JSonUtil.toJSonString(classRoom));
    }

    public void count(String key,ClassRoom classRoom){
        List<String> list=classRoom.getSenstor();
        if(null!=stringRedisTemplate.opsForList().index(list.get(0),0)){
            isBefore(list.get(0),list.get(1),classRoom,key);
        }else if(null!=stringRedisTemplate.opsForList().index(list.get(1),0)) {
            isBefore(list.get(1),list.get(0),classRoom,key);
        }
        if(null!=stringRedisTemplate.opsForList().index(list.get(2),0)){
            isBefore(list.get(2),list.get(3),classRoom,key);
        }else if(null!=stringRedisTemplate.opsForList().index(list.get(3),0)) {
            isBefore(list.get(3),list.get(2),classRoom,key);
        }
    }
}
