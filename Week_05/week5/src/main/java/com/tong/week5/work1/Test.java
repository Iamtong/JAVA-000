package com.tong.week5.work1;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String arg[]){
        DemoImpl demo = new DemoImpl();
        ClassLoader classLoader = demo.getClass().getClassLoader();
        Class[] interfaces =  demo.getClass().getInterfaces();
        AopHandler aopHandler = new AopHandler(demo);

        DemoService test = (DemoService) Proxy.newProxyInstance(classLoader,interfaces,aopHandler);
        test.add();
    }
}
