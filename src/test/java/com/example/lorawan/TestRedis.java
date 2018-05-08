package com.example.lorawan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        ///stringRedisTemplate.opsForValue().set("aaa","eee");
        String devaddr="BBBBBBB7";
        List list=stringRedisTemplate.opsForList().range(devaddr,0,-1);
        for (Object o:list){
            System.out.println(o);
        }
    }
}
