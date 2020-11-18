package com.tong.week5.work1;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

class AopHandler implements InvocationHandler {
    private Object object;

    public AopHandler(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(this.object,args);
        after();
        return null;
    }
    public void before(){
        System.out.println("before_aop");
    }
    public void after(){
        System.out.println("after_aop");
    }
}
