package com.example.lorawan.controller;

import com.example.lorawan.doamin.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class SenstorViewController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping( "/getByDevAddr")
    @ResponseBody
    public ResponseEntity getValueByDev(){
        String devaddr="BBBBBBB7";
        List<String> list=stringRedisTemplate.opsForList().range(devaddr,0,-1);
        return ResponseEntity.success().add("list",list);

}
}
