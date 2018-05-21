package com.example.lorawan.controller;

import com.example.lorawan.service.ClassRoomServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * studentcount
 *
 * @author {Administrator}
 * @data 2018/5/18 11:01
 **/
@RestController
@ResponseBody
public class StudentCountController {
    @Autowired
    private ClassRoomServer classServer;
    @RequestMapping("/getstudentcount")
    public String getStudentCount(){
        return classServer.getClassRoom1().getCount().toString();

    }

}
