package com.example.lorawan.service.serviceImpl;

import com.example.lorawan.doamin.InfraredSentor;
import com.example.lorawan.enmu.redisExplain;
import com.example.lorawan.service.WebSocketServer;

import com.example.lorawan.until.JSonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Configuration
public class WebSocketImpl implements WebSocketServer {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketImpl.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public String send() {
        return "getResponse";
    }


    public Object callback(String msg) throws Exception {
        // 发现消息
        logger.info("待解析数据：{}",msg);
        String data = JSonUtil.getNodeJson(msg, "data");
        String devaddr = JSonUtil.getNodeJson(msg, "devaddr");
        data = data.substring(1, data.length()-1);
        devaddr = devaddr.substring(1, devaddr.length()-1);


        String IRCount=data.substring(16,data.length()-1);
        int peopleCount=Integer.parseInt(IRCount,16);
        InfraredSentor infraredSentor=new InfraredSentor();
        String type = stringRedisTemplate.opsForValue().get(redisExplain.type.explain + devaddr);
        infraredSentor.setDevaddr(devaddr);
        infraredSentor.setCount(peopleCount);

        infraredSentor.setTime(new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date()));
        infraredSentor.setType(type);
        String hongwaiList=stringRedisTemplate.opsForValue().get(redisExplain.hongwaiList.explain);
        if(StringUtils.isEmpty(hongwaiList)){
            hongwaiList=devaddr;
        }else if(hongwaiList.indexOf(devaddr)<0){
            hongwaiList+=","+devaddr;
        }
        stringRedisTemplate.opsForValue().set(redisExplain.hongwaiList.explain,hongwaiList);
        stringRedisTemplate.opsForList().rightPush(devaddr,infraredSentor.toString());

        List<String> list=stringRedisTemplate.opsForList().range(devaddr,0,1);
        for(String s:list){
            System.out.println(s);
        }
        System.out.println(data+"======"+devaddr);
        return "callback ";
    }
}
