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
    private List<String> senstor;

    public ClassRoom(Integer build, Integer floor, Integer room) {
        this.build = build;
        this.floor = floor;
        this.room = room;
    }

    public List<String> getSenstor() {
        return senstor;
    }

    public void setSenstor(List<String> senstor) {
        this.senstor = senstor;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ClassRoom() {
    }

    public ClassRoom(Integer build, Integer floor, Integer room, Integer count) {
        this.build = build;
        this.floor = floor;
        this.room = room;
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
