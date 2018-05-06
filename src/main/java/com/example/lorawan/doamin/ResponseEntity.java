package com.example.lorawan.doamin;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntity {
    //服务器返回给浏览器的状态码
    private int code;
    //提示信息
    private String message;

    public ResponseEntity() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public ResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //返回给浏览器的数据
    private Map<String,Object> data=new HashMap<String,Object>();
    public static ResponseEntity success(){
        return new ResponseEntity(200,"成功");
    }
    public ResponseEntity add(String key,Object value){
        this.getData().put(key,value);
        return this;
    }

    public static ResponseEntity fail(){
        return new ResponseEntity(201,"异常");
    }
    public static ResponseEntity LinkOutline(){
        return new ResponseEntity(202,"LoRa连接已断开");
    }
}
