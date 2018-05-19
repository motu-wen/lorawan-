package com.example.lorawan.controller;

import com.example.lorawan.doamin.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    private ClassRoomServer classServer;
    @RequestMapping("/getstudentcount")
    public String getStudentCount(){
        return classServer.getClassRoom1().getCount().toString();
    }
    /*public void redisToStudentNumber(){
        String s[]=new String[4];

        s[0]= JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB2",100, TimeUnit.MILLISECONDS),"time");
        s[1]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB3",100, TimeUnit.MILLISECONDS),"time");
        s[2]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB5",100, TimeUnit.MILLISECONDS),"time");
        s[3]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB7",100, TimeUnit.MILLISECONDS),"time");
        studentNumber(s);*//**//*
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
    }*/
}
