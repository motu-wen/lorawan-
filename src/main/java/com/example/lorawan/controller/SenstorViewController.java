package com.example.lorawan.controller;

import com.example.lorawan.doamin.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SenstorViewController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/getByDevAddr")
    public ResponseEntity getValueByDev(@RequestParam String devaddr){
        List list=stringRedisTemplate.opsForList().range(devaddr,0,-1);
    return ResponseEntity.success().add("list",list);
}
}
