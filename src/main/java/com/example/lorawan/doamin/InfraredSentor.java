package com.example.lorawan.doamin;

import java.util.Date;

public class InfraredSentor extends sensorAbstract {
    private Integer count;
    private Date time;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
