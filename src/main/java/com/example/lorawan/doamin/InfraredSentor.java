package com.example.lorawan.doamin;

import java.util.Date;

public class InfraredSentor extends sensorAbstract {
    private Integer count;
    private String time;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "devaddr="+getDevaddr()+
                ",type="+getType()+
                "count=" + count +
                ", time=" + time ;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
