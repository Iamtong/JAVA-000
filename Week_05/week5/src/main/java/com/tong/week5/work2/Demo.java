package com.tong.week5.work2;

import org.springframework.beans.factory.annotation.Autowired;

public class Demo {
    //第一种属性自动装配
    @Autowired
    Bean bean1;

    //第二种直接构造方法里面
    public Demo(Bean bean2){
        //第三种直接实例化
        Bean bean3 = new Bean();
    }
}
