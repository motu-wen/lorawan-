package com.example.lorawan.service;

import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.until.JSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * 1
 *
 * @author {Administrator}
 * @data 2018/5/19 16:17
 **/
@Service
public class ClassRoomServer {
    private ClassRoom classRoom1,classRoom2=new ClassRoom();
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public void redisToStudentNumber(){
        classRoom1=JSonUtil.toObject(stringRedisTemplate.opsForValue().get("12-23-2"),ClassRoom.class);
        String s[]=new String[4];
        s[0]= JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB2",100, TimeUnit.MILLISECONDS),"time");
        s[1]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB3",100, TimeUnit.MILLISECONDS),"time");
        s[2]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB5",100, TimeUnit.MILLISECONDS),"time");
        s[3]=JSonUtil.getNodeTextStringValue(stringRedisTemplate.opsForList().rightPop("BBBBBBB7",100, TimeUnit.MILLISECONDS),"time");
        studentNumber(s);

        stringRedisTemplate.opsForValue().set("classroom",JSonUtil.toJSonString(classRoom1));
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
