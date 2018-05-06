package com.example.lorawan;

import com.example.lorawan.until.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHex {
    @Test
    public void test(){
        String hexstr="4952636F756E743D310D0A";
        String h=Hex.HexToString(hexstr);
        System.out.println(h);
    }
}
