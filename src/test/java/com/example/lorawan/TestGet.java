package com.example.lorawan;

import com.example.lorawan.doamin.InfraredSentor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGet {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        List list=stringRedisTemplate.opsForList().range("BBBBBBB7",0,-1);
        for (Object s:list
             ) {
            System.out.println(s);
        }
    }
}
