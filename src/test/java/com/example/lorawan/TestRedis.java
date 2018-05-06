package com.example.lorawan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("aaa","eee");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }
}
