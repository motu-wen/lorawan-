package com.example.lorawan.controller;

import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.until.JSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 1
 *
 * @author {Administrator}
 * @data 2018/5/19 16:17
 **/
@RestController
public class ClassRoomServer {
    private ClassRoom classRoom1=new ClassRoom(),classRoom2=new ClassRoom();
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ClassRoom getClassRoom1() {
        return classRoom1;
    }

    public ClassRoom getClassRoom2() {
        return classRoom2;
    }
    public void redisToStudentNumber(){
        String s[]=new String[4];
        classRoom1.setCount(10);
        s[0]= JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB2",100, TimeUnit.MILLISECONDS),"time");
        s[1]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB3",100, TimeUnit.MILLISECONDS),"time");
        s[2]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB5",100, TimeUnit.MILLISECONDS),"time");
        s[3]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB7",100, TimeUnit.MILLISECONDS),"time");
        studentNumber(s);
    }
    public void studentNumber(String s1[]){
        if(s1[0]!=null&&!"".equals(s1[0])&&s1[1]!=null&&!"".equals(s1[1])) {
            if (s1[0].compareTo(s1[1]) < 0) {
                classRoom1.setCount(classRoom1.getCount() + 1);
            } else {
                classRoom1.setCount(classRoom1.getCount() - 1);
            }
        }
        if(s1[2]!=null&&!"".equals(s1[2])&&s1[3]!=null&&!"".equals(s1[3])) {
            if (s1[2].compareTo(s1[3]) < 0) {
                classRoom1.setCount(classRoom1.getCount() + 1);
            } else {
                classRoom1.setCount(classRoom1.getCount() - 1);
            }
        }
    }
}
