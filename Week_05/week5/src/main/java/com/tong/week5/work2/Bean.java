package com.tong.week5.work2;

import org.springframework.stereotype.Component;

@Component
public class Bean {
    public String test(){
        System.out.println("auto config");
        return "test";
    }
}
