package com.example.lorawan;

import com.example.lorawan.controller.SenstorViewController;
import com.example.lorawan.doamin.InfraredSentor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 测试根据设备获取所有数据的控制器
 *
 * @author {Administrator}
 * @data 2018/5/6 20:55
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestSensorgetController {

  MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Before
    public void init(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void test()throws  Exception{
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/getByDevAddr")).andReturn();
        MockHttpServletRequest request=result.getRequest();
        Object ob=request.getAttribute("");
    }
}
