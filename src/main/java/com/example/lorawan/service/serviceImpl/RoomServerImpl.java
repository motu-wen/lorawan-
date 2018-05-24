package com.example.lorawan.service.serviceImpl;

import com.example.lorawan.doamin.ClassRoom;
import com.example.lorawan.doamin.ResponseEntity;
import com.example.lorawan.until.JSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RoomServerImpl {
    @Autowired
    private StringRedisTemplate template;
    public void saveClassRoom(ClassRoom classroom){
        classroom.setCount(0);
        String build=classroom.getBuild().toString(),floor=classroom.getFloor().toString(),room=classroom.getRoom().toString();
        String key=build+"-"+floor+"-"+room;
        String allkey=template.opsForValue().get("classroom");
        if(allkey==(null)){
            allkey=" ";
        }
        BoundListOperations<String,String> opsbuild=template.boundListOps("buildall");
        List<String> buildlist=opsbuild.range(0,-1);

        BoundListOperations<String,String> opsfloor=template.boundListOps(build+"build");
        List<String> floorlist=opsfloor.range(0,-1);
        BoundListOperations<String,String> opsroom=template.boundListOps(build+"-"+floor+"floor");
        List<String> roomlist=opsroom.range(0,-1);
        if(!allkey.contains(key)){
            allkey+="room"+key;
            template.opsForValue().set("classroom",allkey);
            template.opsForValue().set(key, JSonUtil.toJSonString(classroom));
            if(!buildlist.contains(build)){
                opsbuild.rightPush(build);
            }
            if(!floorlist.contains(floor)){
                opsfloor.rightPush(floor);
            }
            if(!roomlist.contains(room)){
                opsroom.rightPush(room);
            }
        }

    }

}
