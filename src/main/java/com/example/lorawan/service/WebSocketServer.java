package com.example.lorawan.service;

public interface WebSocketServer {
    public String send();
    public Object callback(String msg) throws Exception;
}
