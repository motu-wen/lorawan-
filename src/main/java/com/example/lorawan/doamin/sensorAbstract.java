package com.example.lorawan.doamin;

public abstract class sensorAbstract implements SensorInterface {
    private Integer id;
    private String devaddr;
    private String Type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevaddr() {
        return devaddr;
    }

    public void setDevaddr(String devaddr) {
        this.devaddr = devaddr;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
