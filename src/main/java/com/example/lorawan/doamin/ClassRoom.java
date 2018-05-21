package com.example.lorawan.doamin;

import java.util.List;

/**
 * 教室的实体类
 *
 * @author {Administrator}
 * @data 2018/5/8 16:17
 **/
public class ClassRoom {
    private Integer build;//教学楼编号
    private Integer floor;
    private Integer room;
    private Integer count;
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



    public Integer getBuild() {
        return build;
    }

    public void setBuild(Integer build) {
        this.build = build;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }



}
